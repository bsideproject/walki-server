package bside.palmtree.interfaces.graphql.coach;

import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@GraphQLType(name = "Coach")
public class Coach {

	private Long coachId;
	private String name;
	private String description;

}
