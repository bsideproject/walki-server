package bside.palmtree.interfaces.graphql.auth;

import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@GraphQLType(name = "AccessToken",
	fieldOrder = {"isNew", "accessToken"})
public class AccessToken {
	private Boolean isNew;
	private String accessToken;

	@Builder
	public AccessToken(Boolean isNew, String accessToken) {
		this.isNew = isNew;
		this.accessToken = accessToken;
	}
}
