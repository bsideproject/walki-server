package bside.palmtree.service.auth;

import org.springframework.stereotype.Service;

import bside.palmtree.config.AuthorizationJwtProvider;
import bside.palmtree.domain.member.Member;
import bside.palmtree.domain.member.MemberRepository;
import bside.palmtree.domain.member.Social;
import bside.palmtree.external.OAuthClient;
import bside.palmtree.external.TokenInfo;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2021/04/02
 */
@RequiredArgsConstructor
@Service
public class AuthorizationService {
	private final AuthorizationJwtProvider authorizationJwtProvider;
	private final MemberRepository memberRepository;
	private final OAuthClient oAuthClient;

	public String signIn(Social social, String token) {
		// TODO : SNS 유저 정보 확인 API 구현 필요
		TokenInfo tokenInfo = this.oAuthClient.getTokenInfo(social, token);

		Member member = this.memberRepository.findBySocialAndSocialId(social, tokenInfo.getId())
			.orElseThrow(() -> new RuntimeException("유저 정보 조회 실패"));

		return this.authorizationJwtProvider.createToken(member, null);
	}

	public Boolean signUp(Social social, String token) {
		// TODO : SNS 유저 정보 확인 API 구현 필요
		TokenInfo tokenInfo = this.oAuthClient.getTokenInfo(social, token);

		if (this.memberRepository.findBySocialAndSocialId(social, tokenInfo.getId()).isPresent()) {
			throw new RuntimeException("이미 가입된 유저");
		}

		Member member = Member.builder()
			.social(social)
			.socialId(tokenInfo.getId())
			.build();

		this.memberRepository.save(member);

		return true;
	}
}
