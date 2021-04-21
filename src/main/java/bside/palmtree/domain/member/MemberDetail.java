package bside.palmtree.domain.member;

import lombok.*;
/**
 * Created by YHH on 2021/02/14
 */
@Getter
@Setter
@ToString
public class MemberDetail {
	private String name;

	@Builder
	public MemberDetail(String name) {
		this.name = name;
	}
}
