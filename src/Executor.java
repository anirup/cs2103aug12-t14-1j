import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

import org.joda.time.Duration;
public class Executor {

	public static void analyze(String userInput) {
		
		String[] parameterList = userInput.split("..");
		String command = Logic.getCommand(parameterList);
		if (command.equalsIgnoreCase("add")) {
			analyzeAddInput(parameterList);
			Logic.searchToFalse();
		} else if (command.equalsIgnoreCase("delete")) {
			if (Logic.getSearchState() == true) {
				int index = Logic.getInteger(parameterList);
				ListOfUserLog.add(new ActionArchiveDelete(ListOfEvent.get(index)));
				ListOfEvent.remove(index);
				Logic.searchToFalse();
			} else if (Logic.getSearchState() == false) {
				analyzeAndSearch(parameterList);
				Logic.searchToTrue();
			}
		} else if (command.equalsIgnoreCase("update")) {
			if (Logic.getSearchState() == true) {
				int index = Logic.getInteger(parameterList);
				updateEvent(index);
				Logic.searchToFalse();
			} else if (Logic.getSearchState() == false) {
				analyzeAndSearch(parameterList);
				Logic.searchToTrue();
			}
		} else if (command.equalsIgnoreCase("search")) {
			analyzeAndSearch(parameterList);
		} else if (command.equalsIgnoreCase("done")) {
			if (Logic.getSearchState() == true) {
				int index = Logic.getInteger(parameterList);
				markDone(index);
				Logic.searchToFalse();
			} else if (Logic.getSearchState() == false) {
				analyzeAndSearch(parameterList);
				Logic.searchToTrue();
			}
		} else if (command.equalsIgnoreCase("undone")) {
			if (Logic.getSearchState() == true) {
				int index = Logic.getInteger(parameterList);
				markNotDone(index);
				Logic.searchToFalse();
			} else if (Logic.getSearchState() == false) {
				analyzeAndSearch(parameterList);
				Logic.searchToTrue();
			}
		} else if (command.equalsIgnoreCase("undo")) {
			undoLast();
		}

	}

	public static void undoLast() {
		ListOfUserLog.undo();
	}

	public static void analyzeAndSearch(String[] parameterList) { // keywords
																	// have to
																	// be there
																	// but you
																	// can OR
																	// hash tags

	}

	public static void markNotDone(int index) {
		ListOfEvent.markUndone(index);
	}

	public static void markDone(int index) {
		ListOfEvent.markDone(index);
	}

	public static void analyzeAddInput(String[] parameterList) {
		String priority, keywords,id;
		Duration reminderTime;
		id=Logic.getEventID();
		keywords = Logic.getKeyWords(parameterList);
		priority = Logic.getPriority(parameterList);
		reminderTime = Logic.getReminderTime(parameterList);
		Vector<String> resultHashTags = Logic.getHashTags(parameterList);
		resultHashTags.add(priority);
		String startTimeDate=Logic.getStartTime(parameterList);
		String endTimeDate=Logic.getEndTime(parameterList);
		if(startTimeDate.equalsIgnoreCase(null)&&endTimeDate.equalsIgnoreCase(null))
		{
			
		}
	}

	public static void updateEvent(int index) {

	}
}
