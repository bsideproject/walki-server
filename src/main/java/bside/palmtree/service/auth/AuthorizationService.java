package bside.palmtree.service.auth;

import java.util.Optional;

import org.springframework.stereotype.Service;

import bside.palmtree.config.AuthorizationJwtProvider;
import bside.palmtree.config.LoggingParam;
import bside.palmtree.domain.member.Member;
import bside.palmtree.domain.member.MemberRepository;
import bside.palmtree.domain.member.Social;
import bside.palmtree.external.oauth.OAuthService;
import bside.palmtree.external.oauth.dto.TokenInfo;
import bside.palmtree.service.auth.dto.AccessToken;
import bside.palmtree.service.member.MemberService;
import bside.palmtree.service.member.dto.MemberDetailDto;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2021/04/02
 */
@LoggingParam
@RequiredArgsConstructor
@Service
public class AuthorizationService {
	private final AuthorizationJwtProvider authorizationJwtProvider;
	private final MemberRepository memberRepository;
	private final MemberService memberService;
	private final OAuthService oAuthService;

	public String signIn(Social social, String token) {
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

	public String refreshToken(Member member) {

		return this.authorizationJwtProvider.createToken(member, null);
	}

	public AccessToken getAccessToken(Social social, String token) {
		TokenInfo tokenInfo = this.oAuthService.getTokenInfo(social, token);

		Optional<Member> maybeMember = this.memberRepository.findBySocialAndSocialId(social, tokenInfo.getId());
		Member member = getMember(social, tokenInfo, maybeMember);

		String accessToken = this.authorizationJwtProvider.createToken(member, null);

		return AccessToken.builder()
			.isNew(maybeMember.isEmpty())
			.accessToken(accessToken)
			.build();
	}

	private Member getMember(Social social, TokenInfo tokenInfo, Optional<Member> maybeMember) {
		if (maybeMember.isEmpty()) {
			Member member = Member.builder()
				.social(social)
				.socialId(tokenInfo.getId())
				.name(tokenInfo.getNickname())
				.profileImage(tokenInfo.getProfileImage())
				.build();

			return this.memberRepository.save(member);
		} else {
			// 로그인 시 유저 프로필 업데이트
			MemberDetailDto memberDetailDto = MemberDetailDto.builder()
				.profileImage(tokenInfo.getProfileImage())
				.build();

			return this.memberService.save(maybeMember.get(), memberDetailDto);
		}
	}
}
