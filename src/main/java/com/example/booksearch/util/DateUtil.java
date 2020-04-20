package com.example.booksearch.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateUtil {

	/**
	 * now 에서 입력 받은 days 만큼 일수를 더함.
	 * @param days
	 * @return
	 */
	public static Date nowAfterDaysToDate(Long days) {
		return Date.from(LocalDateTime.now().plusDays(days).toInstant(ZoneOffset.ofHours(9)));
	}
}
