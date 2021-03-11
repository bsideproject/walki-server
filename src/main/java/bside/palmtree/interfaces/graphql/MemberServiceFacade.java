package bside.palmtree.interfaces.graphql;

import org.springframework.stereotype.Service;

import bside.palmtree.domain.member.Member;
import bside.palmtree.interfaces.graphql.dto.MemberDto;
import bside.palmtree.service.MemberService;
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

	/**
	 *query{
	 *   getMember(name: "uni") {
	 *     email
	 *     name
	 *   }
	 * }
	 */
	@GraphQLQuery(name = "getMember")
	public MemberDto getMember(String name) {
		Member member = this.memberService.findByName(name);

		return MemberDto.builder()
			.email(member.getEmail())
			.name(member.getName())
			.build();
	}

	/**
	 * mutation{
	 *   putMember(memberDto:{email:"uni@gmail.com", name: "uni"})
	 * }
	 */
	@GraphQLMutation(name = "putMember")
	public Boolean putMember(MemberDto memberDto) {

		Member member = this.memberService.save(memberDto.getEmail(), memberDto.getName());

		return true;
	}

	/**
	 * query{
	 * 	delMember(name: "uni")
	 * }
	 */
	@GraphQLQuery(name = "delMember")
	public Boolean delMember(String name) {

		return this.memberService.deleteMember(name);
	}
}
