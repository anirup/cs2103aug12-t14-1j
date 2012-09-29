import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import org.joda.time.DateTime;
import org.joda.time.Duration;

public class Logic {

	private static final String SPLIT_HASH = "#";
	private static final String Priority_Normal = "NORMAL";
	private static final String Priority_Low = "LOW";
	private static final String Priority_High = "HIGH";
	private static boolean searchState;
	// private static ListOfEvent list;
	private static Vector<String> searchResults;
	// private static UserLog prevTasks;

	public Logic() {
		searchState = false;
		// list = new ListOfEvent();
		searchResults = new Vector<String>();
	}

	public static void analyze(String userInput) {
		String[] parameterList = userInput.split("..");
		String command = getCommand(parameterList);
		if (command.equalsIgnoreCase("add")) {
			analyzeAddInput(parameterList);
			searchToFalse();
		} else if (command.equalsIgnoreCase("delete")) {
			if (searchState == true) {
				int index = getInteger(parameterList);
				ListOfUserLog.add(new DeleteLog(ListOfEvent.get(index)));
				ListOfEvent.remove(index);
				searchToFalse();
			} else if (searchState == false) {
				analyzeAndSearch(parameterList);
				searchToTrue();
			}
		} else if (command.equalsIgnoreCase("update")) {
			if (searchState == true) {
				int index = getInteger(parameterList);
				updateEvent(index);
				searchToFalse();
			} else if (searchState == false) {
				analyzeAndSearch(parameterList);
				searchToTrue();
			}
		} else if (command.equalsIgnoreCase("search")) {
			analyzeAndSearch(parameterList);
		} else if (command.equalsIgnoreCase("done")) {
			if (searchState == true) {
				int index = getInteger(parameterList);
				markDone(index);
				searchToFalse();
			} else if (searchState == false) {
				analyzeAndSearch(parameterList);
				searchToTrue();
			}
		} else if (command.equalsIgnoreCase("undone")) {
			if (searchState == true) {
				int index = getInteger(parameterList);
				markNotDone(index);
				searchToFalse();
			} else if (searchState == false) {
				analyzeAndSearch(parameterList);
				searchToTrue();
			}
		} else if (command.equalsIgnoreCase("undo")) {
			undoLast();
		}

	}

	private static void searchToTrue() {
		searchState = true;
	}

	private static void searchToFalse() {
		searchState = false;
	}

	private static String getCommand(String[] parameterList) {
		return parameterList[0];
	}

	private static int getInteger(String[] parameterList) {
		try {
			return Integer.parseInt(parameterList[1]);
		} catch (NumberFormatException e) {
			System.out.println("Invalid argument");
			return -1;
		}
	}

	private static void undoLast() {
		ListOfUserLog.undo();
	}

	private static void analyzeAndSearch(String[] parameterList) { // keywords
																	// have to
																	// be there
																	// but you
																	// can OR
																	// hash tags

	}

	private static void markNotDone(int index) {
		ListOfEvent.markUndone(index);
	}

	private static void markDone(int index) {
		ListOfEvent.markDone(index);
	}

	private static void analyzeAddInput(String[] parameterList) {
		String priority, keywords,id;
		Duration reminderTime;
		id=getEventID();
		keywords = getKeyWords(parameterList);
		priority = getPriority(parameterList);
		reminderTime = getReminderTime(parameterList);
		Vector<String> resultHashTags = getHashTags(parameterList);
		resultHashTags.add(priority);
		String startTimeDate=getStartTime(parameterList);
		String endTimeDate=getEndTime(parameterList);
		if(startTimeDate.equalsIgnoreCase(null)&&endTimeDate.equalsIgnoreCase(null))
		{
			
		}
	}

	private static void updateEvent(int index) {

	}

	private static String getKeyWords(String[] parameterList) {
		return parameterList[1].trim().substring(0, parameterList[1].indexOf(SPLIT_HASH));
	}

	private static String getPriority(String[] parameterList) {
		for (int i = 0; i < parameterList.length; i++) {
			if (parameterList[i].trim().equalsIgnoreCase(Priority_High)
					|| parameterList[i].trim().equalsIgnoreCase("h")) {
				return Priority_High;
			} else if (parameterList[i].trim().equalsIgnoreCase(Priority_Low)
					|| parameterList[i].trim().equalsIgnoreCase("l")) {
				return Priority_Low;
			}
		}
		return Priority_Normal;
	}

	private static Duration getReminderTime(String[] parameterList) {
		long miliseconds = 0;
		int indexOfReminder = -1;
		Vector<Long> timeQuantity = new Vector<Long>();
		Vector<String> timeParameter = new Vector<String>();
		for (int i = 0; i < parameterList.length; i++) {
			if (parameterList[i].trim().startsWith("r-")
					|| parameterList[i].trim().startsWith("R-")) {
				indexOfReminder = i;
				Pattern p = Pattern.compile("\\d+");
				Matcher matches = p.matcher(parameterList[i].trim());
				while (matches.find()) {
					timeQuantity.add(Long.parseLong(matches.group()));
				}
			}
			break;
		}
		if (indexOfReminder != -1) {
			for (int j = 0; j < timeQuantity.size() - 1; j++) {
				String firstLimitString = "" + timeParameter.get(j);
				String secondLimitString = "" + timeParameter.get(j + 1);
				int lastIndex = parameterList[indexOfReminder]
						.indexOf(secondLimitString);
				int firstIndex = firstLimitString.length()
						+ parameterList[indexOfReminder]
								.indexOf(firstLimitString);
				String parameter = parameterList[indexOfReminder].substring(
						firstIndex, lastIndex);
				timeParameter.addElement(parameter.toLowerCase());
			}
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
		}

		return (new Duration(miliseconds));
	}

	private static Vector<String> getHashTags(String[] parameterList) {
		Vector<String> listOfHashTags = new Vector<String>();
		for (int i = 0; i < parameterList.length; i++) {
			int startHashCode = parameterList[i].indexOf(SPLIT_HASH);
			if (startHashCode > -1) {
				String[] hashCodes = parameterList[i].substring(startHashCode)
						.split(SPLIT_HASH);
				for (int j = 0; j < hashCodes.length; j++) {
					listOfHashTags.add(hashCodes[i].trim());
				}
			}

		}
		return listOfHashTags;
	}

	private static String getStartTime(String[] parameterList) {
		DateTime currentTime = new DateTime();
		
		return currentTime.toString();
	}
	private static String getEndTime(String[] parameterList)
	{
		return (new DateTime()).toString();
	}
	private static String getEventID()
	{
		return "";
	}
}
