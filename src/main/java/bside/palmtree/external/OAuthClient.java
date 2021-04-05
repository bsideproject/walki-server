package bside.palmtree.external;

import org.springframework.stereotype.Component;

import bside.palmtree.domain.member.Social;

/**
 * Created by YHH on 2021/04/02
 */
@Component
public class OAuthClient {

	public TokenInfo getTokenInfo(Social social, String token) {
		return TokenInfo.builder()
			.id("0")
			.build();
	}
}
