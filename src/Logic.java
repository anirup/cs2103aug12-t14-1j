import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import java.util.Vector;

public class Logic {

	private static final String SPLIT_HASH = "#";
	private static final String Priority_Normal = "NORMAL";
	private static final String Priority_Low = "LOW";
	private static final String Priority_High = "HIGH";
	private static final String SPLITTER = "\\..";
	private static final String TIME_ZONE = "+8:00";
	private static boolean fieldFound[] = { false, false, false, false, false,
			false };

	public static void setUp() {
		for (int i = 0; i < 6; i++) {
			fieldFound[i] = false;
		}
	}

	public static Vector<String> splitInput(String command) {
		Vector<String> parameterList = new Vector<String>();
		command = StringOperation.removeExtraSpace(command);
		command = command + " ";
		parameterList.add(command.substring(0, command.indexOf(" ")));
		command = command.replace(parameterList.get(0), "");
		command = command.trim();
		int originalLength = command.length() - 1;
		for (int j = 0; j < command.length(); j++) {
			String temp = command.substring(j, command.length());
			String original = temp;
			command = command.replace(temp, "");
			temp = StringOperation.prepareInputToAnalyzeTime(temp);
			if (PatternLib.isFindDateTime(temp)[1] == 0) {
				int i;
				String formatted = temp.substring(
						PatternLib.isFindDateTime(temp)[1],
						PatternLib.isFindDateTime(temp)[2]);
				for (i = original.length(); i >= 0; i--) {
					if (!StringOperation.prepareInputToAnalyzeTime(
							original.substring(0, i)).contains(formatted)) {
						break;
					}
				}
				j += PatternLib.isFindDateTime(temp)[2];
				parameterList.add(temp.substring(0,
						PatternLib.isFindDateTime(temp)[2]));
				command += temp
						.substring(0, PatternLib.isFindDateTime(temp)[2]);
				if (i < original.length() - 1) {
					command = command + original.substring(i + 1);
				} else
					command += "";
			} else if (PatternLib.isFindReminderTime(original)[1] == 0) {
				int a[] = PatternLib.isFindReminderTime(original);
				command = command
						+ original.substring(0,
								PatternLib.isFindReminderTime(original)[2]);
				parameterList.add(original.substring(0,
						PatternLib.isFindReminderTime(original)[2]));
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
					command = command + original.substring(i + 1);
				} else
					command += "";
			} else {
				command = command + original;
			}
		}
		if (!command.trim().isEmpty()) {
			parameterList.add(command.trim().substring(0,
					getIndexOfNextComponent(command)));
			command = command.replace(parameterList.lastElement(), "");
		}
		parameterList.add(1, parameterList.lastElement());
		parameterList.remove(parameterList.size() - 1);
		return parameterList;
	}

	private static int getIndexOfNextComponent(String input) {
		int result1 = input.length(), result2 = 0;
		for (int i = 0; i < input.length(); i++) {

			if (input.toLowerCase().indexOf("r-") == i) {
				result1 = i;
				break;
			}
		}
		for (int i = 0; i < input.length(); i++) {
			int timeIndex[] = PatternLib.isFindDateTime(input.substring(
					input.length() - 1 - i, input.length()));
			if (timeIndex[1] == 0 && timeIndex[0] < 19) {
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

	public static String getKeyWords(String[] parameterList) {
		fieldFound[1] = true;
		if (parameterList[1].trim().contains("#")) {
			return parameterList[1].trim()
					.substring(0, parameterList[1].indexOf(SPLIT_HASH)).trim();
		} else {
			return parameterList[1].trim();
		}
	}

	public static void getPriority(Vector<String> hashTags) {
		boolean found=false;
		for(int i=0;i<hashTags.size();i++)
		{
			if(hashTags.get(i).trim().equalsIgnoreCase("high") || hashTags.get(i).trim().equalsIgnoreCase("h"))
			{
				hashTags.add(0, Priority_High);
				hashTags.remove(i);
				found=true;
			}
			else if(hashTags.get(i).trim().equalsIgnoreCase("normal") || hashTags.get(i).trim().equalsIgnoreCase("n"))
			{
				hashTags.add(0, Priority_Normal);
				hashTags.remove(i);
				found=true;
			}
			else if(hashTags.get(i).trim().equalsIgnoreCase("low") || hashTags.get(i).trim().equalsIgnoreCase("l"))
			{
				hashTags.add(0, Priority_Low);
				hashTags.remove(i);
				found=true;
			}
		}
		if(found==false)
		{
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
				Pattern p = Pattern.compile("\\d+");
				Matcher matches = p.matcher(parameterList[i].trim());
				while (matches.find()) {
					timeQuantity.add(Long.parseLong(matches.group()));
				}
				break;
			}
		}
		if (indexOfReminder != -1) {
			for (int j = 0; j < timeQuantity.size() - 1; j++) {
				String firstLimitString = "" + timeQuantity.get(j);
				String secondLimitString = "" + timeQuantity.get(j + 1);
				int lastIndex = parameterList[indexOfReminder]
						.indexOf(secondLimitString);
				int firstIndex = firstLimitString.length()
						+ parameterList[indexOfReminder]
								.indexOf(firstLimitString);
				String parameter = parameterList[indexOfReminder].substring(
						firstIndex, lastIndex);
				timeParameter.addElement(parameter.toLowerCase());
			}
			String firstLimitString = ""
					+ timeQuantity.get(timeQuantity.size() - 1);
			int lastIndex = parameterList[indexOfReminder].length() - 1;
			int firstIndex = firstLimitString.length()
					+ parameterList[indexOfReminder].indexOf(firstLimitString);
			String parameter = parameterList[indexOfReminder].substring(
					firstIndex, lastIndex + 1);
			timeParameter.addElement(parameter.toLowerCase());
			for (int k = 0; k < timeParameter.size(); k++) {
				if (timeParameter.get(k).trim().startsWith("d")
						|| timeParameter.get(k).trim().startsWith("day")
						|| timeParameter.get(k).trim().startsWith("days")) {
					miliseconds += timeQuantity.get(k) * 86400000;
				} else if (timeParameter.get(k).trim().startsWith("h")
						|| timeParameter.get(k).trim().startsWith("hr")
						|| timeParameter.get(k).trim().startsWith("hour")) {
					miliseconds += timeQuantity.get(k) * 3600000;
				} else if (timeParameter.get(k).trim().startsWith("m")
						|| timeParameter.get(k).trim().startsWith("min")
						|| timeParameter.get(k).trim().startsWith("minute")) {
					miliseconds += timeQuantity.get(k) * 60000;
				} else if (timeParameter.get(k).trim().startsWith("s")
						|| timeParameter.get(k).trim().startsWith("sec")
						|| timeParameter.get(k).trim().startsWith("second")) {
					miliseconds += timeQuantity.get(k) * 1000;
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
			int startHashCode = parameterList[i].indexOf(SPLIT_HASH);
			if (startHashCode > -1) {
				String[] hashCodes = parameterList[i].trim()
						.substring(startHashCode + 1).trim().split(SPLIT_HASH);
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
		String allHash = "";
		for (int i = 0; i < hashList.size(); i++) {
			allHash = allHash + "#" + hashList.get(i);
		}
		return allHash;
	}

	public static String getCommand(String[] parameterList) {
		fieldFound[0] = true;
		return parameterList[0];
	}

	public static int getInteger(String[] parameterList) {
		try {
			return Integer.parseInt(parameterList[1]);
		} catch (NumberFormatException e) {
			System.out.println("Invalid argument");
			return -1;
		}
	}

	public static String getStartTime(String[] parameterList) {
		String currentTime = "";
		for (int i = 0; i < 6; i++) {
			if (fieldFound[i] == false && !parameterList[i].equals("-1")) {
				currentTime = "";
				return parameterList[i];
			}
		}

		return currentTime;
	}

	public static String getEndTime(String[] parameterList) {
		String endTime = "";
		for (int i = 0; i < 6; i++) {
			if (fieldFound[i] == false && !parameterList[i].equals("-1")) {
				endTime = "";
				return parameterList[i];
			}
		}

		return endTime;
	}

	public static String getEventID() {
		Vector<Integer> idDigits = new Vector<Integer>();
		String eventId = LocalDate.now().toString()
				+ LocalTime.now().toString();
		Pattern p = Pattern.compile("\\d+");
		Matcher matches = p.matcher(eventId.trim());
		while (matches.find()) {
			idDigits.add((int) Long.parseLong(matches.group()));
		}
		eventId = "";
		for (int i = 0; i < idDigits.size(); i++) {
			eventId += idDigits.get(i);
		}
		return eventId;

	}

	public static String getEventString(String[] parameterList) {
		String eventName = getKeyWords(parameterList);
		String eventHashTag = getHashTagsString(getHashTags(parameterList));
		String endTime = getEndTime(parameterList);
		String startTime = getStartTime(parameterList);
		if (endTime == "") {
			endTime = "invalid";
		}
		if (startTime == "") {
			startTime = "invalid";
		}
		DateTime start = Clock.parseTimeFromString(startTime);
		DateTime end = Clock.parseTimeFromString(endTime);
		Duration eventReminder = getReminderTime(parameterList);
		String eventTime = start.toString() + SPLITTER + end.toString();
		String reminderString = null;
		if (endTime != "invalid") {
			DateTime reminder = end.minusSeconds((int) eventReminder
					.getStandardSeconds());
			reminderString = reminder.getYear() + "-"
					+ reminder.getMonthOfYear() + "-"
					+ reminder.getDayOfMonth() + "T" + reminder.getHourOfDay()
					+ ":" + reminder.getMinuteOfHour() + TIME_ZONE;
		}
		String content = eventName + SPLITTER + eventHashTag + SPLITTER
				+ "false" + SPLITTER + reminderString + SPLITTER + eventTime
				+ SPLITTER;
		return content;
	}
}