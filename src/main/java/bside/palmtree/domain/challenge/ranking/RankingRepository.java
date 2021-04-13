package bside.palmtree.domain.challenge.ranking;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by YHH on 2021/04/13
 */
public interface RankingRepository extends JpaRepository<Ranking, RankingId> {

	List<Ranking> findTop10ById_ChallengeDate(LocalDate date);

	List<Ranking> findById_MemberIdAndId_ChallengeDateBetween(Long memberId, LocalDate start, LocalDate end);
}
