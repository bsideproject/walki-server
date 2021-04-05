package bside.palmtree.external;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by YHH on 2021/04/02
 */
@Getter
@ToString
public class TokenInfo {
	private String id;

	@Builder
	public TokenInfo(String id) {
		this.id = id;
	}
}
