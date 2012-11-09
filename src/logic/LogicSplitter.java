package logic;
import global.Clock;
import global.StringOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
  
public class LogicSplitter {
	protected static final String SHORTHAND_UPDATE = "u";
	protected static final String COMMAND_UPDATE = "update";
	protected static final String SHORTHAND_ADD = "+";
	protected static final String COMMAND_ADD = "add";
	protected static final String COMMAND_DONE = "undone";
	protected static final String COMMAND_UNDONE = "done";
	protected static final String COMMAND_UNDO = "undo";
	protected static final String SHORTHAND_DELETE = "-";
	protected static final String COMMAND_DELETE = "delete";
	protected static final String COMMAND_SEARCH = "search";
	private static final String STRING_SPACE = " ";
	private static final String EMPTY_STRING = "";
	protected static int message = 0;
	public static void setUp() {
		message = 0;
	}

	public static int getMessage() {
		return message;
	}
	public static Vector<String> splitInput(String userInput) throws Exception{
		Vector<String> parameterList = new Vector<String>();
		Exception exception = new Exception();
		try {
			userInput = extractCommandTypeAndUpdateInputString(userInput,
					parameterList);
		} catch (Exception e) {
			message = 12;
			throw exception;
			
		}
		try{
		if(parameterList.get(0).toLowerCase().contains(COMMAND_ADD)||parameterList.get(0).contains(SHORTHAND_ADD))
		{
			parameterList=LogicSplitterAdd.splitInputAdd(userInput,parameterList);
		}
		else if(parameterList.get(0).toLowerCase().contains(COMMAND_UPDATE)||parameterList.get(0).contains(SHORTHAND_UPDATE))
		{
			parameterList=LogicSplitterUpdate.splitInputUpdate(userInput,parameterList);
		}
		else if(parameterList.get(0).toLowerCase().contains(COMMAND_UNDONE))
		{
			parameterList=LogicSplitterUndone.splitInputUndone(userInput,parameterList);
		}
		else if(parameterList.get(0).toLowerCase().contains(COMMAND_DONE))
		{
			parameterList=LogicSplitterDone.splitInputDone(userInput,parameterList);
		}
		else if(parameterList.get(0).toLowerCase().contains(COMMAND_DELETE)||parameterList.get(0).contains(SHORTHAND_DELETE))
		{
			parameterList=LogicSplitterDelete.splitInputDelete(userInput,parameterList);
		}
		else if(parameterList.get(0).toLowerCase().contains(COMMAND_SEARCH))
		{
			parameterList=LogicSplitterSearch.splitInputSearch(userInput,parameterList);
		}
		}
		catch(Exception e)
		{
			throw exception;
		}
		String hashTags=getAllHashTags(userInput);
		if(parameterList.size()>1)
		{
			parameterList.set(1, String.format(parameterList.get(1) + hashTags));
		}
		parameterList = trimAllParameters(parameterList);
		return parameterList;
	}
	protected static String getAllHashTags(String userInput) {
		String hashTagString="";
		Pattern hashPattern=Pattern.compile("(#[a-zA-Z0-9&&[^ ]]{1,} ?)");
		Matcher matches = hashPattern.matcher(userInput);
		while(matches.find())
		{
			hashTagString+=matches.group();
		}
		return hashTagString;
	}
	protected static String extractCommandTypeAndUpdateInputString(
			String command, Vector<String> parameterList) throws Exception {
		command = StringOperation.removeExtraSpace(command);
		command = command + STRING_SPACE;
		parameterList.add(command.substring(0, command.indexOf(STRING_SPACE)));
		command = command.replace(parameterList.get(0), EMPTY_STRING);
		command = command.trim();
		return command;
	}

/*	protected static String extractTimeFieldsAndUpdateInputString(
			int[] reminderFound, String userInput,
			Vector<String> parameterList, Vector<Integer> timeIndexes,
			int[] timeCount) throws Exception {
		Vector<String> timeList=new Vector<String>();
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
					timeList.add(temp.substring(0, indexEnd));
					userInput += temp.substring(0, indexEnd);
					timeIndexes.add(j);
					timeIndexes.add(indexEnd);
					if (i < original.length() - 1) {
						userInput = userInput + original.substring(i + 1);
					} else
						userInput += EMPTY_STRING;
				timeCount[0]++;
				if(timeCount[0]>2)
					timeList.remove(0);
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
		parameterList.addAll(timeList);
		return userInput;
	}	*/
	protected static String extractTimeFieldsAndUpdateInputString(
			int[] reminderFound, String userInput,
			Vector<String> parameterList, Vector<Integer> timeIndexes,
			int[] timeCount) throws Exception {
		Vector<String> timeList=new Vector<String>();
		for (int j = 0; j < userInput.length(); j++) {
			String temp = userInput.substring(j, userInput.length());
			String original = temp;
			userInput = userInput.substring(0, j);
			if (PatternLib.isFindDateTime(temp)[1] == 0) {
					int indexStart = 0;
					int indexEnd = PatternLib.isFindDateTime(temp)[2];
					j += PatternLib.isFindDateTime(temp)[2]-1;
					timeList.add(StringOperation.prepareInputToAnalyzeTime(temp.substring(indexStart, indexEnd)));
					userInput += temp.substring(indexStart, indexEnd);
					timeIndexes.add(j);
					timeIndexes.add(indexEnd);
					if (indexEnd < original.length() - 1) {
						userInput = userInput + original.substring(indexEnd);
					} else
						userInput += EMPTY_STRING;
				timeCount[0]++;
				if(timeCount[0]>2) {
					timeList.remove(0);
				}
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
		parameterList.addAll(timeList);
		return userInput;
	}


	protected static void processEndStartTime(String userInput,
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
			int endFirst = timeIndexes.get(0)+1;
			int startFirst = timeIndexes.get(0) - timeIndexes.get(1)+1;
			int endSecond = timeIndexes.get(2)+1;
			int startSecond = timeIndexes.get(2) - timeIndexes.get(3)+1;
			int firstTimePattern = PatternLib.isMatchDateTime(userInput
					.substring(startFirst, endFirst));
			int secondTimePattern = PatternLib.isMatchDateTime(userInput
					.substring(startSecond, endSecond));
			DateTime time1 = PatternLib
					.getDateTime(StringOperation.prepareInputToAnalyzeTime(userInput.substring(startFirst, endFirst)),
							firstTimePattern);
			DateTime time2 = PatternLib.getDateTime(
					StringOperation.prepareInputToAnalyzeTime(userInput.substring(startSecond, endSecond)),
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

	protected static void extractKeywordsAlongWithHashTags(String userInput,
			Vector<String> parameterList) {
		if (!userInput.trim().isEmpty()
				&& StringOperation.isInteger(userInput.trim()) == -1) {
			try
			{
			parameterList.add(userInput.substring(0,
					getIndexOfNextComponent(userInput)).trim());
			}
			catch(Exception e)
			{
				parameterList.add(userInput.substring(0,
						getIndexOfNextComponent(userInput)-1).trim());
			}
			userInput = userInput.replace(parameterList.lastElement(),
					EMPTY_STRING);
		} else
			parameterList.add(userInput);
	}

	protected static int getIndexOfNextComponent(String input) {
		int result1= input.length() ,result3= input.length(), result2 = input.length();
		for (int i = 0; i < input.length(); i++) {

			if (input.toLowerCase().indexOf("r-") == i) {
				result1 = i;
				break;
			}
		}
		for (int i=0; i<input.length();i++)
		{
			if(input.indexOf("#") ==i ) {
			result3=i;
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
		ArrayList<Integer> arrayForSort = new ArrayList<Integer>();
		arrayForSort.add(result1);
		arrayForSort.add(result2);
		arrayForSort.add(result3);
		Collections.sort(arrayForSort);
		return arrayForSort.get(0);
	}

	protected static void shiftKeywordsToSecondIndex(Vector<String> parameterList) {
		parameterList.add(1, parameterList.lastElement());
		parameterList.remove(parameterList.size() - 1);
	}

	protected static Vector<String> trimAllParameters(Vector<String> parameterList) {
		for (int i = 0; i < parameterList.size(); i++) {
			parameterList.add(i, parameterList.get(i).trim());
			parameterList.remove(i + 1);
		}
		return parameterList;
	}
}