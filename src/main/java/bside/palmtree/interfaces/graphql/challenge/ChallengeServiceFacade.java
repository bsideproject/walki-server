package bside.palmtree.interfaces.graphql.challenge;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

import bside.palmtree.config.LoggedIn;
import bside.palmtree.domain.member.Member;
import bside.palmtree.service.challenge.ChallengeService;
import bside.palmtree.service.challenge.dto.ChallengeDto;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by YHH on 2021/02/15
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@GraphQLApi
public class ChallengeServiceFacade {
	private final ChallengeService challengeService;
	private final ModelMapper modelMapper;

	@GraphQLQuery(name = "getChallenges")
	public List<Challenge> getChallenges(@GraphQLRootContext @LoggedIn Member member) {

		return this.challengeService.findAll(member).stream()
			.map(challenge -> this.modelMapper.map(challenge, Challenge.class))
			.collect(Collectors.toList());
	}

	@GraphQLQuery(name = "getChallenge")
	public Challenge getChallenge(@GraphQLRootContext @LoggedIn Member member, LocalDate challengeDate) {

		return this.modelMapper.map(
			this.challengeService.find(challengeDate, member),
			Challenge.class);
	}

	@GraphQLMutation(name = "putChallenge")
	public Challenge putChallenge(@GraphQLRootContext @LoggedIn Member member, Challenge challenge) {
		log.info("putChallenge : {}", challenge);

		ChallengeDto challengeDto = this.modelMapper.map(challenge, ChallengeDto.class);

		return this.modelMapper.map(
			this.challengeService.save(challengeDto, member),
			Challenge.class);
	}
}
