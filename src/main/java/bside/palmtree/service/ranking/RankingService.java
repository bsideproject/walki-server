package bside.palmtree.service.ranking;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import bside.palmtree.domain.challenge.Challenge;
import bside.palmtree.domain.challenge.ChallengeRepository;
import bside.palmtree.domain.challenge.ranking.ChallengeRanking;
import bside.palmtree.domain.challenge.ranking.Ranking;
import bside.palmtree.domain.challenge.ranking.RankingRepository;
import bside.palmtree.domain.member.Member;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2021/04/13
 */
@RequiredArgsConstructor
@Service
public class RankingService {
	private final RankingRepository rankingRepository;
	private final ChallengeRepository challengeRepository;

	public void createRankings(LocalDate date) {
		List<Challenge> challenges = this.challengeRepository.findByChallengeDate(date);

		ChallengeRanking challengeRanking = ChallengeRanking.from(challenges);

		this.rankingRepository.saveAll(challengeRanking.getRankings());
	}

	public List<Ranking> findTop10ByDate(LocalDate date) {
		return this.rankingRepository.findTop10ById_ChallengeDate(date);
	}

	public List<Ranking> findAllMemberAndDate(Member member, LocalDate start, LocalDate end) {
		return this.rankingRepository.findById_MemberIdAndId_ChallengeDateBetween(member.getId(), start, end);
	}
}
