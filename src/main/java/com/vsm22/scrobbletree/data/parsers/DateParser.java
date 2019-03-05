package com.vsm22.scrobbletree.data.parsers;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParser {
	public static LocalDate parse(String dateString) {
		Pattern datePattern = Pattern.compile("(\\d{1,2}+)\\s(\\D+)\\s(\\d{4}),.*");
		Matcher datePatternMatcher = datePattern.matcher(dateString);
		
		int dayOfMonth = new Integer(datePatternMatcher.group(0)); 
		String monthString = datePatternMatcher.group(1);
		int year = new Integer(datePatternMatcher.group(2));
		
		Month month = getMonthFromStringPattern(monthString);
		
		LocalDate dateResult = LocalDate.of(year, month, dayOfMonth);

		return dateResult;
	}
	
	public static Month getMonthFromStringPattern(String monthString) {

		for (Month month : Month.values()) {
			String monthEnumString = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();
			
			if (monthEnumString.contains(monthString.toUpperCase())) {
				return month;
			}
		}
		
		return null;
	}
}
