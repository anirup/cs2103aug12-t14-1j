import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import java.util.Vector;

public class Logic {
	private static final String SHORTHAND_ADD = "+";
	private static final String COMMAND_ADD = "add";
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
	private static int message = 0;

	public static void setUp() {
		for (int i = 0; i < 6; i++) {
			fieldFound[i] = false;
		}
		message=0;
	}

	public static int getMessage() {
		return message;
	}

	public static Vector<String> splitInput(String userInput) {
		boolean[] reminderFound={false};
		Vector<String> parameterList = new Vector<String>();
		Vector<Integer> timeIndexes = new Vector<Integer>();
		try {
			userInput = extractCommandTypeAndUpdateInputString(userInput,
					parameterList);
		} catch (Exception e) {
			message = 12;
			return parameterList;
		}
		try {
			if (parameterList.get(0).equals(COMMAND_ADD)
					|| parameterList.get(0).equals(SHORTHAND_ADD)) {
				userInput = extractTimeFieldsAndUpdateInputString(reminderFound,userInput,
						parameterList, timeIndexes);
			}
		} catch (Exception e) {
			message = 13;
			return parameterList;
		}
		try {
			extractKeywordsAlongWithHashTags(userInput, parameterList);
		} catch (Exception e) {
			message = 14;
			return parameterList;
		}
		shiftKeywordsToSecondIndex(parameterList);
		processEndStartTime(userInput, parameterList, timeIndexes);
		parameterList = trimAllParameters(parameterList);
		return parameterList;
	}

	private static String extractCommandTypeAndUpdateInputString(
			String command, Vector<String> parameterList) {
		command = StringOperation.removeExtraSpace(command);
		command = command + STRING_SPACE;
		parameterList.add(command.substring(0, command.indexOf(STRING_SPACE)));
		command = command.replace(parameterList.get(0), EMPTY_STRING);
		command = command.trim();
		return command;
	}

	private static String extractTimeFieldsAndUpdateInputString(boolean[] reminderFound,
			String userInput, Vector<String> parameterList,
			Vector<Integer> timeIndexes) {
		for (int j = 0; j < userInput.length(); j++) {
			String temp = userInput.substring(j, userInput.length());
			String original = temp;
			userInput = userInput.substring(0, j);
			temp = StringOperation.prepareInputToAnalyzeTime(temp);
			if (PatternLib.isFindDateTime(temp)[1] == 0) {
				int i;
				int indexStart = PatternLib.isFindDateTime(temp)[1];
				int indexEnd = PatternLib.isFindDateTime(temp)[2];
				String formatted = temp.substring(indexStart, indexEnd);
				for (i = original.length(); i >= 0; i--) {
					if (!StringOperation.prepareInputToAnalyzeTime(
							original.substring(0, i)).contains(formatted)) {
						break;
					}
				}
				j += PatternLib.isFindDateTime(temp)[2];
				parameterList.add(temp.substring(0, indexEnd));
				userInput += temp.substring(0, indexEnd);
				timeIndexes.add(j);
				timeIndexes.add(indexEnd);
				if (i < original.length() - 1) {
					userInput = userInput + original.substring(i + 1);
				} else
					userInput += EMPTY_STRING;
			} else if (PatternLib.isFindReminderTime(original)[1] == 0) {
				int a[] = PatternLib.isFindReminderTime(original);
				userInput = userInput
						+ original.substring(0,
								PatternLib.isFindReminderTime(original)[2]);
				if(reminderFound[0]==false)
				{
				parameterList.add(original.substring(0,
						PatternLib.isFindReminderTime(original)[2]));
				reminderFound[0]=true;
				}
				j += a[2];
				int i;
				String formatted = original.substring(
						PatternLib.isFindReminderTime(original)[1],
						PatternLib.isFindReminderTime(original)[2]);
				for (i = original.length(); i >= 0; i--) {
					if (!original.substring(0, i).contains(formatted)) {
						break;
					}
				}
				if (i < original.length() - 1) {
					userInput = userInput + original.substring(i + 1);
				} else
					userInput += EMPTY_STRING;
			} else {
				userInput = userInput + original;
			}
		}
		return userInput;
	}

	private static void processEndStartTime(String userInput,
			Vector<String> parameterList, Vector<Integer> timeIndexes) {
		Vector<String> timeFields = new Vector<String>();
		Vector<Integer> indexes = new Vector<Integer>();
		if (parameterList.size() > 3) {
			for (int i = 2; i < parameterList.size(); i++) {
				if (!parameterList.get(i).toLowerCase().trim().contains("r-")) {
					timeFields.add(parameterList.get(i));
					indexes.add(i);
				}
			}
		}
		if (timeFields.size() == 2) {
			int endFirst = timeIndexes.get(0);
			int startFirst = timeIndexes.get(0) - timeIndexes.get(1);
			int endSecond = timeIndexes.get(2);
			int startSecond = timeIndexes.get(2) - timeIndexes.get(3);
			if (userInput.substring(endFirst, startSecond + 1).contains(" to")) {
				int firstTimePattern = PatternLib.isMatchDateTime(userInput
						.substring(startFirst, endFirst));
				int secondTimePattern = PatternLib.isMatchDateTime(userInput
						.substring(startSecond, endSecond));
				if (firstTimePattern < 15 && firstTimePattern > 12) {
					DateTime time1 = PatternLib.getDateTime(
							userInput.substring(startFirst, endFirst),
							firstTimePattern);
					DateTime time2 = PatternLib.getDateTime(
							userInput.substring(startSecond, endSecond),
							secondTimePattern);
					time1 = Clock.changeToDate(time1, time2);
					parameterList.add(indexes.get(0), Clock.toString(time1));
					parameterList.remove(indexes.get(0) + 1);
				}
			}
	
		}
	
	}

