package bside.palmtree.domain.challenge.ranking;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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
@Embeddable
public class RankingId implements Serializable {

	@Column(name = "member_id", nullable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long memberId;

	@Column(name = "challenge_date", nullable = false)
	private LocalDate challengeDate;

	protected RankingId(Member member, LocalDate challengeDate) {
		this.memberId = member.getId();
		this.challengeDate = challengeDate;
	}

	protected RankingId(Long memberId, LocalDate challengeDate) {
		this.memberId = memberId;
		this.challengeDate = challengeDate;
	}
}
