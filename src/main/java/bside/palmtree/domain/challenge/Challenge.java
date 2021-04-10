package bside.palmtree.domain.challenge;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * Created by YHH on 2021/03/22
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Challenge extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "challenge_id", nullable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long id;

	@Column(name = "member_id", nullable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long memberId;

	@Column(name = "challenge_date", nullable = false)
	private LocalDate challengeDate;

	@Min(value = 0)
	@Column(name = "step", nullable = false)
	private Integer step;

	@Min(value = 200)
	@Max(value = 10000)
	@Column(name = "step_goal", nullable = false)
	private Integer stepGoal;

	@Builder
	public Challenge(LocalDate challengeDate, Integer stepGoal, Member member) {
		this.memberId = member.getId();
		this.challengeDate = challengeDate;
		this.step = 0;
		this.stepGoal = stepGoal;
	}

	public void update(Integer step, Integer stepGoal) {
		if (step != null) {
			this.step = step;
		}
		if (stepGoal != null) {
			this.stepGoal = stepGoal;
		}
	}

	public Boolean isSuccess() {
		return this.step >= this.stepGoal;
	}
}
