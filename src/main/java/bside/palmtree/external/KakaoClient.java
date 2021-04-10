package bside.palmtree.external;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import reactor.core.publisher.Mono;

/**
 * Created by YHH on 2021/04/09
 */
public class KakaoClient implements OAuthClient {

	private WebClient KApiClient() {
		return WebClient.builder()
			.baseUrl("https://kapi.kakao.com")
			.build();
	}

	public TokenInfo getTokenInfo(String accessToken) {
		return this.KApiClient()
			.get()
			.uri("/v1/user/access_token_info")
			.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
			.exchangeToMono(clientResponse -> {
				if (clientResponse.statusCode().is2xxSuccessful()) {
					return clientResponse.bodyToMono(TokenInfo.class);
				}
				return clientResponse.bodyToMono(KakaoErrorResponse.class)
					.flatMap(response -> Mono.error(
						new ResponseStatusException(clientResponse.statusCode(), response.getMsg())));
			})
			.timeout(Duration.ofSeconds(5))
			.flux()
			.toStream()
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("fail getTokenInfo"));
	}
}
