package bside.palmtree.domain.membercoach;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bside.palmtree.domain.member.Member;

public interface MemberCoachRepository extends JpaRepository<MemberCoach, Long> {

	Optional<MemberCoach> findByMember(Member member);
}
