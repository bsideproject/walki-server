package bside.palmtree.external.oauth;

import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

import bside.palmtree.domain.member.Social;
import bside.palmtree.external.oauth.apple.AppleClient;
import bside.palmtree.external.oauth.dto.TokenInfo;
import bside.palmtree.external.oauth.kakao.KakaoClient;

/**
 * Created by YHH on 2021/04/02
 */
@Component
@Profile("prod")
public class OAuthServiceProd implements OAuthService {
	private final Map<Social, OAuthClient> clientMap = Maps.newHashMap();

	public OAuthServiceProd(KakaoClient kakaoClient, AppleClient appleClient) {
		this.clientMap.put(Social.KAKAO, kakaoClient);
		this.clientMap.put(Social.APPLE, appleClient);
	}

	public TokenInfo getTokenInfo(Social social, String token) {
		if (clientMap.containsKey(social)) {
			return clientMap.get(social).getTokenInfo(token);
		}
		throw new RuntimeException("지원하지 않는 로그인 서비스 입니다.");
	}
}
