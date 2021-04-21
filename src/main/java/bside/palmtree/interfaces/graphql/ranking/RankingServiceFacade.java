package bside.palmtree.interfaces.graphql.ranking;

import bside.palmtree.config.LoggedIn;
import bside.palmtree.interfaces.graphql.member.Member;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bside.palmtree.service.ranking.RankingService;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by YHH on 2021/04/13
 */
@RequiredArgsConstructor
@Service
@GraphQLApi
public class RankingServiceFacade {
	private final RankingService rankingService;
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
				.member(this.modelMapper.map(ranking.getMember(), Member.class))
				.number(ranking.getNumber())
				.challengeDate(ranking.getChallengeDate())
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
				.member(this.modelMapper.map(ranking.getMember(), Member.class))
				.number(ranking.getNumber())
				.challengeDate(ranking.getChallengeDate())
				.build())
			.collect(Collectors.toList());
	}
}
