package bside.palmtree.domain.challenge.ranking;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bside.palmtree.domain.member.Member;

/**
 * Created by YHH on 2021/04/13
 */
public interface RankingRepository extends JpaRepository<Ranking, Long> {

	void deleteAllByChallengeDate(LocalDate date);

	List<Ranking> findTop10ByChallengeDateOrderByNumber(LocalDate date);

	List<Ranking> findByMemberAndChallengeDateBetween(Member member, LocalDate start, LocalDate end);
}
