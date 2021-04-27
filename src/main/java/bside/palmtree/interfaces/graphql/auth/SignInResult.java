package bside.palmtree.interfaces.graphql.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by YHH on 2021/04/02
 */
@NoArgsConstructor
@Getter
@ToString
public class SignInResult {
	private String accessToken;

	public SignInResult(String accessToken) {
		this.accessToken = accessToken;
	}
}
