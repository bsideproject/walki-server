package bside.palmtree.interfaces.graphql.coach;

import io.leangen.graphql.annotations.GraphQLIgnore;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@GraphQLType(name = "Coach", fieldOrder = {"id", "name", "description"})
public class Coach {

	private Long id;
	private String name;
	private String description;
}
