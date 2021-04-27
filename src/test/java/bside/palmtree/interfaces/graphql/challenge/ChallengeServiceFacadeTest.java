package bside.palmtree.interfaces.graphql.challenge;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpHeaders;

import bside.palmtree.interfaces.graphql.common.BaseTest;
import reactor.core.publisher.Mono;

/**
 * Created by YHH on 2021/03/25
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChallengeServiceFacadeTest extends BaseTest {

	@Test
	@Order(1)
	public void crChallenges() throws Exception {
		String putChallengeQuery = "mutation {"
			+ "  putChallenge(challenge:{"
			+ "    stepGoal: 211,"
			+ "    step: 0,"
			+ "    challengeDate: \"2021-01-01\""
			+ "  }) {"
			+ "    stepGoal,"
			+ "    step,"
			+ "    challengeDate,"
			+ "    createdAt,"
			+ "    updatedAt"
			+ "  }"
			+ "}";

		this.webTestClient
			.post()
			.uri("/graphql")
			.header(HttpHeaders.AUTHORIZATION, this.accessToken)
			.body(Mono.just(generateRequest(putChallengeQuery)), String.class)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.data.putChallenge.challengeDate").isEqualTo("2021-01-01");

		String getChallengeQuery = "query {"
			+ "  getChallenge(challengeDate: \"2021-01-01\") {"
			+ "    challengeDate,"
			+ "    stepGoal,"
			+ "    step,"
			+ "    createdAt,"
			+ "    updatedAt,"
			+ "  }"
			+ "}";

		this.webTestClient
			.post()
			.uri("/graphql")
			.header(HttpHeaders.AUTHORIZATION, this.accessToken)
			.body(Mono.just(generateRequest(getChallengeQuery)), String.class)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.data.getChallenge.challengeDate").isEqualTo("2021-01-01");

		String getChallengesQuery = "query {"
			+ "  getChallenges {"
			+ "    challengeDate"
			+ "    stepGoal"
			+ "    step"
			+ "    createdAt"
			+ "    updatedAt"
			+ "  }"
			+ "}";

		this.webTestClient
			.post()
			.uri("/graphql")
			.header(HttpHeaders.AUTHORIZATION, this.accessToken)
			.body(Mono.just(generateRequest(getChallengesQuery)), String.class)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.data.getChallenges").isArray();
	}
}
