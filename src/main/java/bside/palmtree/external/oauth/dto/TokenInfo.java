package bside.palmtree.external.oauth.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by YHH on 2021/04/02
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TokenInfo {
	private String id;
	private Properties properties;

	@Builder
	public TokenInfo(String id, Properties properties) {
		this.id = id;
		this.properties = properties;
	}

	@NoArgsConstructor
	@Getter
	@Setter
	@ToString
	public static class Properties {
		@JsonProperty("nickname")
		private String nickname;

		@JsonProperty("thumbnail_image")
		private String thumbnailImage;

		@JsonProperty("profile_image")
		private String profileImage;

		@Builder
		public Properties(String nickname, String thumbnailImage, String profileImage) {
			this.nickname = nickname;
			this.thumbnailImage = thumbnailImage;
			this.profileImage = profileImage;
		}
	}

	public String getNickname() {
		if (Objects.isNull(this.properties)) {
			return null;
		}
		return this.properties.nickname;
	}

	public String getProfileImage() {
		if (Objects.isNull(this.properties)) {
			return null;
		}
		return this.properties.profileImage;
	}
}
