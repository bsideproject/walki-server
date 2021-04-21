package bside.palmtree.interfaces.graphql.ranking;

import bside.palmtree.interfaces.graphql.member.Member;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.*;


import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@GraphQLType(name = "Ranking",
	fieldOrder = {"member", "number", "challengeDate"})
public class Ranking {
	private Member member;
	private Long number;
	private LocalDate challengeDate;

	@Builder
	public Ranking(Member member, Long number, LocalDate challengeDate) {
		this.member = member;
		this.number = number;
		this.challengeDate = challengeDate;
	}
}
