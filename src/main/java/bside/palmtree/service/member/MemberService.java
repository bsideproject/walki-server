package bside.palmtree.service.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bside.palmtree.config.LoggingParam;
import bside.palmtree.domain.member.Member;
import bside.palmtree.domain.member.MemberDetail;
import bside.palmtree.domain.member.MemberRepository;
import bside.palmtree.service.member.dto.MemberDetailDto;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2021/02/14
 */
@LoggingParam
@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
	private final MemberRepository memberRepository;

	@Transactional
	public Member save(Member member, MemberDetailDto memberDetailDto) {
		Member findMember = this.memberRepository.findById(member.getId())
			.filter(Member::isActive)
			.orElseThrow(() -> new IllegalArgumentException("없는 유저 입니다."));

		MemberDetail memberDetail = MemberDetail.builder()
			.name(memberDetailDto.getName())
			.profileImage(memberDetailDto.getProfileImage())
			.build();

		findMember.update(memberDetail);

		return findMember;
	}

	public Member findById(Member member) {
		return this.memberRepository.findById(member.getId())
			.filter(Member::isActive)
			.orElseThrow(() -> new IllegalArgumentException("없는 유저 입니다."));
	}

	@Transactional
	public Boolean deleteMember(Member member) {
		Member findMember = this.memberRepository.findById(member.getId())
			.filter(Member::isActive)
			.orElseThrow(() -> new IllegalArgumentException("없는 유저 입니다."));

		findMember.delete();

		return true;
	}
}
