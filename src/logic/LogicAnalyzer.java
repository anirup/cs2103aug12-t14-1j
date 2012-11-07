package logic;

import global.Clock;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.DateTime;
import org.joda.time.Duration;


public class LogicAnalyzer {
	private static final String ELEMENT_EMPTY = "-1";
	private static final String EXTRACT_NUMBERS_PATTERN = "\\d+";
	private static final String STRING_FALSE = "false";
	private static final String STRING_INVALID = "invalid";
	private static final String STRING_SPACE = " ";
	private static final String EMPTY_STRING = "";
	private static final String REMINDER_SECOND_SHORTHAND2 = "s";
	private static final String REMINDER_SECOND_SHORTHAND1 = "sec";
	private static final String REMINDER_SECOND = "second";
	private static final String REMINDER_MINUTE_SHORTHAND2 = "m";
	private static final String REMINDER_MINUTE_SHORTHAND1 = "min";
	private static final String REMINDER_MINUTE = "minute";
	private static final String REMINDER_DAY_SHORTHAND2 = "d";
	private static final String REMINDER_DAY_SHORTHAND1 = "day";
	private static final String REMINDER_DAYS = "days";
	private static final String REMINDER_HOUR_SHORTHAND2 = "h";
	private static final String REMINDER_HOUR_SHORTHAND1 = "hr";
	private static final String REMINDER_HOUR = "hour";
	private static final int MILLISECONDS_IN_SECOND = 1000;
	private static final int MILLISECONDS_IN_MINUTE = 60000;
	private static final int MILLISECONDS_IN_HOUR = 3600000;
	private static final int MILLISECONDS_IN_DAY = 86400000;
	private static final String SPLITTER_HASH = "#";
	private static final String SPLITTER_DATE = "/";
	private static final String SPLITTER_TIME = ":";
	private static final String SPLITTER = "..";
	private static final String Priority_Normal = "NORMAL";
	private static final String Priority_Low = "LOW";
	private static final String Priority_High = "HIGH";
	private static boolean fieldFound[] = { false, false, false, false, false,
		false };
	public static void setUp() {
		for (int i = 0; i < 6; i++) {
			fieldFound[i] = false;
		}
	}
	public static String getEventString(String[] parameterList) {
		String eventID = getEventID();
		String eventName = getKeyWords(parameterList);
		Vector<String> listOfHashTags = getAllHashTags(parameterList);
		String priority = listOfHashTags.get(0);
		listOfHashTags.remove(0);
		Duration eventReminder = getReminderTime(parameterList);
		String eventHashTag = getHashTagsString(listOfHashTags);
		String startTime=getTime(parameterList);
		String endTime=getTime(parameterList);
		if (endTime == EMPTY_STRING) {
			endTime = STRING_INVALID;
		}
		if (startTime == EMPTY_STRING) {
			startTime = STRING_INVALID;
		}
		DateTime start, end;
		if (!(startTime != STRING_INVALID && endTime != STRING_INVALID)) {
			start = PatternLib.getDateTime(startTime,
					PatternLib.isMatchDateTime(startTime));
			end = PatternLib.getDateTime(endTime,
					PatternLib.isMatchDateTime(endTime));
		} else {
			start = DateTime.parse(startTime);
			end = DateTime.parse(endTime);
		}
		if ((!startTime.equalsIgnoreCase(STRING_INVALID))
				&& !(endTime.equalsIgnoreCase(STRING_INVALID))) {
			if (Clock.isBefore(end, start)) {
				DateTime temp = start;
				start = end;
				end = temp;
			}
		}
		if (startTime != STRING_INVALID) {
			startTime = Clock.toString(start);
		}
		if (endTime != STRING_INVALID) {
			endTime = Clock.toString(end);
		}

		String eventTime = startTime + SPLITTER + endTime;
		String reminderString = null;
		if (endTime != STRING_INVALID && eventReminder.getMillis() != 0) {
			DateTime reminder = end.minusSeconds((int) eventReminder
					.getStandardSeconds());
			reminderString = reminder.getHourOfDay() + SPLITTER_TIME
					+ reminder.getMinuteOfHour() + STRING_SPACE
					+ reminder.getDayOfMonth() + SPLITTER_DATE
					+ reminder.getMonthOfYear() + SPLITTER_DATE
					+ reminder.getYear();
		} else {
			reminderString = STRING_INVALID;
		}
		String content = eventID + SPLITTER + eventName + SPLITTER + priority
				+ SPLITTER + eventHashTag + SPLITTER + STRING_FALSE + SPLITTER
				+ reminderString + SPLITTER + eventTime + SPLITTER + STRING_INVALID;
		return content;
	}

