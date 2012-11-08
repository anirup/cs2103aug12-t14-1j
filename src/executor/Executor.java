package executor;

import java.util.*;

import logAndException.ExceptionHandler;
import logAndException.Log;
import logic.LogicAnalyzer;
import logic.LogicSplitter;
import logic.PatternLib;

import actionArchive.*;

import event.*;

public class Executor implements ListOfEventObserver {

	private static final String COMMAND_SWITCH_UPCOMING = "upcoming";
	private static final String COMMAND_BACK = "back";
	private static ArrayList<String> currentListOfUpcomingEventToDisplay = new ArrayList<String>();
	private static ArrayList<String> currentListOfFloatingEventToDisplay = new ArrayList<String>();
	private static ArrayList<String> searchResults = new ArrayList<String>();

	private static Executor _instance = new Executor();

	private Executor() {

	}

	public static Executor getInstance() {
		return _instance;
	}

	private static final String STRING_NULL = "";
	private static final String SHORTHAND_UPDATE = "u";
	private static final String SHORTHAND_DELETE = "-";
	private static final String SHORTHAND_ADD = "+";
	private static final String EXPRESSION_WHITESPACE = "\\s+";
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_UPDATE = "update";
	private static final String COMMAND_DONE = "done";
	private static final String COMMAND_SEARCH = "search";
	private static final String COMMAND_EXIT = "exit";
	private static final String COMMAND_UNDONE = "undone";
	private static final String COMMAND_UNDO = "undo";

	private static boolean searchState = false;
	private static String previousCommand = "Nothing";
	private static int userInputInteger;
	private static int updateIndex = -1;

	private static boolean getSearchState() {
		return searchState;
	}

	public static void searchToFalse() {
		searchState = false;
	}

	public static void searchToTrue() {
		searchState = true;
	}

	public static int analyze(String userInput) throws Exception {
		init();

		Vector<String> parameters = new Vector<String>();
		String[] parameterList = { "-1", "-1", "-1", "-1", "-1", "-1" };
		
		
		try {
			parameters = LogicSplitter.splitInput(userInput);
		} catch (Exception e) {
			return splittingError();
		}
		for (int i = 0; i < parameters.size(); i++)
			parameterList[i] = parameters.get(i);

		// Check if user has just entered a positive integer parameter, returns
		// -2 if not an integer, -1 if user entered 0
		userInputInteger = getUserInteger(parameterList);

		ListOfEvent.sortList();

		try {
			// Save to file before each operation
			ListOfEvent.syncDataToDatabase();
		} catch (Exception e) {
			ListOfEvent.formatListOfEvent();
			Log.toLog(2, ExceptionHandler.getException(10));
			return 10;
		}

		try {
			String command = LogicAnalyzer.getCommand(parameterList);
			if (isAdd(command)) {
				return executeAdd(parameterList);
			} else if (isDelete(command)) {
				return executeDelete(userInputInteger);

			} else if (isUpdate(command)) {
				return executeUpdate(parameterList);

			} else if (isSearch(command)) {
				executeSearch(parameterList);
			} else if (isDone(command)) {
				return executeDone();
			} else if (isUndone(command)) {
				return executeUndone();
			} else if (isUndo(command)) {
				return executeUndo();
			} else if (command.equalsIgnoreCase(COMMAND_EXIT)) {
				Log.toLog(0, ExceptionHandler.getException(7));
				System.exit(0);
				return 7;
			} else if (command.equalsIgnoreCase(COMMAND_BACK)) {
				return 15;
			} else if (command.trim().equalsIgnoreCase(COMMAND_SWITCH_UPCOMING)) {
				return 17;
			} else
				return 5;
		} catch (Exception e) {
			Log.toLog(2, ExceptionHandler.getException(9));
			return 9;
		}

		ListOfEvent.notifyObservers();
		return 11;

	}

	private static void init() {
		PatternLib.setUpPattern();
		LogicSplitter.setUp();
		LogicAnalyzer.setUp();
	}

	private static int splittingError() throws Exception {
		if (LogicSplitter.getMessage() == 0) {
			Log.toLog(2, ExceptionHandler.getException(9));
			return 9;
		} else
			return LogicSplitter.getMessage();
	}

