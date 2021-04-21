package bside.palmtree.interfaces.graphql.member;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import bside.palmtree.interfaces.graphql.common.BaseTest;
import reactor.core.publisher.Mono;

/**
 * Created by YHH on 2021/03/25
 */
class MemberServiceFacadeTest extends BaseTest {

	@Test
	public void saveMemberDetail() throws Exception {
		String putMemberQuery = "mutation {\n"
			+ "  putMemberDetail(memberDetail:{name: \"유니\", challenge_goal: 20}) {\n"
			+ "    name"
			+ "    challenge_goal"
			+ "  }\n"
			+ "}";

		this.webTestClient
			.post()
			.uri("/graphql")
			.header(HttpHeaders.AUTHORIZATION, this.accessToken)
			.body(Mono.just(generateRequest(putMemberQuery)), String.class)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.data.putMemberDetail.name").isEqualTo("유니");

		String getMemberQuery = "query {"
			+ "  getMemberDetail {"
			+ "    name"
			+ "    challenge_goal"
			+ "  }"
			+ "}";

		this.webTestClient
			.post()
			.uri("/graphql")
			.header(HttpHeaders.AUTHORIZATION, this.accessToken)
			.body(Mono.just(generateRequest(getMemberQuery)), String.class)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.data.getMemberDetail.name").isEqualTo("유니");
	}
}
