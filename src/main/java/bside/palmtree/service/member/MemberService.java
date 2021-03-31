package bside.palmtree.service.member;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bside.palmtree.domain.member.Member;
import bside.palmtree.domain.member.MemberRepository;
import bside.palmtree.service.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2021/02/14
 */
@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
	private final MemberRepository memberRepository;

	public Member findByName(String name) {
		return this.memberRepository.findByName(name)
			.orElseThrow(MemberNotFoundException::new);
	}

	public Boolean deleteMember(String name) {
		Optional<Member> maybeMember = this.memberRepository.findByName(name);

		if (maybeMember.isPresent()) {
			this.memberRepository.delete(maybeMember.get());

			return true;
		}

		return false;
	}

	public Member save(String name) {
		Member member = Member.builder()
			.name(name)
			.build();

		return this.memberRepository.save(member);
	}
}
