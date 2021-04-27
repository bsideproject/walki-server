package bside.palmtree.external;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

import bside.palmtree.domain.member.Social;

/**
 * Created by YHH on 2021/04/02
 */
@Component
public class OAuthService {
	private static final Map<Social, OAuthClient> CLIENT_MAP = ImmutableMap.<Social, OAuthClient>builder()
		.put(Social.KAKAO, new KakaoClient())
		.build();

	public TokenInfo getTokenInfo(Social social, String token) {
		if (CLIENT_MAP.containsKey(social)) {
			return CLIENT_MAP.get(social).getTokenInfo(token);
		}

		return TokenInfo.builder()
			.id("0")
			.build();
	}
}
