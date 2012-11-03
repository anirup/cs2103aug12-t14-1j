package Project;

import java.io.IOException;
import java.util.*;

public class Executor implements ListOfEventObserver {

	private static ArrayList<String> currentListOfUpcomingEventToDisplay = new ArrayList<String>();
	private static ArrayList<String> currentListOfFloatingEventToDisplay = new ArrayList<String>();
	private static ArrayList<String> searchResults = new ArrayList<String>();

	private static final String PRIORITY_LOW = "Low";
	private static final String PRIORITY_NORMAL = "Normal";
	private static final String PRIORITY_HIGH = "high";
	private static final String STRING_NULL = "";
	private static final String SHORTHAND_UPDATE = "u";
	private static final String SHORTHAND_DELETE = "-";
	private static final String SHORTHAND_ADD = "+";
	// private static final String INPUT_SPLITTER = "\\..";
	private static final String EXPRESSION_WHITESPACE = "\\s+";
	// private static final String FORMAT_DATE = "yyyy-MM-dd'T'hh:mmZ";
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_UPDATE = "update";
	private static final String COMMAND_DONE = "done";
	private static final String COMMAND_SEARCH = "search";
	private static final String COMMAND_EXIT = "exit";
	private static final String COMMAND_UNDONE = "undone";
	private static final String COMMAND_UNDO = "undo";
	// private static final String TIME_ZONE = "+8:00";

	// private static Vector<EventForSort> searchResults = new
	// Vector<EventForSort>();

	private static int displaySplitIndex = 0;
	private static boolean searchState = false;
	private static String previousCommand = "Nothing";

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
		PatternLib.setUpPattern();
		Logic.setUp();
		Vector<String> parameters = new Vector<String>();
		// String[] parameters = userInput.split(INPUT_SPLITTER);

		String[] parameterList = { "-1", "-1", "-1", "-1", "-1", "-1" };
		Logic.setUp();

		// Check if user has just entered a positive integer parameter, returns
		// -2 if not an integer, -1 if user entered 0
		int userInputInteger = Logic.getInteger(parameterList) - 1;

		try {
			parameters = Logic.splitInput(userInput);
		} catch (Exception e) {
			if (Logic.getMessage() == 0) {
				Log.toLog(2, ExceptionHandler.getException(9));
				return 9;
			} else
				return Logic.getMessage();
		}
		for (int i = 0; i < parameters.size(); i++)
			parameterList[i] = parameters.get(i);

		// Collections.sort(ListOfEvent.getCurrentListOfEvent(), sortByDate);
		ListOfEvent.sortList();

		try {
			String command = Logic.getCommand(parameterList);
			if (command.equalsIgnoreCase(COMMAND_ADD)
					|| command.equalsIgnoreCase(SHORTHAND_ADD)) {
				ListOfActionArchive.add(new ActionArchiveAdd(
						analyzeAddInput(parameterList)));
				try {
					// Saving to file after add
					ListOfEvent.syncDataToDatabase();
				} catch (IOException e) {
				} catch (Exception e) {
					// ListOfEvent.formatListOfEvent();
					Log.toLog(2, ExceptionHandler.getException(10));
					return 10;
				}
				previousCommand = COMMAND_ADD;
				Log.toLog(0, ExceptionHandler.getException(0));
				return 0;
			} else if (command.equalsIgnoreCase(COMMAND_DELETE)
					|| command.equalsIgnoreCase(SHORTHAND_DELETE)) {

				assert (userInputInteger > -1);
				if (userInputInteger > -1) {
					if (searchState == true) {
						ListOfActionArchive.add(new ActionArchiveDelete(
								ListOfEvent.removeSearch(userInputInteger)));
					} else {
						ListOfEvent.removeList(userInputInteger);
					}
				}
				Log.toLog(0, ExceptionHandler.getException(1));
				return 1;

			} else if (command.equalsIgnoreCase(COMMAND_UPDATE)
					|| command.equalsIgnoreCase(SHORTHAND_UPDATE)) {

				Event eventEdit = null;
				if (userInputInteger > 0) {
					if (searchState == true) {
						// eventEdit =
						// searchResults.get(userInputInteger).event();
					} else {
						eventEdit = ListOfEvent.get(userInputInteger);
					}
					previousCommand = COMMAND_UPDATE;
					getUpdateEventinString(userInputInteger);

				} else {
					assert (previousCommand.equals(COMMAND_UPDATE));
					parameterList[0] = COMMAND_ADD;
					ListOfActionArchive.add(new ActionArchiveUpdate(eventEdit,
							analyzeAddInput(parameterList)));
					// ListOfEvent.update(searchResults.get(userInputInteger).index(),eventEdit);
					previousCommand = COMMAND_UPDATE;
					Log.toLog(0, ExceptionHandler.getException(2));
					return 2;
				}

			} else if (command.equalsIgnoreCase(COMMAND_SEARCH)) {
				analyzeAndSearch(parameterList);
				searchToTrue();
			} else if (command.equalsIgnoreCase(COMMAND_DONE)) {
				if (userInputInteger > -1) {
					if (getSearchState() == true) {
						ListOfEvent.markDoneSearch(userInputInteger);
						Log.toLog(0, ExceptionHandler.getException(3));
						return 3;
					} else if (getSearchState() == false) {
						ListOfEvent.markDoneList(userInputInteger);
					}
				}
			} else if (command.equalsIgnoreCase(COMMAND_UNDONE)) {
				if (userInputInteger > -1) {
					if (getSearchState() == true) {
						ListOfEvent.markUndoneSearch(userInputInteger);
						Log.toLog(0, ExceptionHandler.getException(4));
						return 4;
					} else if (getSearchState() == false) {
						ListOfEvent.markUndoneList(userInputInteger);
					}
				}

			} else if (command.equalsIgnoreCase(COMMAND_UNDO)) {
				undoLast();
				previousCommand = COMMAND_UNDONE;
				Log.toLog(0, ExceptionHandler.getException(6));
				return 6;
			} else if (command.equalsIgnoreCase(COMMAND_EXIT)) {
				Log.toLog(0, ExceptionHandler.getException(7));
				System.exit(0);
				return 7;
			} else if (command.equalsIgnoreCase("back")) {
				return 15;
			} else
				return 5;
		} catch (Exception e) {
			Log.toLog(2, ExceptionHandler.getException(9));
			return 9;
		}

