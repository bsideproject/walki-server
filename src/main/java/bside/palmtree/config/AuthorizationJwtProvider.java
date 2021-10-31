package bside.palmtree.config;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import bside.palmtree.domain.member.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2020/12/12
 */
@RequiredArgsConstructor
@Component
public class AuthorizationJwtProvider {

	private final String secretKey = "5bec3675-17a8-4a6d-a865-a4c75c799543";

	// JWT 토큰 생성
	public String createToken(Member member, Map<String, Object> data) {
		Date now = new Date();

		long tokenValidTime = 30 * 24 * 60 * 60 * 1000L;

		return Jwts.builder()
			.setSubject(member.getId().toString())
			.addClaims(data)
			.setIssuedAt(now) // 토큰 발행 시간 정보
			.setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}

	// 토큰에서 회원 정보 추출
	public Long getMemberId(String token) {
		try {
			String memberId = Jwts.parser().setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();

			return Long.parseLong(memberId);
		} catch (Exception exception) {
			throw new AuthorizationException("jwt parse error");
		}
	}

	// 토큰의 유효성 + 만료일자 확인
	public boolean validateToken(String jwtToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}
}