	private static int getUserInteger(String[] parameterList) {
		return LogicAnalyzer.getInteger(parameterList) - 1;
	}
	
	public ArrayList<String> getFeedback(){
		return ListOfEvent.getFeedback();
	}
	

	private static boolean isUndo(String command) {
		return command.equalsIgnoreCase(COMMAND_UNDO);
	}

	private static int executeUndone() throws Exception {
		if (isValidIndex()) {
			if (getSearchState() == true) {
				if (userInputInteger < searchResults.size()) {
					ListOfEvent.markUndoneSearch(userInputInteger);
				} else {
					Log.toLog(2, ExceptionHandler.getException(19));
					return 19;
				}
			} else if (getSearchState() == false) {
				if (userInputInteger < ListOfEvent.size()) {
					ListOfEvent.markUndoneList(userInputInteger);
				} else {
					Log.toLog(2, ExceptionHandler.getException(19));
					return 19;
				}
			}
			Log.toLog(0, ExceptionHandler.getException(4));
			ListOfEvent.notifyObservers();
			return 4;
		}

		else {
			Log.toLog(2, ExceptionHandler.getException(20));
			return 20;
		}
	}

	private static boolean isUndone(String command) {
		return command.equalsIgnoreCase(COMMAND_UNDONE);
	}

	private static boolean isDone(String command) {
		return command.equalsIgnoreCase(COMMAND_DONE);
	}

	private static int executeDone() throws Exception {
		if (isValidIndex()) {
			if (getSearchState() == true) {
				if (userInputInteger < searchResults.size()) {
					ListOfEvent.markDoneSearch(userInputInteger);
				} else {
					Log.toLog(2, ExceptionHandler.getException(19));
					return 19;
				}
			} else if (getSearchState() == false) {
				if (userInputInteger < ListOfEvent.size()) {
					ListOfEvent.markDoneList(userInputInteger);
				} else {
					Log.toLog(2, ExceptionHandler.getException(19));
					return 19;
				}
			} else {
				Log.toLog(2, ExceptionHandler.getException(20));
				return 20;
			}
			Log.toLog(0, ExceptionHandler.getException(3));
			ListOfEvent.notifyObservers();
			return 3;
		} else {
			Log.toLog(2, ExceptionHandler.getException(20));
			return 20;
		}
	}

	private static boolean isValidIndex() {
		return userInputInteger > -1;
	}

	private static boolean isSearch(String command) {
		return command.equalsIgnoreCase(COMMAND_SEARCH);
	}

	private static boolean isAdd(String command) {
		return command.equalsIgnoreCase(COMMAND_ADD)
				|| command.equalsIgnoreCase(SHORTHAND_ADD);
	}

	private static boolean isDelete(String command) {
		return command.equalsIgnoreCase(COMMAND_DELETE)
				|| command.equalsIgnoreCase(SHORTHAND_DELETE);
	}

	private static boolean isUpdate(String command) {
		return command.equalsIgnoreCase(COMMAND_UPDATE)
				|| command.equalsIgnoreCase(SHORTHAND_UPDATE);
	}

	private static int executeUpdate(String[] parameterList) throws Exception {
		try {
			updateIndex = Integer
					.parseInt(parameterList[parameterList.length - 1]) - 1;
		} catch (Exception e) {
			Log.toLog(0, ExceptionHandler.getException(2));
			return 9;
		}
		ArrayList<Event> changedEvents;
		String eventToAdd = LogicAnalyzer.getEventString(parameterList);
		if (searchState == false) {
			changedEvents = ListOfEvent.updateList(updateIndex, eventToAdd);
		} else {
			changedEvents = ListOfEvent.updateSearch(updateIndex, eventToAdd);
		}
		ListOfActionArchive.add(new ActionArchiveUpdate(changedEvents.get(0),
				changedEvents.get(1)));
		updateIndex = -1;
		Log.toLog(0, ExceptionHandler.getException(2));
		ListOfEvent.notifyObservers();
		return 2;
	}

