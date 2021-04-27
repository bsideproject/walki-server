package bside.palmtree.domain.membercoach;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import bside.palmtree.domain.coach.Coach;
import bside.palmtree.domain.common.BaseTimeEntity;
import bside.palmtree.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@ToString
@Entity
@Table(name = "member_coach")
public class MemberCoach extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long id;

	@OneToOne
	@JoinColumn(name = "member_id", nullable = false, columnDefinition = "INT(11) UNSIGNED")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "coach_id", nullable = false, columnDefinition = "INT(11) UNSIGNED")
	private Coach coach;

	@Builder
	public MemberCoach(Long id, Member member, Coach coach) {
		this.id = id;
		this.member = member;
		this.coach = coach;
	}

	public void update(Coach coach) {
		if (Objects.nonNull(coach)) {
			this.coach = coach;
		}
	}
}
