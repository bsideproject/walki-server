package bside.palmtree.service.ranking;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private final EntityManager entityManager;

	@Transactional
	public void createRankings(LocalDate date) {
		this.rankingRepository.deleteAllByChallengeDate(date);
		this.entityManager.flush();

		List<Challenge> challenges = this.challengeRepository.findByChallengeDate(date);

		ChallengeRanking challengeRanking = ChallengeRanking.from(challenges);
		this.rankingRepository.saveAll(challengeRanking.getRankings());
	}

	public List<Ranking> findTop10ByDate(LocalDate date) {
		return this.rankingRepository.findTop10ByChallengeDateOrderByNumber(date);
	}

	public List<Ranking> findAllByMemberAndDate(Member member, LocalDate start, LocalDate end) {
		return this.rankingRepository.findByMemberAndChallengeDateBetween(member, start, end);
	}
}