	private static int executeUndo() throws Exception {
		undoLast();
		previousCommand = COMMAND_UNDO;
		Log.toLog(0, ExceptionHandler.getException(6));
		ListOfEvent.notifyObservers();
		return 6;
	}

	private static void executeSearch(String[] parameterList) {
		analyzeAndSearch(parameterList);
		ListOfEvent.notifyObservers();
	}

	private static int executeDelete(int userInputInteger) throws Exception {
		assert (userInputInteger > -1);
		if (userInputInteger > -1) {
			if (searchState == true) {
				if (userInputInteger < searchResults.size()) {
					ListOfActionArchive.add(new ActionArchiveDelete(ListOfEvent
							.removeSearch(userInputInteger)));
				} else {
					Log.toLog(2, ExceptionHandler.getException(19));
					return 19;
				}
			} else {
				if (userInputInteger < ListOfEvent.size()) {
					ListOfActionArchive.add(new ActionArchiveDelete(ListOfEvent
							.removeList(userInputInteger)));
				} else {
					Log.toLog(2, ExceptionHandler.getException(19));
					return 19;
				}
			}
		} else {
			Log.toLog(2, ExceptionHandler.getException(20));
			return 20;
		}
		Log.toLog(0, ExceptionHandler.getException(1));
		ListOfEvent.notifyObservers();
		return 1;
	}

	private static int executeAdd(String[] parameterList) throws Exception {
		Event newEvent = analyzeAddInput(parameterList);
		if (newEvent != null) {
			ListOfActionArchive.add(new ActionArchiveAdd(newEvent));			
			try {
				// Saving to file after add
				ListOfEvent.syncDataToDatabase();
			} catch (Exception e) {
				// ListOfEvent.formatListOfEvent();
				Log.toLog(2, ExceptionHandler.getException(10));
				return 10;
			}
			previousCommand = COMMAND_ADD;
			Log.toLog(0, ExceptionHandler.getException(0));
			ListOfEvent.notifyObservers();
			return 0;
		} else {
			previousCommand = COMMAND_ADD;
			Log.toLog(2, ExceptionHandler.getException(9));
			return 9;
		}
	}

	private static void undoLast() {
		ListOfActionArchive.undo();
	}
	
	public static void analyzeAndSearch(String[] parameterList) {
		searchResults.clear();
		Vector<String> searchWords = getSearchWords(parameterList);
		commenceSearch(searchWords);
		ListOfEvent.notifyObservers();
	}

	private static Vector<String> getSearchWords(String[] parameterList) {
		Vector<String> searchWords = new Vector<String>();
		searchWords = LogicAnalyzer.getHashTags(parameterList);
		String[] tempArr = (LogicAnalyzer.getKeyWords(parameterList)).trim()
				.split(EXPRESSION_WHITESPACE);
		searchWords.addAll(Arrays.asList(tempArr));
		return searchWords;
	}

	private static void commenceSearch(Vector<String> searchWords) {
		ListOfEvent.searchInNameAndHashTags(searchWords);
	}

	public static Event analyzeAddInput(String[] parameterList) {

		String eventToAdd = LogicAnalyzer.getEventString(parameterList);
		return ListOfEvent.add(eventToAdd);

	}

	public static ArrayList<String> printDataBase() {

		return currentListOfUpcomingEventToDisplay;
	}

	public static ArrayList<String> printFloatingDataBase() {

		return currentListOfFloatingEventToDisplay;
	}

	public void updateListOfEvent() {
		ListOfEvent.sortList();
		currentListOfFloatingEventToDisplay = ListOfEvent
				.getListOfFloatingEventToDisplayInString();
		currentListOfUpcomingEventToDisplay = ListOfEvent
				.getListOfEventToDisplayInString();
		searchResults = ListOfEvent.getSearchResultsToDisplayInString();
	}

	public static ArrayList<String> printSearchResults() {
		return searchResults;
	}

	public static void loadDatabase() throws Exception {
		ListOfEvent.setUpDataFromDatabase();
	}

	public static void formatDatabase() throws Exception {
		ListOfEvent.formatListOfEvent();
	}

}