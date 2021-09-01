package bside.palmtree.external.oauth;

import bside.palmtree.external.oauth.dto.TokenInfo;

/**
 * Created by YHH on 2021/04/09
 */
public interface OAuthClient {
	TokenInfo getTokenInfo(String accessToken);
}
