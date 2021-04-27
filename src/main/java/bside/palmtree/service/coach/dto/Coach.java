package bside.palmtree.service.coach.dto;

import java.time.LocalDateTime;

import io.leangen.graphql.annotations.GraphQLIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Coach {
	private Long coachId;
	private String name;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// @GraphQLIgnore
	// public void setCreatedAt(LocalDateTime createdAt) {
	// 	this.createdAt = createdAt;
	// }
	//
	// @GraphQLIgnore
	// public void setUpdatedAt(LocalDateTime updatedAt) {
	// 	this.updatedAt = updatedAt;
	// }
}
