package bside.palmtree.interfaces.graphql.member;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bside.palmtree.service.member.MemberService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
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
	public Member getMember(String name) {

		return this.modelMapper.map(
			this.memberService.findByName(name),
			Member.class
		);
	}

	@GraphQLMutation(name = "putMember")
	public Member putMember(Member member) {

		return this.modelMapper.map(
			this.memberService.save(member.getName()),
			Member.class
		);
	}

	@GraphQLMutation(name = "deleteMember")
	public Boolean deleteMember(String name) {

		return this.memberService.deleteMember(name);
	}
}
