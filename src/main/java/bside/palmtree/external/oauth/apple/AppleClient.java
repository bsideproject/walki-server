package bside.palmtree.external.oauth.apple;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bside.palmtree.config.AuthorizationException;
import bside.palmtree.external.oauth.OAuthClient;
import bside.palmtree.external.oauth.dto.TokenInfo;
import bside.palmtree.external.oauth.exception.OAuthClientException;
import io.jsonwebtoken.Claims;
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
	private final ObjectMapper objectMapper;

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
			.timeout(Duration.ofSeconds(20))
			.flux()
			.toStream()
			.findFirst()
			.map(this::convertTokenInfo)
			.orElseThrow(() -> new IllegalArgumentException("fail getTokenInfo"));
	}

	public ApplePublicKeyResponse getPublicKey() {
		return this.appleApiClient()
			.get()
			.uri("/auth/keys")
			.retrieve()
			.onStatus(HttpStatus::isError, setResponseBodyInException())
			.bodyToMono(ApplePublicKeyResponse.class)
			.timeout(Duration.ofSeconds(20))
			.flux()
			.toStream()
			.findFirst()
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
			.signWith(SignatureAlgorithm.ES256, getPrivateKey())
			.compact();
	}

	private PrivateKey getPrivateKey() {
		try {
			String key = IOUtils.toString(
				new ClassPathResource(appleClientProperties.getKeyPath()).getInputStream(),
				StandardCharsets.UTF_8
			);
			key = key.replaceFirst("-----BEGIN PRIVATE KEY-----", "");
			key = key.replaceFirst("-----END PRIVATE KEY-----", "");
			key = key.replaceAll("\\s", "");

			byte[] keyBytes = Base64Utils.decodeFromString(key);
			KeyFactory factory = KeyFactory.getInstance("EC");
			return factory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
		} catch (Exception e) {
			throw new AuthorizationException("애플 PrivateKey 생성 실패");
		}
	}

	public TokenInfo convertTokenInfo(AppleTokenAuthResponse appleTokenAuthResponse) {
		log.info("AppleTokenAuthResponse : {}", appleTokenAuthResponse);
		try {
			String tokenId = appleTokenAuthResponse.getIdToken();

			String headerOfIdentityToken = tokenId.substring(0, tokenId.indexOf("."));
			Map<String, String> header = this.objectMapper.readValue(
				new String(Base64.getDecoder().decode(headerOfIdentityToken), StandardCharsets.UTF_8),
				new TypeReference<>() {
				});
			ApplePublicKeyResponse.Key key = this.getPublicKey().getMatchedKeyBy(header.get("kid"), header.get("alg"))
				.orElseThrow(() -> new NullPointerException("Failed get public key from apple's id server."));

			byte[] nBytes = Base64.getUrlDecoder().decode(key.getN());
			byte[] eBytes = Base64.getUrlDecoder().decode(key.getE());

			BigInteger n = new BigInteger(1, nBytes);
			BigInteger e = new BigInteger(1, eBytes);

			RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
			KeyFactory keyFactory = KeyFactory.getInstance(key.getKty());
			PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

			Claims claims = Jwts.parser()
				.setSigningKey(publicKey)
				.parseClaimsJws(tokenId)
				.getBody();

			return TokenInfo.builder()
				.id(claims.get("email", String.class))
				.properties(TokenInfo.Properties.builder()
					.nickname(claims.get("givenName", String.class))
					.build())
				.build();
		} catch (Exception noSuchAlgorithmException) {
			throw new OAuthClientException("애플 로그인 실패");
		}
	}

	private Function<ClientResponse, Mono<? extends Throwable>> setResponseBodyInException() {
		return response -> response.bodyToMono(String.class)
			.flatMap(error -> {
				log.error("statusCode : {}, error : {}", response.statusCode(), error);
				return Mono.error(new OAuthClientException(error));
			});
	}
}
