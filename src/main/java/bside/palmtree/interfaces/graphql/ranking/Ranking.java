package bside.palmtree.interfaces.graphql.ranking;

import bside.palmtree.interfaces.graphql.challenge.Challenge;
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
@GraphQLType(name = "Ranking", fieldOrder = {"member", "challenge", "number"})
public class Ranking {
	private Member member;
	private Challenge challenge;
	private Long number;

	@Builder
	public Ranking(Member member, Challenge challenge, Long number) {
		this.member = member;
		this.challenge = challenge;
		this.number = number;
	}
}
