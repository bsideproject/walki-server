package bside.palmtree.interfaces.graphql.dto;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by YHH on 2021/02/15
 */
@Data
@NoArgsConstructor
@ToString
public class MemberDto {
	@GraphQLQuery(name = "email")
	private String email;
	@GraphQLQuery(name = "name")
	private String name;

	@Builder
	public MemberDto(String email, String name) {
		this.email = email;
		this.name = name;
	}
}
