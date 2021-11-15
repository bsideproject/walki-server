package bside.palmtree.service.membercoach;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bside.palmtree.config.LoggingParam;
import bside.palmtree.domain.coach.Coach;
import bside.palmtree.domain.coach.CoachRepository;
import bside.palmtree.domain.member.Member;
import bside.palmtree.domain.membercoach.MemberCoach;
import bside.palmtree.domain.membercoach.MemberCoachRepository;
import lombok.RequiredArgsConstructor;

@LoggingParam
@Service
@RequiredArgsConstructor
public class MemberCoachService {

	private final MemberCoachRepository memberCoachRepository;
	private final CoachRepository coachRepository;

	@Transactional
	public MemberCoach save(Member member, Long coachId) {
		Optional<MemberCoach> maybeMemberCoach = this.memberCoachRepository.findByMember(member);
		Coach coach = this.coachRepository.findById(coachId)
			.orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 코치 아이디 입니다."));

		if (maybeMemberCoach.isPresent()) {
			MemberCoach memberCoach = maybeMemberCoach.get();
			memberCoach.update(coach);

			return memberCoach;
		}

		MemberCoach memberCoach = MemberCoach.builder()
			.member(member)
			.coach(coach)
			.build();

		return this.memberCoachRepository.save(memberCoach);
	}

	public MemberCoach findBy(Member member) {
		return this.memberCoachRepository.findByMember(member)
			.orElse(null);
	}
}
