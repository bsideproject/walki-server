package bside.palmtree.domain.member;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by YHH on 2020/11/30
 */
@Slf4j
@Converter
public class SocialTypeConverter implements AttributeConverter<Social, String> {

	@Override
	public String convertToDatabaseColumn(Social attribute) {
		if (Objects.isNull(attribute)) {
			return null;
		}

		return attribute.name();
	}

	@Override
	public Social convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}

		try {
			return Social.valueOf(dbData);
		} catch (IllegalArgumentException e) {
			log.error("failure to convert cause unexpected code [{}]", dbData, e);
			throw e;
		}
	}
}
