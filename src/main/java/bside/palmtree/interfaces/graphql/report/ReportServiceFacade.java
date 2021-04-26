package bside.palmtree.interfaces.graphql.report;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bside.palmtree.config.LoggedIn;
import bside.palmtree.domain.member.Member;
import bside.palmtree.service.report.ReportService;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2021/02/15
 */
@RequiredArgsConstructor
@Service
@GraphQLApi
public class ReportServiceFacade {
	private final ReportService reportService;
	private final ModelMapper modelMapper;

	@GraphQLQuery(name = "getReport")
	public Report getReport(@GraphQLRootContext @LoggedIn Member member, @NotNull YearMonth yearMonth) {

		return this.modelMapper.map(
			this.reportService.findReportByYearMonth(member, yearMonth.toNativeYearMonth()),
			Report.class
		);
	}
}
