package bside.palmtree.interfaces.graphql.common;

import static org.springframework.http.HttpHeaders.*;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.jayway.jsonpath.JsonPath;

import reactor.core.publisher.Mono;

/**
 * Created by YHH on 2021/04/05
 */
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {
	@Autowired
	protected WebTestClient webTestClient;

	@LocalServerPort
	protected int port;

	protected String accessToken;

	@BeforeEach
	protected void setUp() throws Exception {
		this.webTestClient = WebTestClient.bindToServer()
			.baseUrl("http://localhost:" + this.port)
			.defaultHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();

		String signUpQuery = "mutation {"
			+ "  signUp(social: KAKAO, token: \"0\")"
			+ "}";
		this.webTestClient
			.post()
			.uri("/graphql")
			.body(Mono.just(generateRequest(signUpQuery)), String.class)
			.exchange()
			.expectStatus()
			.isOk()
			.returnResult(String.class)
			.getResponseBody()
			.blockFirst();

		String signInQuery = "query {\n"
			+ "  signIn(social: KAKAO, token: \"0\") {\n"
			+ "    accessToken\n"
			+ "  }\n"
			+ "}";
		String signInResultJson = this.webTestClient
			.post()
			.uri("/graphql")
			.body(Mono.just(generateRequest(signInQuery)), String.class)
			.exchange()
			.expectStatus()
			.isOk()
			.returnResult(String.class)
			.getResponseBody()
			.blockFirst();
		System.out.println(signInResultJson);
		this.accessToken = "Bearer " + JsonPath.parse(signInResultJson).read("$.data.signIn.accessToken");
	}

	protected String generateRequest(String query) throws JSONException {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("query", query);

		return jsonObject.toString();
	}
}
