package bside.palmtree.service.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by YHH on 2021/04/03
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberDetailDto {
	private String name;
	private String profileImage;

	@Builder
	public MemberDetailDto(String name, String profileImage) {
		this.name = name;
		this.profileImage = profileImage;
	}
}
