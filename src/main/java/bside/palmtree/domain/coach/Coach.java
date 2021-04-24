package bside.palmtree.domain.coach;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import bside.palmtree.domain.common.BaseTimeEntity;
import bside.palmtree.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Coach extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coach_id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name="description", columnDefinition = "TEXT")
	private String description;

	@OneToMany
	@JoinTable(name = "member_coach",
			   joinColumns = @JoinColumn(name = "coach_id"),
			   inverseJoinColumns = @JoinColumn(name = "member_id"))
	private Set<Member> members = new HashSet<>();
	// private List<Member> members = new ArrayList<>();
}
