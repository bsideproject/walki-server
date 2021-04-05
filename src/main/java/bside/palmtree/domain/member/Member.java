package bside.palmtree.domain.member;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
	@Column(name = "member_id", nullable = false)
	private Long id;

	@Column(name = "social", nullable = false)
	@Convert(converter = SocialTypeConverter.class)
	private Social social;

	@Column(name = "social_id", nullable = false)
	private String socialId;

	@Builder
	public Member(Social social, String socialId) {
		this.social = social;
		this.socialId = socialId;
	}
}
