package bside.palmtree.interfaces.graphql.member;

import java.time.LocalDateTime;

import bside.palmtree.domain.member.Social;
import io.leangen.graphql.annotations.GraphQLIgnore;
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
@GraphQLType(name = "Member", fieldOrder = {"id", "social", "socialId", "name", "profileImage", "createdAt",
	"updatedAt"})
public class Member {
	private Long id;
	private Social social;
	private String socialId;
	private String name;
	private String profileImage;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@GraphQLIgnore
	public void setId(Long id) {
		this.id = id;
	}

	@GraphQLIgnore
	public void setSocial(Social social) {
		this.social = social;
	}

	@GraphQLIgnore
	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	@GraphQLIgnore
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	@GraphQLIgnore
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@GraphQLIgnore
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
