package bside.palmtree.interfaces.graphql.memberCoach;

import java.time.LocalDateTime;

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
@GraphQLType(name = "MemberCoach", fieldOrder = {"id", "memberId", "coachId", "createdAt", "updatedAt"})
public class MemberCoach {

	private Long id;
	private Long memberId;
	private Long coachId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@GraphQLIgnore
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@GraphQLIgnore
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
