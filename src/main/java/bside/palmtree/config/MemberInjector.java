package bside.palmtree.config;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Parameter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import bside.palmtree.domain.member.Member;
import bside.palmtree.domain.member.MemberRepository;
import io.leangen.graphql.execution.ResolutionEnvironment;
import io.leangen.graphql.generator.mapping.ArgumentInjector;
import io.leangen.graphql.generator.mapping.ArgumentInjectorParams;
import io.leangen.graphql.spqr.spring.autoconfigure.DefaultGlobalContext;
import io.leangen.graphql.util.ContextUtils;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2021/04/03
 */
@RequiredArgsConstructor
@Component
public class MemberInjector implements ArgumentInjector {
	private static final String AUTH_PREFIX = "Bearer ";

	private final MemberRepository memberRepository;
	private final AuthorizationJwtProvider jwtProvider;

	@Override
	public Object getArgumentValue(ArgumentInjectorParams params) {
		ResolutionEnvironment env = params.getResolutionEnvironment();

		@SuppressWarnings("unchecked")
		DefaultGlobalContext<ServletWebRequest> rootContext =
			(DefaultGlobalContext<ServletWebRequest>)ContextUtils.unwrapContext(env.rootContext);

		String jwt = getJwtByHeader(rootContext);

		return this.memberRepository.findById(this.jwtProvider.getMemberId(jwt))
			.filter(Member::isActive)
			.orElseThrow(() -> new AuthorizationException("유저 조회 실패"));
	}

	private String getJwtByHeader(DefaultGlobalContext<ServletWebRequest> rootContext) {
		// Bearer ${jwt}
		String authorizationValue = rootContext.getNativeRequest().getHeader(HttpHeaders.AUTHORIZATION);
		if (authorizationValue == null || authorizationValue.isEmpty()) {
			throw new AuthorizationException("접근 권한 없음");
		}

		if (authorizationValue.startsWith(AUTH_PREFIX)) {
			return authorizationValue.substring(AUTH_PREFIX.length());
		}

		throw new AuthorizationException("접근 권한 없음");
	}

	@Override
	public boolean supports(AnnotatedType type, Parameter parameter) {
		return parameter != null && parameter.isAnnotationPresent(LoggedIn.class);
	}
}
