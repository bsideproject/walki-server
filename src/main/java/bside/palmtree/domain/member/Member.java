package bside.palmtree.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Member extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Builder
	public Member(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
