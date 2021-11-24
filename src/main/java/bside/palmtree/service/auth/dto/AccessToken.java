package bside.palmtree.service.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class AccessToken {
	private Boolean isNew;
	private String accessToken;

	@Builder
	public AccessToken(Boolean isNew, String accessToken) {
		this.isNew = isNew;
		this.accessToken = accessToken;
	}
}
