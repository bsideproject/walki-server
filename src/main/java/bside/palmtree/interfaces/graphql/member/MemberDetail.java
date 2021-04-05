package bside.palmtree.interfaces.graphql.member;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by YHH on 2021/02/15
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@GraphQLType(name = "MemberDetail", fieldOrder = {"name", "challenge_goal"})
public class MemberDetail {

	@GraphQLQuery(name = "name")
	private String name;

	@GraphQLQuery(name = "challenge_goal")
	private Integer challengeGoal;

	@Builder
	public MemberDetail(String name, Integer challengeGoal) {
		this.name = name;
		this.challengeGoal = challengeGoal;
	}
}
