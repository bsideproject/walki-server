package bside.palmtree.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import bside.palmtree.domain.coach.Coach;
import bside.palmtree.domain.common.BaseTimeEntity;
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

	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false, columnDefinition = "INT(11) UNSIGNED")
	private Member memberId;

	@ManyToOne
	@JoinColumn(name = "coach_id", nullable = false, columnDefinition = "INT(11) UNSIGNED")
	private Coach coachId;

	@Builder
	public MemberCoach(Long id, Member memberId, Coach coachId) {
		this.id = id;
		this.memberId = memberId;
		this.coachId = coachId;
	}
}
