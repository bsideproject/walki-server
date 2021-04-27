package bside.palmtree.service.memberCoach;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bside.palmtree.domain.member.Member;
import bside.palmtree.domain.member.MemberCoach;
import bside.palmtree.domain.member.MemberCoachRepository;
import bside.palmtree.service.memberCoach.dto.MemberCoachDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberCoachService {

	private final MemberCoachRepository coachRepository;

	@Transactional
	public MemberCoach save(Member member, MemberCoachDto memberCoachDto) {
		MemberCoach findMember = coachRepository.findByMemberId(member.getId())
			.orElseThrow(() -> new IllegalArgumentException(""));

		return findMember;
	}
}
