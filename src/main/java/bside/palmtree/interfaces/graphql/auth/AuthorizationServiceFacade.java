package bside.palmtree.interfaces.graphql.auth;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import bside.palmtree.domain.member.Social;
import bside.palmtree.service.auth.AuthorizationService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by YHH on 2021/04/02
 */
@Slf4j
@RequiredArgsConstructor
@Service
@GraphQLApi
public class AuthorizationServiceFacade {
	private final AuthorizationService authorizationService;

	@GraphQLQuery(name = "signIn")
	public SignInResult signIn(@NotNull Social social, @NotNull String token) {

		return new SignInResult(this.authorizationService.signIn(social, token));
	}

	@GraphQLMutation(name = "signUp")
	public Boolean signUp(@NotNull Social social, @NotNull String token) {

		return this.authorizationService.signUp(social, token);
	}
}
