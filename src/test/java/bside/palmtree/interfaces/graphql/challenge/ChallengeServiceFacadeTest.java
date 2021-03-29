package bside.palmtree.interfaces.graphql.challenge;

import static org.springframework.http.HttpHeaders.*;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;

/**
 * Created by YHH on 2021/03/25
 */
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChallengeServiceFacadeTest {

	@Autowired
	private WebTestClient webTestClient;

	@LocalServerPort
	private int port;

	@BeforeEach
	protected void setUp() {
		this.webTestClient = WebTestClient.bindToServer()
			.baseUrl("http://localhost:" + this.port)
			.defaultHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}

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
			.body(Mono.just(generateRequest(getChallengeQuery)), String.class)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.data.getChallenge.challengeDate").isEqualTo("2021-01-01");

		String getChallengesQuery = "query {"
			+ "  getChallenges(jwt: \"\") {"
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
			.body(Mono.just(generateRequest(getChallengesQuery)), String.class)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.data.getChallenges").isArray();
	}

	private String generateRequest(String query) throws JSONException {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("query", query);

		return jsonObject.toString();
	}
}
