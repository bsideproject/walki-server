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
	public void saveMember() throws Exception {
		String putMemberQuery = "mutation {\n"
			+ "  putMember(member:{name: \"유니\"}) {\n"
			+ "    name"
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
			.jsonPath("$.data.putMember.name").isEqualTo("유니");

		String getMemberQuery = "query {"
			+ "  getMember {"
			+ "    name"
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
			.jsonPath("$.data.getMember.name").isEqualTo("유니");
	}
}
