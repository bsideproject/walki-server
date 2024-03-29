package bside.palmtree.service.coach;

import java.util.List;

import org.springframework.stereotype.Service;

import bside.palmtree.config.LoggingParam;
import bside.palmtree.domain.coach.Coach;
import bside.palmtree.domain.coach.CoachRepository;
import lombok.RequiredArgsConstructor;

@LoggingParam
@RequiredArgsConstructor
@Service
public class CoachService {

	private final CoachRepository coachRepository;

	public List<Coach> findAll() {
		return this.coachRepository.findAll();
	}
}
