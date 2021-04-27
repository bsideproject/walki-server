package bside.palmtree.domain.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCoachRepository extends JpaRepository<MemberCoach, Long> {

	Optional<MemberCoach> findByMemberId(Long memberId);
}
