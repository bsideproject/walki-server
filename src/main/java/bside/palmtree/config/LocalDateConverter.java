package bside.palmtree.config;

import java.time.LocalDate;
import java.util.Date;

import org.modelmapper.AbstractConverter;

/**
 * Created by YHH modelMapper 에서 LocalDateTime 필드를 변환하는 방법 설정
 */
public class LocalDateConverter extends AbstractConverter<LocalDate, Date> {

	@Override
	protected java.sql.Date convert(LocalDate localDate) {
		return java.sql.Date.valueOf(localDate);
	}
}