	public static String getKeyWords(String[] parameterList) {
		fieldFound[1] = true;
		if (parameterList[1].trim().contains(SPLITTER_HASH)) {
			return parameterList[1].trim()
					.substring(0, parameterList[1].indexOf(SPLITTER_HASH))
					.trim();
		} else {
			return parameterList[1].trim();
		}
	}

	private static void getPriority(Vector<String> hashTags) {
		boolean found = false;
		for (int i = 0; i < hashTags.size(); i++) {
			if (hashTags.get(i).trim().equalsIgnoreCase("high")
					|| hashTags.get(i).trim().equalsIgnoreCase("h")) {
				hashTags.remove(i);
				hashTags.add(0, Priority_High);
				found = true;
				return;
			} else if (hashTags.get(i).trim().equalsIgnoreCase("normal")
					|| hashTags.get(i).trim().equalsIgnoreCase("n")) {
				hashTags.remove(i);
				hashTags.add(0, Priority_Normal);
				found = true;
				return;
			} else if (hashTags.get(i).trim().equalsIgnoreCase("low")
					|| hashTags.get(i).trim().equalsIgnoreCase("l")) {
				hashTags.remove(i);
				hashTags.add(0, Priority_Low);
				found = true;
				return;
			}
		}
		if (found == false) {
			hashTags.add(0, Priority_Normal);
		}
	}

	private static Duration getReminderTime(String[] parameterList) {
		long miliseconds = 0;
		int indexOfReminder = -1;
		Vector<Long> timeQuantity = new Vector<Long>();
		Vector<String> timeParameter = new Vector<String>();
		for (int i = 0; i < parameterList.length; i++) {
			if (parameterList[i].trim().startsWith("r-")
					|| parameterList[i].trim().startsWith("R-")) {
				fieldFound[i] = true;
				indexOfReminder = i;
				Pattern p = Pattern.compile(EXTRACT_NUMBERS_PATTERN);
				Matcher matches = p.matcher(parameterList[i].trim());
				while (matches.find()) {
					timeQuantity.add(Long.parseLong(matches.group()));
				}
				break;
			}
		}
		if (indexOfReminder != -1) {
			for (int j = 0; j < timeQuantity.size() - 1; j++) {
				String firstLimitString = EMPTY_STRING + timeQuantity.get(j);
				String secondLimitString = EMPTY_STRING
						+ timeQuantity.get(j + 1);
				int lastIndex = parameterList[indexOfReminder]
						.indexOf(secondLimitString);
				int firstIndex = firstLimitString.length()
						+ parameterList[indexOfReminder]
								.indexOf(firstLimitString);
				String parameter = parameterList[indexOfReminder].substring(
						firstIndex, lastIndex);
				timeParameter.addElement(parameter.toLowerCase());
			}
			String firstLimitString = EMPTY_STRING
					+ timeQuantity.get(timeQuantity.size() - 1);
			int lastIndex = parameterList[indexOfReminder].length() - 1;
			int firstIndex = firstLimitString.length()
					+ parameterList[indexOfReminder].indexOf(firstLimitString);
			String parameter = parameterList[indexOfReminder].substring(
					firstIndex, lastIndex + 1);
			timeParameter.addElement(parameter.toLowerCase());
			for (int k = 0; k < timeParameter.size(); k++) {
				if (timeParameter.get(k).trim()
						.startsWith(REMINDER_DAY_SHORTHAND2)
						|| timeParameter.get(k).trim()
								.startsWith(REMINDER_DAY_SHORTHAND1)
						|| timeParameter.get(k).trim()
								.startsWith(REMINDER_DAYS)) {
					miliseconds += timeQuantity.get(k) * MILLISECONDS_IN_DAY;
				} else if (timeParameter.get(k).trim()
						.startsWith(REMINDER_HOUR_SHORTHAND2)
						|| timeParameter.get(k).trim()
								.startsWith(REMINDER_HOUR_SHORTHAND1)
						|| timeParameter.get(k).trim()
								.startsWith(REMINDER_HOUR)) {
					miliseconds += timeQuantity.get(k) * MILLISECONDS_IN_HOUR;
				} else if (timeParameter.get(k).trim()
						.startsWith(REMINDER_MINUTE_SHORTHAND2)
						|| timeParameter.get(k).trim()
								.startsWith(REMINDER_MINUTE_SHORTHAND1)
						|| timeParameter.get(k).trim()
								.startsWith(REMINDER_MINUTE)) {
					miliseconds += timeQuantity.get(k) * MILLISECONDS_IN_MINUTE;
				} else if (timeParameter.get(k).trim()
						.startsWith(REMINDER_SECOND_SHORTHAND2)
						|| timeParameter.get(k).trim()
								.startsWith(REMINDER_SECOND_SHORTHAND1)
						|| timeParameter.get(k).trim()
								.startsWith(REMINDER_SECOND)) {
					miliseconds += timeQuantity.get(k) * MILLISECONDS_IN_SECOND;
				}
			}
		} else {
			fieldFound[4] = true;
		}

		return (new Duration(miliseconds));
	}