		try {
			// Save to file before each operation
			ListOfEvent.syncDataToDatabase();
		} catch (Exception e) {
			ListOfEvent.formatListOfEvent();
			Log.toLog(2, ExceptionHandler.getException(10));
			return 10;
		}
		
		ListOfEvent.notifyObservers();
		return 11;

	}

	private static void undoLast() {
		ListOfActionArchive.undo();
	}

	private static String getUpdateEventinString(int index) {
		String event = ListOfEvent.get(index).composeContentToDisplayInString();
		String[] parameters = event.split("\\..");
		String out = STRING_NULL;
		for (int i = 0; i < parameters.length; i++) {
			out += (parameters + " ");
		}
		return out;
	}

	public static void analyzeAndSearch(String[] parameterList) {
		searchResults.clear();
		Vector<String> searchWords = getSearchWords(parameterList);
		commenceSearch(searchWords);
		Collections.sort(searchResults);
	}

	private static Vector<String> getSearchWords(String[] parameterList) {
		Vector<String> searchWords = new Vector<String>();
		searchWords = Logic.getHashTags(parameterList);
		String[] tempArr = (Logic.getKeyWords(parameterList)).trim().split(
				EXPRESSION_WHITESPACE);
		searchWords.addAll(Arrays.asList(tempArr));
		return searchWords;
	}

	private static void commenceSearch(Vector<String> searchWords) {
		/*
		 * int size = ListOfEvent.size(); for (int i = 0; i < size; i++) { if
		 * (searchWords.isEmpty()) break; boolean isChecked = false; String
		 * searchCheck = STRING_NULL; String[] tags =
		 * ListOfEvent.get(i).getEventHashTag().split("#"); for (int j = 0; j <
		 * tags.length; j++) { searchCheck += tags[j]; searchCheck += "."; }
		 * searchCheck += ListOfEvent.get(i).getEventName(); for (int k = 0; k <
		 * searchWords.size(); k++) { if (searchCheck.toLowerCase().contains(
		 * searchWords.get(k).toLowerCase()) && isChecked == false) {
		 * searchResults.add(new EventForSort(i, ListOfEvent.get(i))); break; }
		 * } }
		 */
		/*
		 * int size = ListOfEvent.size(); for (int i = 0; i < size; i++) { if
		 * (searchWords.isEmpty()) break; boolean isChecked = false; String
		 * searchCheck = STRING_NULL; String[] tags =
		 * ListOfEvent.get(i).getEventHashTag().split("#"); for (int j = 0; j <
		 * tags.length; j++) { searchCheck += tags[j]; searchCheck += "."; }
		 * searchCheck += ListOfEvent.get(i).getEventName(); for (int k = 0; k <
		 * searchWords.size(); k++) { if (searchCheck.toLowerCase().contains(
		 * searchWords.get(k).toLowerCase()) && isChecked == false) {
		 * searchResults.add(new EventForSort(i, ListOfEvent.get(i))); break; }
		 * } }
		 */
		ListOfEvent.searchInNameAndHashTags(searchWords);
	}

	public static Event analyzeAddInput(String[] parameterList) {

		String eventToAdd = Logic.getEventString(parameterList);
		return ListOfEvent.add(eventToAdd);

	}

	public static ArrayList<String> printDataBase() {

		return currentListOfUpcomingEventToDisplay;
	}

	public static ArrayList<String> printFloatingDataBase() {

		return currentListOfFloatingEventToDisplay;
	}

	public void updateListOfEvent() {
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
