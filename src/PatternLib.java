import java.util.ArrayList;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

public class PatternLib {
	private static ArrayList<PatternDateTime> pat = new ArrayList<PatternDateTime>();
	private static ArrayList<PatternDateTime> patReminder = new ArrayList<PatternDateTime>();
	private static final int flags = Pattern.CASE_INSENSITIVE; //| Pattern.COMMENTS;
	
	private static final String date29 = "((0?[0-9])|([12][0-9]))";
	private static final String date28 = "((0?[0-9])|(1[0-9])|(2[0-8]))";
 	private static final String date30 = "((0?[0-9])|([12][0-9])|(30))";
	private static final String date31 = "((0?[0-9])|([12][0-9])|(3[01]))";
	private static final String month30Int = "((0?[469])|(11))";
	private static final String month31Int = "((0?[13578])|(1[02]))";
	private static final String month29Int = "(0?2)";
	private static final String month30String = "((apr)|(april)|(jun)|(june)|(sep)|(september)|(nov)|(november))";
	private static final String month31String = "((jan)|(january)|(mar)|(march)|(may)|(jul)|(july)|(aug)|(august)|(oct)|(october)|(dec)|(december))";
	private static final String month29String = "((feb)|(february))";
	private static final String yearInt = "((20)?[0-9][0-9])";
	private static final String leapYearInt = "((20)?(([02468][048])|([13579][26])))";
	private static final String dateSeparator = "[\\/-]";
	private static final String hour12 = "((0?[0-9])|(1[0-2]))";
	private static final String hour24 = "((0?[0-9])|(1[0-9])|(2[0-3]))";
	private static final String minute = "((0?[0-9])|([1-5][0-9]))";
	private static final String amOrPm = "((am)|(pm))";
	private static final String timeSeparator = "[:\\.]";
	private static final String dateInWeek = "((Mon)|(Monday)|(Tue)|(Tuesday)|(Wed)|(Wednesday)|(Thu)|(Thursday)|(Fri)|(Friday)|(Sat)|(Saturday)|(Sun)|(Sunday))";
	private static final String weekIndicator = "((this)|(next))";
	private static final String dateSpecial = "((today)|(tomorrow)|(tmr))";
	private static final String space = " ";
	private static final String patternTime12InDay = "^(" + hour12 + "(" + timeSeparator + minute + ")" + "?" + space +  "?" + amOrPm + ")";
	private static final String patternTime24InDay = "^(" + hour24 + "(" + timeSeparator + minute + ")" + "?" + ")";
	private static final String patternDateInWeek = "(" + weekIndicator + space + dateInWeek +  ")";
	private static final String patternDateWithoutYearInt = "((" + date29 + dateSeparator + month29Int + ")|(" + 
								date30 + dateSeparator + month30Int + ")|(" + date31 +  dateSeparator + month31Int + "))"; 
	private static final String patternDateWithoutYearString = "((" + date29 + dateSeparator + "?" + month29String + ")|(" + 
								date30 + dateSeparator + "?" + month30String + ")|(" + date31 +  dateSeparator + "?" + month31String + "))"; 
	private static final String patternDateWithYearInt = "((" + date28 + dateSeparator + month29Int + dateSeparator + yearInt + ")|(" +
								date29 + dateSeparator + month29Int + dateSeparator + leapYearInt + ")|(" + 
								date30 + dateSeparator + month30Int + dateSeparator + yearInt + ")|(" + 
								date31 +  dateSeparator + month31Int + dateSeparator + yearInt + "))";
	private static final String patternDateWithYearString = "((" + date28 + dateSeparator + "?" + month29String + dateSeparator + "?" + yearInt + ")|(" +
								date29 + dateSeparator + "?" + month29String + dateSeparator + "?" + leapYearInt + ")|(" + 
								date30 + dateSeparator + "?" + month30String + dateSeparator + "?" + yearInt + ")|(" + 
								date31 +  dateSeparator + "?" + month31Int + dateSeparator + "?" + yearInt + "))";
	private static final String patternReminderMinutes = "r- ?(((0?[0-9])|([1-5][0-9])) ?((min[s]?)|(minute[s]?)))";
	private static final String patternReminderHours = "r- ?((0?[0-9])|(1[0-9])|(2[0-4])) ?((hour[s]?)|(h)|(hr[s]))";
	private static final String patternReminderDays = "r- ?(((0?[0-9])|([1-9][0-9])) ?((day[s]?)|(d)))";
	private static final String patternReminderMinutesHoursDays = "r- ?(((0?[0-9])|([1-5][0-9])) ?((min[s]?)|(minute[s]?))) ?((0?[0-9])|(1[0-9])|(2[0-4])) ?((hour[s]?)|(h)|(hr[s]?)) ?(((0?[0-9])|([1-9][0-9])) ?((day[s]?)|(d)))";
	private static final String patternReminderHoursDays = "r- ?((0?[0-9])|(1[0-9])|(2[0-4])) ?((hour[s]?)|(h)|(hr[s]?)) ?(((0?[0-9])|([1-9][0-9])) ?((day[s]?)|(d)))";
	private static final String patternReminderMinutesDays = "r- ?(((0?[0-9])|([1-5][0-9])) ?((min[s]?)|(minute[s]?))) ?(((0?[0-9])|([1-9][0-9])) ?((day[s]?)|(d)))";
	private static final String patternReminderMinutesHours = "r- ?(((0?[0-9])|([1-5][0-9])) ?((min[s]?)|(minute[s]?))) ?((0?[0-9])|(1[0-9])|(2[0-4])) ?((hour[s]?)|(h)|(hr[s]?))";
	public static void setUpPattern() {
		
		String patternTime12AndDateInWeek = "(" + patternTime12InDay + space + patternDateInWeek + ")";
		PatternDateTime pattern = new PatternDateTime(Pattern.compile(patternTime12AndDateInWeek, flags), "patternTime12AndDateInWeek");
		pat.add(pattern);
		
		String patternTime24AndDateInWeek = "(" + patternTime24InDay + space + patternDateInWeek + ")";
		pattern = new PatternDateTime(Pattern.compile(patternTime24AndDateInWeek, flags), "patternTime24AndDateInWeek");
		pat.add(pattern);
		
		String patternTime12AndDateSpecial = "(" + patternTime12InDay + space + dateSpecial + ")";
		pattern = new PatternDateTime(Pattern.compile(patternTime12AndDateSpecial, flags), "patternTime12AndDateSpecial");
		pat.add(pattern);
		
		String patternTime24AndDateSpecial = "(" + patternTime24InDay + space + dateSpecial + ")";
		pattern = new PatternDateTime(Pattern.compile(patternTime24AndDateSpecial, flags), "patternTime24AndDateSpecial");
		pat.add(pattern);
		
		String patternTime12AndDateIntNoYear = "(" + patternTime12InDay + space + patternDateWithoutYearInt + ")";
		pattern = new PatternDateTime(Pattern.compile(patternTime12AndDateIntNoYear, flags), "HH:mmaa dd/MM");
		pat.add(pattern);
		
		String patternTime24AndDateIntNoYear = "(" + patternTime24InDay + space + patternDateWithoutYearInt + ")";
		pattern = new PatternDateTime(Pattern.compile(patternTime24AndDateIntNoYear, flags), "HH:mm dd/MM");
		pat.add(pattern);
		
		String patternTime12AndDateStringNoYear = "(" + patternTime12InDay + space + patternDateWithoutYearString + ")";
		pattern = new PatternDateTime(Pattern.compile(patternTime12AndDateStringNoYear, flags), "HH:mmaa dd/MMM");
		pat.add(pattern);
		
		String patternTime24AndDateStringNoYear = "(" + patternTime24InDay + space + patternDateWithoutYearString + ")";
		pattern = new PatternDateTime(Pattern.compile(patternTime24AndDateStringNoYear, flags), "HH:mm dd/MMM");
		pat.add(pattern);
		
		String patternTime12AndDateIntWithYear = "(" + patternTime12InDay + space + patternDateWithYearInt + ")";
		pattern = new PatternDateTime(Pattern.compile(patternTime12AndDateIntWithYear, flags), "HH:mmaa dd/MM/yy");
		pat.add(pattern);
		
		String patternTime24AndDateIntWithYear = "(" + patternTime24InDay + space + patternDateWithYearInt + ")";
		pattern = new PatternDateTime(Pattern.compile(patternTime24AndDateIntWithYear, flags), "HH:mm dd/MM/yy");
		pat.add(pattern);
		
		String patternTime12AndDateStringWithYear = "(" + patternTime12InDay + space + patternDateWithYearString + ")";
		pattern = new PatternDateTime(Pattern.compile(patternTime12AndDateStringWithYear, flags), "HH:mmaa dd/MMM/yy");
		pat.add(pattern);
		
		String patternTime24AndDateStringWithYear = "(" + patternTime24InDay + space + patternDateWithYearString + ")";
		pattern = new PatternDateTime(Pattern.compile(patternTime24AndDateStringWithYear, flags), "HH:mm dd/MMM/yy");
		pat.add(pattern);

		pattern = new PatternDateTime(Pattern.compile(patternDateWithoutYearInt, flags), "dd/MM");
		pat.add(pattern);
		
		pattern = new PatternDateTime(Pattern.compile(patternTime12InDay, flags), "patternTime12InDay");
		pat.add(pattern);
		
		pattern = new PatternDateTime(Pattern.compile(patternTime24InDay, flags), "patternTime24InDay");
		pat.add(pattern);
		
		pattern = new PatternDateTime(Pattern.compile(patternDateWithoutYearString, flags), "dd/MMM");
		pat.add(pattern);
		
		pattern = new PatternDateTime(Pattern.compile(patternDateWithYearInt, flags), "dd/MM/yy");
		pat.add(pattern);
		
		pattern = new PatternDateTime(Pattern.compile(patternDateWithYearString, flags), "dd/MMM/yy");
		pat.add(pattern);	

		pattern = new PatternDateTime(Pattern.compile(patternDateInWeek, flags), "patternDateInWeek");
		pat.add(pattern);
		
		pattern = new PatternDateTime(Pattern.compile(dateSpecial, flags), "dateSpecial");
		pat.add(pattern);
		
		pattern = new PatternDateTime(Pattern.compile(patternReminderMinutesHoursDays), "r-");
		pat.add(pattern);
		patReminder.add(pattern);
		
		pattern = new PatternDateTime(Pattern.compile(patternReminderHoursDays), "r-");
		pat.add(pattern);
		patReminder.add(pattern);
		
		pattern = new PatternDateTime(Pattern.compile(patternReminderMinutesDays), "r-");
		pat.add(pattern);
		patReminder.add(pattern);
		
		pattern = new PatternDateTime(Pattern.compile(patternReminderMinutesHours), "r-");
		pat.add(pattern);
		patReminder.add(pattern);
		
		pattern = new PatternDateTime(Pattern.compile(patternReminderHours), "r-");
		pat.add(pattern);
		patReminder.add(pattern);
		
		pattern = new PatternDateTime(Pattern.compile(patternReminderDays), "r-");
		pat.add(pattern);
		patReminder.add(pattern);

		pattern = new PatternDateTime(Pattern.compile(patternReminderMinutes), "r-");
		pat.add(pattern);
		patReminder.add(pattern);
	}
	
	public static DateTime getDateTime(String input, int patternIndex) {
		if(patternIndex < 0) {
			return Clock.getBigBangTime();
		}
		return pat.get(patternIndex).getDateTime(input);
	}
	
	public static int isMatchDateTime(String input) {
		for(int i = 0; i < pat.size(); i++) {	
			if(pat.get(i).isMatches(input)) {
				return i;
			}
		}
		return -1;
	}
	
	public static int[] isFindDateTime(String input) {
		int[] isFind = {-1, -1, -1};
		for(int i = 0; i < pat.size(); i++) {
			int[] pos = pat.get(i).isFind(input); 
			if(pos[0] != -1) {
				isFind[0] = i;
				isFind[1] = pos[0];
				isFind[2] = pos[1];
				return isFind;
			}
		}
		return isFind;
	}
	public static int[] isFindReminderTime(String input) {
		int[] isFind = {-1, -1, -1};
		for(int i = 0; i < patReminder.size(); i++) {
			int[] pos = patReminder.get(i).isFind(input); 
			if(pos[0] != -1) {
				isFind[0] = i;
				isFind[1] = pos[0];
				isFind[2] = pos[1];
				return isFind;
			}
		}
		return isFind;
	}
}
