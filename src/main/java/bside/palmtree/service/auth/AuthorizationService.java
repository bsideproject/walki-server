package bside.palmtree.service.auth;

import org.springframework.stereotype.Service;

import bside.palmtree.config.AuthorizationJwtProvider;
import bside.palmtree.domain.member.Member;
import bside.palmtree.domain.member.MemberRepository;
import bside.palmtree.domain.member.Social;
import bside.palmtree.external.OAuthService;
import bside.palmtree.external.TokenInfo;
import bside.palmtree.service.member.MemberService;
import bside.palmtree.service.member.dto.MemberDetailDto;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2021/04/02
 */
@RequiredArgsConstructor
@Service
public class AuthorizationService {
	private final AuthorizationJwtProvider authorizationJwtProvider;
	private final MemberRepository memberRepository;
	private final MemberService memberService;
	private final OAuthService oAuthService;

	public String signIn(Social social, String token) {
		// TODO : SNS 유저 정보 확인 APPLE API 구현 필요
		TokenInfo tokenInfo = this.oAuthService.getTokenInfo(social, token);

		Member member = this.memberRepository.findBySocialAndSocialId(social, tokenInfo.getId())
			.orElseThrow(() -> new RuntimeException("유저 정보 조회 실패"));

		// 로그인 시 유저 프로필 업데이트
		MemberDetailDto memberDetailDto = MemberDetailDto.builder()
			.profileImage(tokenInfo.getProfileImage())
			.build();
		this.memberService.save(member, memberDetailDto);

		return this.authorizationJwtProvider.createToken(member, null);
	}

	public Boolean signUp(Social social, String token) {
		// TODO : SNS 유저 정보 확인 APPLE API 구현 필요
		TokenInfo tokenInfo = this.oAuthService.getTokenInfo(social, token);

		if (this.memberRepository.findBySocialAndSocialId(social, tokenInfo.getId()).isPresent()) {
			throw new RuntimeException("이미 가입된 유저");
		}

		Member member = Member.builder()
			.social(social)
			.socialId(tokenInfo.getId())
			.name(tokenInfo.getNickname())
			.profileImage(tokenInfo.getProfileImage())
			.build();

		this.memberRepository.save(member);

		return true;
	}
}
