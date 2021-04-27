package bside.palmtree.interfaces.graphql.memberCoach;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bside.palmtree.config.LoggedIn;
import bside.palmtree.domain.member.Member;
import bside.palmtree.service.memberCoach.MemberCoachService;
import bside.palmtree.service.memberCoach.dto.MemberCoachDto;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLRootContext;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@GraphQLApi
public class MemberCoachServiceFacade {
	private final MemberCoachService memberCoachService;
	private final ModelMapper modelMapper;

	@GraphQLMutation(name = "putMemberCoach")
	public MemberCoach putMemberCoach(@GraphQLRootContext @LoggedIn Member loginMember, MemberCoachDto memberCoach) {
		return this.modelMapper.map(
			this.memberCoachService.save(loginMember, memberCoach)
			,MemberCoach.class
		);
	}
}
