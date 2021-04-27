package bside.palmtree.interfaces.graphql.coach;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import bside.palmtree.interfaces.graphql.common.BaseTest;
import reactor.core.publisher.Mono;

class CoachDtoServiceFacadeTest extends BaseTest {

	@Test
	public void successGetCoachList() throws Exception {
		String getCoachListQuery = "query {"
			+ "  getCoachList {"
			+ "    coachId"
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
			.jsonPath("$.data.getCoachList").isArray();

	}
}
