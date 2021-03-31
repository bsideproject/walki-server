package bside.palmtree.interfaces.graphql.member;

import static org.springframework.http.HttpHeaders.*;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberServiceFacadeTest {
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
	public void crdMember() throws Exception {
		String putMemberQuery = "mutation {"
			+ "  putMember(member:{name: \"uni\"}) {"
			+ "    name"
			+ "  }"
			+ "}";

		this.webTestClient
			.post()
			.uri("/graphql")
			.body(Mono.just(generateRequest(putMemberQuery)), String.class)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.data.putMember.name").isEqualTo("uni");

		String getMemberQuery = "query {"
			+ "  getMember(name: \"uni\") {"
			+ "    name,"
			+ "    createdAt,"
			+ "    updatedAt,"
			+ "  }"
			+ "}";

		this.webTestClient
			.post()
			.uri("/graphql")
			.body(Mono.just(generateRequest(getMemberQuery)), String.class)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.data.getMember.name").isEqualTo("uni");

		String deleteMemberQuery = "mutation {"
			+ "  deleteMember(name: \"uni\")"
			+ "}";

		this.webTestClient
			.post()
			.uri("/graphql")
			.body(Mono.just(generateRequest(deleteMemberQuery)), String.class)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.data.deleteMember").isEqualTo(true);
	}

	private String generateRequest(String query) throws JSONException {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("query", query);

		return jsonObject.toString();
	}
}
