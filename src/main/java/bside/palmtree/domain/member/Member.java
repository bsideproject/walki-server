package bside.palmtree.domain.member;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.logging.log4j.util.Strings;

import bside.palmtree.domain.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by YHH on 2021/02/14
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"social", "social_id"}))
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id", nullable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long id;

	@Column(name = "social", nullable = false)
	@Convert(converter = SocialTypeConverter.class)
	private Social social;

	@Column(name = "social_id", nullable = false)
	private String socialId;

	@Column(name = "name")
	private String name;

	@Column(name = "profile_image", length = 1000)
	private String profileImage;

	@Builder
	public Member(Long id, Social social, String socialId, String name, String profileImage) {
		this.id = id;
		this.social = social;
		this.socialId = socialId;
		this.name = name;
		this.profileImage = profileImage;
	}

	public void update(MemberDetail memberDetail) {
		if (Strings.isNotEmpty(memberDetail.getName())) {
			this.name = memberDetail.getName();
		}

		if (Strings.isNotEmpty(memberDetail.getProfileImage())) {
			this.profileImage = memberDetail.getProfileImage();
		}
	}
}
