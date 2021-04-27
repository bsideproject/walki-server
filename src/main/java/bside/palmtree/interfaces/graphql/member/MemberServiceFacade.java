package bside.palmtree.interfaces.graphql.member;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bside.palmtree.config.LoggedIn;
import bside.palmtree.domain.membercoach.MemberCoach;
import bside.palmtree.interfaces.graphql.coach.Coach;
import bside.palmtree.service.member.MemberService;
import bside.palmtree.service.member.dto.MemberDetailDto;
import bside.palmtree.service.membercoach.MemberCoachService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2021/02/15
 */
@RequiredArgsConstructor
@Service
@GraphQLApi
public class MemberServiceFacade {
	private final MemberService memberService;
	private final MemberCoachService memberCoachService;
	private final ModelMapper modelMapper;

	@GraphQLQuery(name = "getMember")
	public Member getMember(@GraphQLRootContext @LoggedIn bside.palmtree.domain.member.Member loginMember) {

		Member resultMember = this.modelMapper.map(
			this.memberService.findById(loginMember),
			Member.class
		);

		MemberCoach memberCoach = this.memberCoachService.findBy(loginMember);
		if (Objects.nonNull(memberCoach)) {
			resultMember.setCoach(this.modelMapper.map(memberCoach.getCoach(), Coach.class));
		}

		return resultMember;
	}

	@GraphQLMutation(name = "putMember")
	public Member putMember(@GraphQLRootContext @LoggedIn bside.palmtree.domain.member.Member loginMember,
		Member member) {

		MemberDetailDto memberDetailDto = this.modelMapper.map(member, MemberDetailDto.class);

		Member resultMember = this.modelMapper.map(
			this.memberService.save(loginMember, memberDetailDto),
			Member.class
		);

		if (member.hasCoachId()) {
			MemberCoach memberCoach = this.memberCoachService.save(loginMember, member.coachId);
			resultMember.setCoach(this.modelMapper.map(memberCoach.getCoach(), Coach.class));
		}

		return resultMember;
	}
}
