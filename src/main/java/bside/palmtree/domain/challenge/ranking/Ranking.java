package bside.palmtree.domain.challenge.ranking;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import bside.palmtree.domain.challenge.Challenge;
import bside.palmtree.domain.common.BaseTimeEntity;
import bside.palmtree.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by YHH on 2021/04/13
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@ToString
@Entity
public class Ranking extends BaseTimeEntity {

	@EmbeddedId
	private RankingId id;

	@Column(name = "ranking_number", nullable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long number;

	private Ranking(RankingId id, Long number) {
		this.id = id;
		this.number = number;
	}

	public static Ranking from(Member member, LocalDate date, Long number) {
		RankingId rankingId = new RankingId(member, date);

		return new Ranking(rankingId, number);
	}

	public static Ranking from(Challenge challenge, Long number) {
		RankingId rankingId = new RankingId(challenge.getMemberId(), challenge.getChallengeDate());

		return new Ranking(rankingId, number);
	}
}
