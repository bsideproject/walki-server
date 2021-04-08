package bside.palmtree.domain.challenge.report;

import java.util.ArrayList;
import java.util.List;

import bside.palmtree.domain.challenge.Challenge;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by YHH on 2021/04/05
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Report {
	private Integer stepAchievement;
	private Integer stepGoal;
	private Integer challengeAchievement;
	private Integer challengeGoal;
	private List<Challenge> challenges;

	private Report(List<Challenge> challenges) {
		this.challenges = challenges;
		this.stepAchievement = 0;
		this.stepGoal = 0;
		this.challengeAchievement = 0;
		this.challengeGoal = challenges.size();

		for (Challenge challenge : challenges) {
			if (challenge.isSuccess()) {
				this.challengeAchievement++;
			}
			this.stepAchievement += challenge.getStep();
			this.stepGoal += challenge.getStepGoal();
		}
	}

	public static Report from(List<Challenge> challenges) {
		return new Report(challenges != null ? challenges : new ArrayList<>());
	}
}