	private static void extractKeywordsAlongWithHashTags(String userInput,
			Vector<String> parameterList) {
		if (!userInput.trim().isEmpty()
				&& StringOperation.isInteger(userInput.trim()) == -1) {
			parameterList.add(userInput.trim().substring(0,
					getIndexOfNextComponent(userInput)));
			userInput = userInput.replace(parameterList.lastElement(),
					EMPTY_STRING);
		} else
			parameterList.add(userInput);
	}

	private static int getIndexOfNextComponent(String input) {
		int result1 = input.length(), result2 = input.length();
		for (int i = 0; i < input.length(); i++) {

			if (input.toLowerCase().indexOf("r-") == i) {
				result1 = i;
				break;
			}
		}
		for (int i = 0; i < input.length(); i++) {
			int timeIndex[] = PatternLib.isFindDateTime(input.substring(
					input.length() - 1 - i, input.length()));
			if (timeIndex[1] == 0) {
				result2 = input.length() - 1 - i;
			}
		}
		if (result1 > result2) {
			return result2;
		} else if (result2 > result1) {
			return result1;
		} else
			return input.length();
	}

	private static void shiftKeywordsToSecondIndex(Vector<String> parameterList) {
		parameterList.add(1, parameterList.lastElement());
		parameterList.remove(parameterList.size() - 1);
	}

	private static Vector<String> trimAllParameters(Vector<String> parameterList) {
		for (int i = 0; i < parameterList.size(); i++) {
			parameterList.add(i, parameterList.get(i).trim());
			parameterList.remove(i + 1);
		}
		return parameterList;
	}

	public static String getEventString(String[] parameterList) {
		String eventID = getEventID();
		String eventName = getKeyWords(parameterList);
		String eventHashTag = getHashTagsString(getHashTags(parameterList));
		Duration eventReminder = getReminderTime(parameterList);
		String endTime = getEndTime(parameterList);
		String startTime = getStartTime(parameterList);
		if (endTime == EMPTY_STRING) {
			endTime = STRING_INVALID;
		}
		if (startTime == EMPTY_STRING) {
			startTime = STRING_INVALID;
		}
		DateTime start = PatternLib.getDateTime(startTime,
				PatternLib.isMatchDateTime(startTime));
		DateTime end = PatternLib.getDateTime(endTime,
				PatternLib.isMatchDateTime(endTime));
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
		String content = eventID + SPLITTER + eventName + SPLITTER
				+ eventHashTag + SPLITTER + STRING_FALSE + SPLITTER
				+ reminderString + SPLITTER + eventTime + SPLITTER;
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

	public static void getPriority(Vector<String> hashTags) {
		boolean found = false;
		for (int i = 0; i < hashTags.size(); i++) {
			if (hashTags.get(i).trim().equalsIgnoreCase("high")
					|| hashTags.get(i).trim()
							.equalsIgnoreCase(REMINDER_HOUR_SHORTHAND2)) {
				hashTags.add(0, Priority_High);
				hashTags.remove(i);
				found = true;
			} else if (hashTags.get(i).trim().equalsIgnoreCase("normal")
					|| hashTags.get(i).trim().equalsIgnoreCase("n")) {
				hashTags.add(0, Priority_Normal);
				hashTags.remove(i);
				found = true;
			} else if (hashTags.get(i).trim().equalsIgnoreCase("low")
					|| hashTags.get(i).trim().equalsIgnoreCase("l")) {
				hashTags.add(0, Priority_Low);
				hashTags.remove(i);
				found = true;
			}
		}
		if (found == false) {
			hashTags.add(0, Priority_Normal);
		}
	}

	public static Duration getReminderTime(String[] parameterList) {
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
					listOfHashTags.add(hashCodes[j].trim());
				}
				break;
			}

		}
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

	public static String getStartTime(String[] parameterList) {
		String currentTime = EMPTY_STRING;
		for (int i = 2; i < 6; i++) {
			if (fieldFound[i] == false
					&& !parameterList[i].equals(ELEMENT_EMPTY)) {
				currentTime = EMPTY_STRING;
				fieldFound[i] = true;
				return parameterList[i];
			}
		}

		return currentTime;
	}

	public static String getEndTime(String[] parameterList) {
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

	public static String getEventID() {
		Vector<Integer> idDigits = new Vector<Integer>();
		String eventId = LocalDate.now().toString()
				+ LocalTime.now().toString();
		Pattern p = Pattern.compile(EXTRACT_NUMBERS_PATTERN);
		Matcher matches = p.matcher(eventId.trim());
		while (matches.find()) {
			idDigits.add((int) Long.parseLong(matches.group()));
		}
		eventId = EMPTY_STRING;
		for (int i = 0; i < idDigits.size(); i++) {
			eventId += idDigits.get(i);
		}
		return eventId;

	}

	public static int getInteger(String[] parameterList) {
		try {
			return Integer.parseInt(parameterList[1]);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}