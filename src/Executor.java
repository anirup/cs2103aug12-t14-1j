import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

import org.joda.time.Duration;
public class Executor {

	private static Vector<Event> searchResults;
	private static boolean searchState;
	
	public Executor() {
		searchState = false;
		searchResults = new Vector<Event>();
	}
	
	private static boolean getSearchState() {
		return searchState;
	}
	
	private static void searchToFalse() {
		searchState = false;
	}
	
	private static void searchToTrue() {
		searchState = true;
	}
	public static void analyze(String userInput) {
		
		String[] parameterList = userInput.split("\\..");
		String command = Logic.getCommand(parameterList);
		if (command.equalsIgnoreCase("add")) {
			analyzeAddInput(parameterList);
			searchToFalse();
		} else if (command.equalsIgnoreCase("delete")) {
			if (getSearchState() == true) {
				int index = Logic.getInteger(parameterList);
				ListOfArchive.add(new ActionArchiveDelete(ListOfEvent.get(index)));
				ListOfEvent.remove(index);
				searchToFalse();
			} else if (getSearchState() == false) {
				analyzeAndSearch(parameterList);				
				searchToTrue();
			}
		} else if (command.equalsIgnoreCase("update")) {
			if (getSearchState() == true) {
				int index = Logic.getInteger(parameterList);
				updateEvent(index);
				searchToFalse();
			} else if (getSearchState() == false) {
				analyzeAndSearch(parameterList);
				searchToTrue();
			}
		} else if (command.equalsIgnoreCase("search")) {
			analyzeAndSearch(parameterList);
		} else if (command.equalsIgnoreCase("done")) {
			if (getSearchState() == true) {
				int index = Logic.getInteger(parameterList);
				markDone(index);
				searchToFalse();
			} else if (getSearchState() == false) {
				analyzeAndSearch(parameterList);
				searchToTrue();
			}
		} else if (command.equalsIgnoreCase("undone")) {
			if (getSearchState() == true) {
				int index = Logic.getInteger(parameterList);
				markNotDone(index);
				searchToFalse();
			} else if (getSearchState() == false) {
				analyzeAndSearch(parameterList);
				searchToTrue();
			}
		} else if (command.equalsIgnoreCase("undo")) {
			undoLast();
		}
		//save file , exit

	}

	public static void undoLast() {
		ListOfArchive.undo();
	}

	public static void analyzeAndSearch(String[] parameterList) { // keywords // have to
		Event current = ListOfEvent.getCurrentListOfEvent().getFirst();															// be there
		while(current!=null) {											// but you// can OR hash tags
			boolean isAdded = false;
			String hashTemp ="." ;
			for(int k = 0; k< current.getEventHashTag().length; k++) {
				hashTemp +=(current.getEventHashTag()[k] + ".");
			}
			for(int i = 1; i< parameterList.length;i++) {				
				if(isAdded==false&&(current.getEventName().toLowerCase().contains(parameterList[i].toLowerCase())||hashTemp.toLowerCase().contains(parameterList[i].toLowerCase()))){
					searchResults.add(current);
					isAdded = true;
					break;
				}				
			}
		}
																	
		//Sort 

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
		resultHashTags.add(0,priority);
		String startTimeDate=Logic.getStartTime(parameterList);
		String endTimeDate=Logic.getEndTime(parameterList);
		if(startTimeDate.equalsIgnoreCase(null)&&endTimeDate.equalsIgnoreCase(null))
		{
			ListOfEvent.add(new FloatingEvent(id,keywords,resultHashTags.toString(),reminderTime,false));
		}
	}

	public static void updateEvent(int index) {

	}
}
