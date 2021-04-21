package bside.palmtree.domain.challenge.ranking;

import java.time.LocalDate;
import java.util.List;

import bside.palmtree.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by YHH on 2021/04/13
 */
public interface RankingRepository extends JpaRepository<Ranking, Long> {

	List<Ranking> findTop10ByChallengeDateOrderByNumber(LocalDate date);

	List<Ranking> findByMemberAndChallengeDateBetween(Member member, LocalDate start, LocalDate end);
}
