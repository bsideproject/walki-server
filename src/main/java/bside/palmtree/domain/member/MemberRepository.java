package bside.palmtree.domain.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by YHH on 2021/02/14
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findBySocialAndSocialId(Social social, String socialId);
}
