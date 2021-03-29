package bside.palmtree.service.challenge;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import bside.palmtree.domain.challenge.Challenge;
import bside.palmtree.domain.challenge.ChallengeRepository;
import bside.palmtree.domain.member.Member;
import bside.palmtree.service.challenge.dto.ChallengeDto;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2021/03/22
 */
@RequiredArgsConstructor
@Service
public class ChallengeService {
	private final ChallengeRepository challengeRepository;

	@Transactional
	public Challenge save(ChallengeDto challengeDto, Member member) {
		Optional<Challenge> maybeChallenge = this.challengeRepository
			.findByMemberIdAndChallengeDate(member.getId(), challengeDto.getChallengeDate());

		if (maybeChallenge.isPresent()) {
			Challenge challenge = maybeChallenge.get();
			challenge.update(challengeDto.getStep(), challengeDto.getStepGoal());

			return challenge;
		}

		Challenge challenge = Challenge.builder()
			.challengeDate(challengeDto.getChallengeDate())
			.stepGoal(challengeDto.getStepGoal())
			.member(member)
			.build();

		return this.challengeRepository.save(challenge);
	}

	public Challenge find(LocalDate challengeDate, Member member) {
		return this.challengeRepository.findByMemberIdAndChallengeDate(member.getId(), challengeDate)
			.orElseThrow();
	}

	public List<Challenge> findAll(Member member) {
		return this.challengeRepository.findAllByMemberIdOrderByChallengeDateDesc(member.getId());
	}
}
