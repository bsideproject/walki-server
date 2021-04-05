package bside.palmtree.domain.challenge;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by YHH on 2021/03/22
 */
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

	Optional<Challenge> findByMemberIdAndChallengeDate(Long memberId, LocalDate challengeDate);

	List<Challenge> findAllByMemberIdOrderByChallengeDateDesc(Long memberId);

	List<Challenge> findAllByMemberIdAndChallengeDateBetween(Long memberId, LocalDate start, LocalDate end);
}
