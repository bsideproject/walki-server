package bside.palmtree.service.memberCoach.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberCoachDto {

	private Long id;
	private Long memberId;
	private Long coachId;
}
