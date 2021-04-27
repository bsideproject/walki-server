package bside.palmtree.interfaces.graphql.coach;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import bside.palmtree.interfaces.graphql.common.BaseTest;
import reactor.core.publisher.Mono;

class CoachServiceFacadeTest extends BaseTest {

	@Test
	public void successGetCoachList() throws Exception {
		String getCoachListQuery = "query {"
			+ "  getCoaches {"
			+ "    id"
			+ "    description"
			+ "    name"
			+ "  }"
			+ "}";

		this.webTestClient
			.post()
			.uri("/graphql")
			.header(HttpHeaders.AUTHORIZATION, this.accessToken)
			.body(Mono.just(generateRequest(getCoachListQuery)), String.class)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.data.getCoaches").isArray();

	}
}
