package anvil.data.remote.lastfm;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Static utility class for parsing date values provided by the LastFM API
 */
public class LastFM_Date {
	
	/** 
	 * Convert a {dd Month yyyy} representation of a date to java.time.LocalDate
	 * @param dateString
	 * @return
	 */
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

    /** 
     * Convert a string representation of a month to java.time.Month
     * @param {String} monthString
     */
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
