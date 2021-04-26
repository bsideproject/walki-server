package bside.palmtree.interfaces.graphql.report;

import java.util.List;

import bside.palmtree.interfaces.graphql.challenge.Challenge;
import io.leangen.graphql.annotations.types.GraphQLType;
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
@GraphQLType(name = "Report",
	fieldOrder = {"stepAchievement", "stepGoal", "challengeAchievement", "challengeGoal", "challenges"})
public class Report {
	private Integer stepAchievement;
	private Integer stepGoal;
	private Integer challengeAchievement;
	private Integer challengeGoal;
	private List<Challenge> challenges;
}
