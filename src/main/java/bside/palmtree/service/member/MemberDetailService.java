package bside.palmtree.service.member;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bside.palmtree.domain.member.Member;
import bside.palmtree.domain.member.detail.MemberDetail;
import bside.palmtree.domain.member.detail.MemberDetailRepository;
import bside.palmtree.service.member.dto.MemberDetailDto;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2021/02/14
 */
@RequiredArgsConstructor
@Service
@Transactional
public class MemberDetailService {
	private final MemberDetailRepository memberDetailRepository;

	@Transactional
	public MemberDetail save(Member member, MemberDetailDto memberDetailDto) {
		Optional<MemberDetail> maybeMemberDetail = this.memberDetailRepository.findById(member.getId());

		MemberDetail memberDetail = MemberDetail.builder()
			.member(member)
			.name(memberDetailDto.getName())
			.challengeGoal(memberDetailDto.getChallengeGoal())
			.build();

		if (maybeMemberDetail.isPresent()) {
			MemberDetail persistMemberDetail = maybeMemberDetail.get();
			persistMemberDetail.update(memberDetail);
			return persistMemberDetail;
		}

		return this.memberDetailRepository.save(memberDetail);
	}

	public MemberDetail findById(Member member) {
		Optional<MemberDetail> maybeMemberDetail = this.memberDetailRepository.findById(member.getId());

		if (maybeMemberDetail.isEmpty()) {

			return this.memberDetailRepository.save(MemberDetail.fromDefault(member));
		}

		return maybeMemberDetail.get();

	}
}
