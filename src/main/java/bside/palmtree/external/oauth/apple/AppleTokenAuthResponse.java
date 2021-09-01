package bside.palmtree.external.oauth.apple;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AppleTokenAuthResponse {
	private String accessToken;
	private Long expiresIn;
	private String idToken;
	private String refreshToken;
	private String tokenType;
}
