package bside.palmtree.service.challenge.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by YHH on 2021/03/22
 */
@Getter
@Setter
@ToString
public class ChallengeDto {
	private LocalDate challengeDate;
	private Integer step;
	private Integer stepGoal;
}
