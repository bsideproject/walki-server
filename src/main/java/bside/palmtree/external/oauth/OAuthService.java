package bside.palmtree.external.oauth;

import bside.palmtree.domain.member.Social;
import bside.palmtree.external.oauth.dto.TokenInfo;

public interface OAuthService {

	TokenInfo getTokenInfo(Social social, String token);

}
