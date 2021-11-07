package bside.palmtree.domain.challenge.report;

import java.time.YearMonth;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import bside.palmtree.domain.challenge.Challenge;
import lombok.Getter;

@Getter
public class ReportMonth {
	private final List<YearMonth> yearMonthList;

	private ReportMonth(Set<YearMonth> yearMonthList) {
		this.yearMonthList = yearMonthList.stream()
			.sorted(YearMonth::compareTo)
			.collect(Collectors.toList());
	}

	public static ReportMonth from(List<Challenge> challenges) {
		Set<YearMonth> yearMonths = challenges.stream()
			.map(it -> YearMonth.from(it.getChallengeDate()))
			.collect(Collectors.toSet());

		return new ReportMonth(yearMonths);
	}
}
