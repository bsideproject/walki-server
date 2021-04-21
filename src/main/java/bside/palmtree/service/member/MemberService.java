package bside.palmtree.service.member;

import bside.palmtree.domain.member.MemberDetail;
import bside.palmtree.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bside.palmtree.domain.member.Member;
import bside.palmtree.service.member.dto.MemberDetailDto;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2021/02/14
 */
@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
	private final MemberRepository memberRepository;

	@Transactional
	public Member save(Member member, MemberDetailDto memberDetailDto) {
		Member findMember = this.memberRepository.findById(member.getId())
			.orElseThrow(() -> new IllegalArgumentException("없는 유저 입니다."));

		MemberDetail memberDetail = MemberDetail.builder()
			.name(memberDetailDto.getName())
			.build();

		findMember.update(memberDetail);

		return findMember;
	}

	public Member findById(Member member) {
		return this.memberRepository.findById(member.getId())
			.orElseThrow(() -> new IllegalArgumentException("없는 유저 입니다."));
	}
}
