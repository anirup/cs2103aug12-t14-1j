package logic;
import global.Clock;
import global.StringOperation;

import java.util.Vector;

import org.joda.time.DateTime;


public class LogicSplitter {
	private static final String SHORTHAND_ADD = "+";
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_SEARCH = "search";
	private static final String STRING_SPACE = " ";
	private static final String EMPTY_STRING = "";
	private static final String Priority_Normal = "NORMAL";
	private static final String Priority_Low = "LOW";
	private static final String Priority_High = "HIGH";
	private static int message = 0;
	public static Vector<String> additionalComments;

	public static void setUp() {
		message = 0;
		additionalComments = new Vector<String>();
		additionalComments.clear();
	}

	public static int getMessage() {
		return message;
	}

	public static Vector<String> getAdditionalComments() {
		return additionalComments;
	}

	public static Vector<String> splitInput(String userInput) {
		int[] reminderFound = { 0 };
		int[] timeCount = { 0 };
		Vector<String> parameterList = new Vector<String>();
		Vector<Integer> timeIndexes = new Vector<Integer>();
		try {
			userInput = extractCommandTypeAndUpdateInputString(userInput,
					parameterList);
		} catch (Exception e) {
			message = 12;
			return parameterList;
		}
		if (parameterList.get(0).equals(COMMAND_ADD)
				|| parameterList.get(0).equals(SHORTHAND_ADD)
				|| parameterList.get(0).equals(COMMAND_SEARCH)) {
			try {

				userInput = extractTimeFieldsAndUpdateInputString(
						reminderFound, userInput, parameterList, timeIndexes,
						timeCount);
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
			try {
				shiftKeywordsToSecondIndex(parameterList);
			} catch (Exception e) {
				message = 16;
				return parameterList;
			}
			try {
				processEndStartTime(userInput, parameterList, timeIndexes);
			} catch (Exception e) {
				message = 13;
				return parameterList;
			}
		} else {
			extractKeywordsAlongWithHashTags(userInput, parameterList);
		}
		parameterList = trimAllParameters(parameterList);
		String[] convertedArray = new String[parameterList.size()];
		Vector<String> checkHashTags = LogicAnalyzer.getHashTags(parameterList
				.toArray(convertedArray));
		checkMultiplePriorities(checkHashTags);
		parameterList = trimAllParameters(parameterList);
		setOtherAdditionalComments(reminderFound, timeCount);
		return parameterList;
	}

	private static void checkMultiplePriorities(Vector<String> checkHashTags) {
		int count = 0;
		for (int j = 0; j < checkHashTags.size(); j++) {
			if ((checkHashTags.get(j).equalsIgnoreCase(Priority_High))
					|| (checkHashTags.get(j).equalsIgnoreCase(Priority_Low))
					|| (checkHashTags.get(j).equalsIgnoreCase(Priority_Normal))) {
				count++;
				if (count > 1) {
					additionalComments
							.add("More than 1 Type of Priorities Detected - Taking the first one.");
				}
			}
		}

	}

	private static void setOtherAdditionalComments(int[] reminderFound,
			int[] timeCount) {
		if (timeCount[0] == 0) {
			additionalComments
					.add("No Time Parameters Found - If You Did Enter Time Parameters - Please check Supported Time formats.");
		} else if (timeCount[0] == 1) {
			additionalComments
					.add("1 Time Parameter Found - It was taken as Start Time");
		} else if (timeCount[0] == 2) {
			additionalComments
					.add("2 Time Parameters Found - Taken as Start and End Time");
		} else if (timeCount[0] > 2) {
			additionalComments
					.add("More than 2 Time Parameters Found - Please check Supported Time Formats.");
		}
		if (reminderFound[0] == 0) {
			additionalComments.add("No Reminder Set");
		} else if (reminderFound[0] == 1) {
			additionalComments.add("Reminder Set");
		} else if (reminderFound[0] > 1) {
			additionalComments
					.add("More than 1 Reminder Found - Reminder Set as the first one");
		}
		if (reminderFound[0] > 0 && timeCount[0] == 0) {
			additionalComments.add("No Reminder Time for Floating Type Events");
		}
	}

	private static String extractCommandTypeAndUpdateInputString(
			String command, Vector<String> parameterList) throws Exception {
		command = StringOperation.removeExtraSpace(command);
		command = command + STRING_SPACE;
		parameterList.add(command.substring(0, command.indexOf(STRING_SPACE)));
		command = command.replace(parameterList.get(0), EMPTY_STRING);
		command = command.trim();
		return command;
	}

	private static String extractTimeFieldsAndUpdateInputString(
			int[] reminderFound, String userInput,
			Vector<String> parameterList, Vector<Integer> timeIndexes,
			int[] timeCount) throws Exception {
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
				timeCount[0]++;
				if(timeCount[0]>2)
					parameterList.remove(1);
			} else if (PatternLib.isFindReminderTime(original)[1] == 0) {
				if (reminderFound[0] < 1) {
					int a[] = PatternLib.isFindReminderTime(original);
					userInput = userInput
							+ original.substring(0,
									PatternLib.isFindReminderTime(original)[2]);
					if (reminderFound[0] == 0) {
						parameterList.add(original.substring(0,
								PatternLib.isFindReminderTime(original)[2]));

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
				}
				reminderFound[0]++;
			} else {
				userInput = userInput + original;
			}
		}
		return userInput;
	}

	private static void processEndStartTime(String userInput,
			Vector<String> parameterList, Vector<Integer> timeIndexes)
			throws Exception {
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
			int firstTimePattern = PatternLib.isMatchDateTime(userInput
					.substring(startFirst, endFirst));
			int secondTimePattern = PatternLib.isMatchDateTime(userInput
					.substring(startSecond, endSecond));
			DateTime time1 = PatternLib
					.getDateTime(userInput.substring(startFirst, endFirst),
							firstTimePattern);
			DateTime time2 = PatternLib.getDateTime(
					userInput.substring(startSecond, endSecond),
					secondTimePattern);
			if (userInput.substring(endFirst - 1, startSecond + 1).contains(
					" to")) {
				if (firstTimePattern < 15 && firstTimePattern > 12) {
					time1 = Clock.changeToDate(time1, time2);
				}
			}
				parameterList.add(indexes.get(0), time1.toString());
				parameterList.remove(indexes.get(0) + 1);
				parameterList.add(indexes.get(1), time2.toString());
				parameterList.remove(indexes.get(1) + 1);

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
}