package bside.palmtree.external.oauth.kakao;

import java.time.Duration;
import java.util.function.Function;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import bside.palmtree.external.oauth.OAuthClient;
import bside.palmtree.external.oauth.exception.OAuthClientException;
import bside.palmtree.external.oauth.dto.TokenInfo;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Created by YHH on 2021/04/09
 */
@Slf4j
@Component
public class KakaoClient implements OAuthClient {
	private static final String API_SERVER_HOST = "https://kapi.kakao.com";
	private static final String TOKEN_INFO_PATH = "/v2/user/me";

	private WebClient kakaoApiClient() {
		return WebClient.builder()
			.baseUrl(API_SERVER_HOST)
			.build();
	}

	public TokenInfo getTokenInfo(String accessToken) {
		return this.kakaoApiClient()
			.get()
			.uri(TOKEN_INFO_PATH)
			.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
			.retrieve()
			.onStatus(HttpStatus::isError, setResponseBodyInException())
			.bodyToMono(TokenInfo.class)
			.timeout(Duration.ofSeconds(5))
			.flux()
			.toStream()
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("fail getTokenInfo"));
	}

	private Function<ClientResponse, Mono<? extends Throwable>> setResponseBodyInException() {
		return response -> response.bodyToMono(String.class)
			.flatMap(error -> {
				log.error("statusCode : {}, error : {}", response.statusCode(), error);
				return Mono.error(new OAuthClientException(error));
			});
	}
}
