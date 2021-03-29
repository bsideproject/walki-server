package bside.palmtree.interfaces.graphql.member;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import io.leangen.graphql.annotations.GraphQLIgnore;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by YHH on 2021/02/15
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@GraphQLType(name = "Member", fieldOrder = {"name", "createdAt", "updatedAt"})
public class Member {
	@NotNull
	@GraphQLQuery(name = "name")
	protected String name;
	@GraphQLQuery(name = "createdAt")
	protected LocalDateTime createdAt;
	@GraphQLQuery(name = "updatedAt")
	protected LocalDateTime updatedAt;

	@GraphQLIgnore
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@GraphQLIgnore
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
