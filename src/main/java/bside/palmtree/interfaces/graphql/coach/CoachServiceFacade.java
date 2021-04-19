package bside.palmtree.interfaces.graphql.coach;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

import bside.palmtree.config.LoggedIn;
import bside.palmtree.domain.member.Member;
import bside.palmtree.service.coach.CoachService;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@GraphQLApi
public class CoachServiceFacade {
	private final CoachService coachService;
	private final ModelMapper modelMapper;

	@GraphQLQuery(name = "getCoachList")
	public List<Coach> getCoachList(@GraphQLRootContext @LoggedIn Member member) {

		return this.coachService.findAll().stream()
			.map(coach -> this.modelMapper.map(coach, Coach.class))
			.collect(Collectors.toList());
	}
}
