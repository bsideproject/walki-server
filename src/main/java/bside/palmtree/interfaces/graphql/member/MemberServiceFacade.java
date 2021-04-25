package bside.palmtree.interfaces.graphql.member;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bside.palmtree.config.LoggedIn;
import bside.palmtree.service.member.MemberService;
import bside.palmtree.service.member.dto.MemberDetailDto;
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
	private final ModelMapper modelMapper;

	@GraphQLQuery(name = "getMember")
	public Member getMember(@GraphQLRootContext @LoggedIn bside.palmtree.domain.member.Member loginMember) {

		return this.modelMapper.map(
			this.memberService.findById(loginMember),
			Member.class
		);
	}

	@GraphQLMutation(name = "putMember")
	public Member putMember(@GraphQLRootContext @LoggedIn bside.palmtree.domain.member.Member loginMember,
		Member member) {
		MemberDetailDto memberDetailDto = this.modelMapper.map(member, MemberDetailDto.class);

		return this.modelMapper.map(
			this.memberService.save(loginMember, memberDetailDto),
			Member.class
		);
	}
}
