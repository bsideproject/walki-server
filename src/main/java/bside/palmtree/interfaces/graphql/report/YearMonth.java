package bside.palmtree.interfaces.graphql.report;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by YHH on 2021/04/08
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class YearMonth {

	@NotNull
	private Integer year;
	@NotNull
	private Integer month;

	public java.time.YearMonth toNativeYearMonth() {
		return java.time.YearMonth.of(this.year, this.month);
	}
}
