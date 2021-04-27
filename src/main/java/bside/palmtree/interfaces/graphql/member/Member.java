package bside.palmtree.interfaces.graphql.member;

import java.time.LocalDateTime;
import java.util.Objects;

import bside.palmtree.domain.member.Social;
import bside.palmtree.interfaces.graphql.coach.Coach;
import io.leangen.graphql.annotations.GraphQLIgnore;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by YHH on 2021/02/15
 */
@NoArgsConstructor
@ToString
@GraphQLType(name = "Member",
	fieldOrder = {"id", "social", "socialId", "name", "profileImage", "coach", "createdAt", "updatedAt"},
	inputFieldOrder = {"name", "coachId"})
public class Member {
	private Long id;
	private Social social;
	private String socialId;
	private String name;
	private String profileImage;
	private Coach coach;
	protected Long coachId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Long getId() {
		return this.id;
	}

	@GraphQLIgnore
	public void setId(Long id) {
		this.id = id;
	}

	public Social getSocial() {
		return this.social;
	}

	@GraphQLIgnore
	public void setSocial(Social social) {
		this.social = social;
	}

	public String getSocialId() {
		return this.socialId;
	}

	@GraphQLIgnore
	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileImage() {
		return this.profileImage;
	}

	@GraphQLIgnore
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Coach getCoach() {
		return this.coach;
	}

	@GraphQLIgnore
	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}

	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}

	@GraphQLIgnore
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return this.updatedAt;
	}

	@GraphQLIgnore
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean hasCoachId() {
		return Objects.nonNull(this.coachId);
	}
}
