package bside.palmtree.external.oauth.apple;

import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import bside.palmtree.external.oauth.OAuthClient;
import bside.palmtree.external.oauth.exception.OAuthClientException;
import bside.palmtree.external.oauth.dto.TokenInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class AppleClient implements OAuthClient {
	private static final String TOKEN_AUTH_URL = "/auth/token";

	private final AppleClientProperties appleClientProperties;

	private WebClient appleApiClient() {
		return WebClient.builder()
			.baseUrl(this.appleClientProperties.getBaseUrl())
			.build();
	}

	@Override
	public TokenInfo getTokenInfo(String accessToken) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("client_id", this.appleClientProperties.getServiceId());
		body.add("client_secret", createClientSecret());
		body.add("code", accessToken);
		body.add("grant_type", "authorization_code");

		return this.appleApiClient()
			.post()
			.uri(TOKEN_AUTH_URL)
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.body(BodyInserters.fromFormData(body))
			.retrieve()
			.onStatus(HttpStatus::isError, setResponseBodyInException())
			.bodyToMono(AppleTokenAuthResponse.class)
			.timeout(Duration.ofSeconds(5))
			.flux()
			.toStream()
			.findFirst()
			.map(this::convertTokenInfo)
			.orElseThrow(() -> new IllegalArgumentException("fail getTokenInfo"));
	}

	private String createClientSecret() {
		Date now = new Date();

		return Jwts.builder()
			.setIssuer(appleClientProperties.getTeamId())
			.setIssuedAt(now) // 토큰 발행 시간 정보
			.setExpiration(new Date(now.getTime() + 3600000))
			.setAudience(appleClientProperties.getBaseUrl())
			.setSubject(appleClientProperties.getServiceId())
			.signWith(SignatureAlgorithm.HS256, appleClientProperties.getKeyId())
			.compact();
	}

	public TokenInfo convertTokenInfo(AppleTokenAuthResponse appleTokenAuthResponse) {
		String tokenId = appleTokenAuthResponse.getIdToken();

		String id = Jwts.parser()
			.parseClaimsJws(tokenId)
			.getBody()
			.get("email", String.class);

		return new TokenInfo(id);
	}

	private Function<ClientResponse, Mono<? extends Throwable>> setResponseBodyInException() {
		return response -> response.bodyToMono(String.class)
			.flatMap(error -> {
				log.error("statusCode : {}, error : {}", response.statusCode(), error);
				return Mono.error(new OAuthClientException(error));
			});
	}
}
