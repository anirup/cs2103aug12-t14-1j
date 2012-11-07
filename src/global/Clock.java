package global;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Clock {
	private static final String dateTimeFormat = "HH:mm dd/MM/yyyy";
	private static final DateTime unableToParse = new DateTime(1970, 1, 1, 1, 0);
	
	public static DateTime getBigBangTime() {
		return new DateTime(1970, 1, 1, 0, 0);
	}
	
	public static boolean isBigBangTime(DateTime time) {
		if(toString(time).equalsIgnoreCase("invalid")) {
			return true;
		}
		return false;
	}
	
	public static DateTime parseTimeFromString(String date) {
		if(date.equalsIgnoreCase("invalid")) {
			return getBigBangTime();
		}
		try {
			DateTimeFormatter dateFormat = DateTimeFormat.forPattern(dateTimeFormat);
			return dateFormat.parseDateTime(date);
		} catch (Exception e) {
			return unableToParse;
		}
	}
	
	public static String toString(DateTime time) {
		DateTimeFormatter dateFormat = DateTimeFormat.forPattern(dateTimeFormat);
		String dateInString = time.toString(dateFormat);
		if(dateInString.equalsIgnoreCase("00:00 01/01/1970")) {
			return "invalid";
		}
		return dateInString;
	}
	
	public static String toStringToDisplay(DateTime time) {
		DateTimeFormatter dateFormat = DateTimeFormat.forPattern(dateTimeFormat);
		String dateInString = time.toString(dateFormat);
		if(dateInString.equalsIgnoreCase("00:00 01/01/1970")) {
			return "";
		}
		return dateInString;
	}
	
	
	public static DateTime changeToDate(DateTime fromDate, DateTime toDate) {
		DateTime date = new DateTime(toDate.getYear(), toDate.getMonthOfYear(), toDate.getDayOfMonth(),
				fromDate.getHourOfDay(), fromDate.getMinuteOfHour());
		return date;
	}
	
	public static boolean isSameTime(DateTime time, DateTime anotherTime) {
		if(time.getMillis() == anotherTime.getMillis()) {
			return true;
		} 
		return false;
	}
	
	public static boolean isValidTimeInString(String time) {
		DateTime date = parseTimeFromString(time);
		if(isSameTime(date, unableToParse)) {
			return false;
		}
		return true;
	}
	
	
	
	public static boolean isBefore(DateTime time, DateTime anotherTime) {
		long timeLong = anotherTime.getMillis();
		return time.isBefore(timeLong);
	}
	
	/*
	public static boolean searchTime(DateTime time, DateTime start, DateTime end) {
		if(isInDay(time, start) || isInDay(time, end)) {
			return true;
		}
		return false;
	}
	
	public static boolean isInDay(DateTime time, DateTime day) {
		if((time.getDayOfYear() == day.getDayOfYear()) && (time.getYearOfCentury() == time.getYearOfCentury())) {
			return true;
		}
		return false;
	}
	
	*/
}

