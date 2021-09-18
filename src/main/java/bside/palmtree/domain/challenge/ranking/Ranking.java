package bside.palmtree.domain.challenge.ranking;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"challenge_date", "member_id"}))
public class Ranking extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ranking_id", nullable = false)
	private Long id;

	@Column(name = "challenge_date", nullable = false)
	private LocalDate challengeDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@Column(name = "ranking_number", nullable = false)
	private Long number;

	public Ranking(LocalDate challengeDate, Member member, Long number) {
		this.challengeDate = challengeDate;
		this.member = member;
		this.number = number;
	}

	public static Ranking from(Member member, LocalDate date, Long number) {
		return new Ranking(date, member, number);
	}

	public static Ranking from(Challenge challenge, Long number) {

		return new Ranking(challenge.getChallengeDate(), Member.builder().id(challenge.getMemberId()).build(), number);
	}
}
