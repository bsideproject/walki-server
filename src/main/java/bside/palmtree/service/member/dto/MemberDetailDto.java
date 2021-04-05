package bside.palmtree.service.member.dto;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by YHH on 2021/04/03
 */
@Getter
@Setter
@ToString
public class MemberDetailDto {
	private String name;
	private Integer challengeGoal;
}
