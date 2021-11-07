package bside.palmtree.interfaces.graphql.report;

import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@GraphQLType(name = "ReportMonth", fieldOrder = {"yearMonthList"})
public class ReportMonth {
	private List<String> yearMonthList;

	private ReportMonth(List<String> yearMonthList) {
		this.yearMonthList = yearMonthList;
	}

	public static ReportMonth from(List<YearMonth> yearMonths) {
		return new ReportMonth(
			yearMonths.stream()
				.map(YearMonth::toString)
				.collect(Collectors.toList())
		);
	}
}
