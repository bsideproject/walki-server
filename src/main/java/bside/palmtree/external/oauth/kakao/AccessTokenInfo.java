package bside.palmtree.external.oauth.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccessTokenInfo {

	@JsonProperty(value = "id")
	private Long id;

	@JsonProperty(value = "expires_in")
	private Long expiresIn;

	@JsonProperty(value = "app_id")
	private Long appId;

}
