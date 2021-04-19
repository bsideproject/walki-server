package bside.palmtree.domain.coach;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Coach {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coach_id", nullable = false)
	private Long coachId;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name="description", columnDefinition = "TEXT")
	private String description;

}
