package com.zhuoxun.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 字符串类型转换成java.util.Date类型的类型转换器实现类
 * 
 * @author lzx
 */
@Component
public class String2DateConverter implements Converter<String, Date> {
	public static final String DATE_PATTERN_LONG = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN_2 = "yyyy-MM-dd HH:mm";
	public static final String DATE_PATTERN = "yyyy-MM-dd";

	@Override
	public Date convert(String src) {
		if (StringUtils.hasText(src)) {
			DateFormat df = DateFormat.getDateTimeInstance();

			src = src.trim();
			if (src.length() == DATE_PATTERN_LONG.length()) {
				df = new SimpleDateFormat(DATE_PATTERN_LONG);
			} else if (src.length() == DATE_PATTERN_2.length()) {
				df = new SimpleDateFormat(DATE_PATTERN_2);
			} else if (src.length() == DATE_PATTERN.length()) {
				df = new SimpleDateFormat(DATE_PATTERN);
			}

			Date date = null;

			try {
				date = df.parse(src);
			} catch (ParseException e) {
				throw new IllegalArgumentException("字符串数据转换成java.util.Date类型的数据时出现异常", e);
			}

			return date;
		}
		return null;
	}

}
