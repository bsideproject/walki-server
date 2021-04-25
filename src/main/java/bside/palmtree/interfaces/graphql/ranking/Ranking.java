package bside.palmtree.interfaces.graphql.ranking;

import java.time.LocalDate;

import bside.palmtree.interfaces.graphql.member.Member;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
