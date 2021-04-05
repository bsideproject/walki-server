package bside.palmtree.domain.member.detail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import bside.palmtree.domain.common.BaseTimeEntity;
import bside.palmtree.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by YHH on 2021/04/01
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class MemberDetail extends BaseTimeEntity {
	@Id
	@Column(name = "member_id", nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Max(31)
	@Min(0)
	@Column(name = "challenge_goal")
	private Integer challengeGoal;

	@Builder
	public MemberDetail(
		Member member,
		String name,
		Integer challengeGoal) {
		this.id = member.getId();
		this.name = name;
		this.challengeGoal = challengeGoal;
	}

	public static MemberDetail fromDefault(Member member) {
		return MemberDetail.builder()
			.member(member)
			.build();
	}

	public void update(MemberDetail memberDetail) {
		this.name = memberDetail.name;
		this.challengeGoal = memberDetail.challengeGoal;
	}
}
