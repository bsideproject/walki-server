package bside.palmtree.interfaces.graphql.ranking;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bside.palmtree.config.LoggedIn;
import bside.palmtree.domain.membercoach.MemberCoach;
import bside.palmtree.interfaces.graphql.challenge.Challenge;
import bside.palmtree.interfaces.graphql.coach.Coach;
import bside.palmtree.interfaces.graphql.member.Member;
import bside.palmtree.service.membercoach.MemberCoachService;
import bside.palmtree.service.ranking.RankingService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2021/04/13
 */
@RequiredArgsConstructor
@Service
@GraphQLApi
public class RankingServiceFacade {
	private final RankingService rankingService;
	private final MemberCoachService memberCoachService;
	private final ModelMapper modelMapper;

	@GraphQLMutation(name = "createRankings")
	public Boolean createRankings(LocalDate date) {
		this.rankingService.createRankings(date);

		return true;
	}

	@GraphQLQuery(name = "getTop10Rankings")
	public List<Ranking> getTop10Rankings(
		@GraphQLRootContext @LoggedIn bside.palmtree.domain.member.Member member,
		LocalDate date) {

		return this.rankingService.findTop10ByDate(date).stream()
			.map(ranking -> Ranking.builder()
				.member(convertMember(ranking.getMember()))
				.number(ranking.getNumber())
				.challenge(this.modelMapper.map(ranking.getChallenge(), Challenge.class))
				.build())
			.collect(Collectors.toList());
	}

	@GraphQLQuery(name = "getMyRankings")
	public List<Ranking> getMyRankings(
		@GraphQLRootContext @LoggedIn bside.palmtree.domain.member.Member member,
		@NotNull LocalDate start,
		@NotNull LocalDate end) {

		return this.rankingService.findAllByMemberAndDate(member, start, end).stream()
			.map(ranking -> Ranking.builder()
				.member(convertMember(ranking.getMember()))
				.number(ranking.getNumber())
				.challenge(this.modelMapper.map(ranking.getChallenge(), Challenge.class))
				.build())
			.collect(Collectors.toList());
	}

	private Member convertMember(bside.palmtree.domain.member.Member member) {
		MemberCoach memberCoach = this.memberCoachService.findBy(member);

		Member memberDto = this.modelMapper.map(member, Member.class);

		if (Objects.nonNull(memberCoach)) {
			memberDto.setCoach(this.modelMapper.map(memberCoach.getCoach(), Coach.class));
		}

		return memberDto;
	}
}
