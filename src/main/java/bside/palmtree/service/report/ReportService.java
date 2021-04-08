package bside.palmtree.service.report;

import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Service;

import bside.palmtree.domain.challenge.Challenge;
import bside.palmtree.domain.challenge.ChallengeRepository;
import bside.palmtree.domain.challenge.report.Report;
import bside.palmtree.domain.member.Member;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2021/04/08
 */
@RequiredArgsConstructor
@Service
public class ReportService {
	private final ChallengeRepository challengeRepository;

	public Report findReportByYearMonth(Member member, YearMonth yearMonth) {

		List<Challenge> challenges = this.challengeRepository.findAllByMemberIdAndChallengeDateBetween(
			member.getId(),
			yearMonth.atDay(1),
			yearMonth.atEndOfMonth());

		return Report.from(challenges);
	}
}
