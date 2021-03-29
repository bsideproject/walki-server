package bside.palmtree.interfaces.graphql.challenge;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.leangen.graphql.annotations.GraphQLIgnore;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLScalar;
import io.leangen.graphql.annotations.types.GraphQLInterface;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by YHH on 2021/03/22
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@GraphQLType(name = "Challenge", fieldOrder = {"challengeDate", "step", "stepGoal", "createdAt", "updatedAt"})
public class Challenge {
	@NotNull
	private LocalDate challengeDate;
	private Integer step;
	private Integer stepGoal;
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