	public static Vector<String> getHashTags(String[] parameterList) {
		Vector<String> listOfHashTags = new Vector<String>();
		for (int i = 0; i < parameterList.length; i++) {
			int startHashCode = parameterList[i].indexOf(SPLITTER_HASH);
			if (startHashCode > -1) {
				String[] hashCodes = parameterList[i].trim()
						.substring(startHashCode + 1).trim()
						.split(SPLITTER_HASH);
				for (int j = 0; j < hashCodes.length; j++) {
					if (!hashCodes[j].trim().isEmpty()
							|| !hashCodes[j].trim().equalsIgnoreCase(
									EMPTY_STRING)) {
						listOfHashTags.add(hashCodes[j].trim());
					}
				}
				break;
			}

		}
		return listOfHashTags;
	}

	private static Vector<String> getAllHashTags(String[] parameterList) {
		Vector<String> listOfHashTags = getHashTags(parameterList);
		getPriority(listOfHashTags);
		return listOfHashTags;
	}

	private static String getHashTagsString(Vector<String> hashList) {
		String allHash = EMPTY_STRING;
		for (int i = 0; i < hashList.size(); i++) {
			allHash = allHash + "#" + hashList.get(i);
		}
		return allHash;
	}

	public static String getCommand(String[] parameterList) {
		fieldFound[0] = true;
		return parameterList[0];
	}

	private static String getTime(String[] parameterList) {
		String endTime = EMPTY_STRING;
		for (int i = 2; i < 6; i++) {
			if (fieldFound[i] == false
					&& !parameterList[i].equals(ELEMENT_EMPTY)) {
				endTime = EMPTY_STRING;
				fieldFound[i] = true;
				return parameterList[i];
			}
		}

		return endTime;
	}

	private static String getEventID() {
		long id=System.currentTimeMillis();
		String eventId=""+id;
		return eventId;
		

	}

	public static int getInteger(String[] parameterList) {
		try {
			return Integer.parseInt(parameterList[1]);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public static String getReminderInFromat(String endTime, String reminder) {
		String formattedReminder = EMPTY_STRING;
		if (!endTime.equalsIgnoreCase(EMPTY_STRING)
				&& !reminder.equalsIgnoreCase(EMPTY_STRING)) {
			formattedReminder += "r-";
			DateTime end = Clock.parseTimeFromString(endTime);
			DateTime reminderTime = Clock.parseTimeFromString(reminder);
			Long milli = (end.getMillis() - reminderTime.getMillis());
			int day = (int) (milli / MILLISECONDS_IN_DAY);
			milli = (Long) (milli - (day * MILLISECONDS_IN_DAY));
			int hour = (int) (milli / MILLISECONDS_IN_HOUR);
			milli = (Long) (milli - (day * MILLISECONDS_IN_HOUR));
			int minutes = (int) (milli / MILLISECONDS_IN_MINUTE);
			milli = (Long) (milli - (day * MILLISECONDS_IN_MINUTE));
			int seconds = (int) (milli / 1000.0);
			if (day != 0) {
				formattedReminder += day + "day";
			} else if (hour != 0) {
				formattedReminder += hour + "hr";
			} else if (minutes != 0) {
				formattedReminder += minutes + "min";
			} else if (seconds != 0) {
				formattedReminder += seconds + "sec";
			}
		}
		return formattedReminder;
	}
}
