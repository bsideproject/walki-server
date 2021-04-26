package bside.palmtree.domain.challenge.ranking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import bside.palmtree.domain.challenge.Challenge;
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
public class ChallengeRanking {
	private List<Ranking> rankings;

	private ChallengeRanking(List<Challenge> challenges) {
		List<Challenge> sortedChallenges = challenges.stream()
			.sorted(Comparator.comparing(Challenge::getStep).reversed()
				.thenComparing(Challenge::getCreatedAt)
				.thenComparing(Challenge::getMemberId))
			.collect(Collectors.toList());

		this.rankings = getRankings(sortedChallenges);
	}

	private List<Ranking> getRankings(List<Challenge> sortedChallenges) {
		long rankNumber = 0L;
		List<Ranking> rankings = new ArrayList<>();

		for (Challenge sortedChallenge : sortedChallenges) {
			Ranking ranking = Ranking.from(sortedChallenge, ++rankNumber);
			rankings.add(ranking);
		}
		return rankings;
	}

	public static ChallengeRanking from(List<Challenge> challenges) {
		return new ChallengeRanking(challenges);
	}
}
