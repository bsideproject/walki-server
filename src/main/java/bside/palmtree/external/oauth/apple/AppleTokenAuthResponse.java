package bside.palmtree.external.oauth.apple;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppleTokenAuthResponse {
	@JsonProperty(value = "access_token")
	private String accessToken;
	@JsonProperty(value = "expires_in")
	private Long expiresIn;
	@JsonProperty(value = "id_token")
	private String idToken;
	@JsonProperty(value = "refresh_token")
	private String refreshToken;
	@JsonProperty(value = "token_type")
	private String tokenType;
}
