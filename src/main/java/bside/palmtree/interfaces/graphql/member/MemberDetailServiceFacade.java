package bside.palmtree.interfaces.graphql.member;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bside.palmtree.config.LoggedIn;
import bside.palmtree.domain.member.Member;
import bside.palmtree.service.member.MemberDetailService;
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
public class MemberDetailServiceFacade {
	private final MemberDetailService memberDetailService;
	private final ModelMapper modelMapper;

	@GraphQLQuery(name = "getMemberDetail")
	public MemberDetail getMemberDetail(@GraphQLRootContext @LoggedIn Member member) {

		return this.modelMapper.map(
			this.memberDetailService.findById(member),
			MemberDetail.class
		);
	}

	@GraphQLMutation(name = "putMemberDetail")
	public MemberDetail putMemberDetail(@GraphQLRootContext @LoggedIn Member member, MemberDetail memberDetail) {
		MemberDetailDto memberDetailDto = this.modelMapper.map(memberDetail, MemberDetailDto.class);

		return this.modelMapper.map(
			this.memberDetailService.save(member, memberDetailDto),
			MemberDetail.class
		);
	}
}
