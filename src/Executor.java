import java.io.IOException;
import java.util.*;


public class Executor {

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

	private static Vector<EventForSort> searchResults = new Vector<EventForSort>();
	
	private static int displaySplitIndex = 0;
	private static boolean searchState = false;
	private static String previousCommand = "Nothing";

	private static boolean getSearchState() {
		return searchState;
	}

	private static void searchToFalse() {
		searchState = false;
	}

	private static void searchToTrue() {
		searchState = true;
	}

	public static int analyze(String userInput) throws Exception {
		PatternLib.setUpPattern();
		Logic.setUp();
		Vector<String> parameters = new Vector<String>();
		// String[] parameters = userInput.split(INPUT_SPLITTER);
		
		String[] parameterList = { "-1", "-1", "-1", "-1", "-1", "-1" };
		Logic.setUp();
		for (int i = 0; i < parameters.size(); i++)
			parameterList[i] = parameters.get(i);
		
		//Check if user has just entered a positive integer parameter, returns -2 if not an integer, -1 if user entered 0
		int userInputInteger = Logic.getInteger(parameterList)-1;
		
		try
		{
			parameters = Logic.splitInput(userInput);
		} catch (Exception e) {
			if (Logic.getMessage() == 0)
				return 9;
			else
				return Logic.getMessage();
		}	
				
		// Collections.sort(ListOfEvent.getCurrentListOfEvent(), sortByDate);
		ListOfEvent.sortByTime();		
				
		try
		{
		String command = Logic.getCommand(parameterList);
		if (command.equalsIgnoreCase(COMMAND_ADD)
				|| command.equalsIgnoreCase(SHORTHAND_ADD)) {
			 ListOfActionArchive.add(new ActionArchiveAdd(analyzeAddInput(parameterList)));
				searchToFalse();
				try {
			//Saving to file after add
					ListOfEvent.syncDataToDatabase();
				} catch (Exception e) {
					ListOfEvent.formatListOfEvent();
					return 10;
				}
				previousCommand = COMMAND_ADD;
				return 0;
			} else if (command.equalsIgnoreCase(COMMAND_DELETE)
					|| command.equalsIgnoreCase(SHORTHAND_DELETE)) {
				
				assert(userInputInteger > -1);
				if(userInputInteger > -1 ) {
					if(searchState == true){
						ListOfActionArchive.add(new ActionArchiveDelete(searchResults.get(userInputInteger).event()));
						ListOfEvent.remove(searchResults.get(userInputInteger).index());
					}
					else {						
						ListOfEvent.remove(userInputInteger);
					}
				}
				
				return 1;
			 
			 
		} else if (command.equalsIgnoreCase(COMMAND_UPDATE)
				|| command.equalsIgnoreCase(SHORTHAND_UPDATE)) {
			
			Event eventEdit = null;
			if(userInputInteger > 0 ){
				if(searchState == true){					
					eventEdit = searchResults.get(userInputInteger).event(); 
				}
				else {
					eventEdit=ListOfEvent.get(userInputInteger); 
				}
				previousCommand = COMMAND_UPDATE;
				getUpdateEventinString(userInputInteger); 
				
			} else {
				assert(previousCommand.equals(COMMAND_UPDATE));
				parameterList[0] = COMMAND_ADD;
				ListOfActionArchive.add(new ActionArchiveUpdate(eventEdit,analyzeAddInput(parameterList)));
				ListOfEvent.update(searchResults.get(userInputInteger).index(),eventEdit);
				previousCommand = COMMAND_UPDATE;
			}
			
				
			/*if (getSearchState() == true && previousCommand == COMMAND_UPDATE) {				
				// ListOfActionArchive.add(new ActionArchiveUpdate(null, null));
				updateEvent(userInputInteger);
				previousCommand = COMMAND_UPDATE;
				searchToFalse();
				return 2;
			} else if (getSearchState() == false) {
				analyzeAndSearch(parameterList);
				previousCommand = COMMAND_UPDATE;
				searchToTrue();						
			}*/
		} else if (command.equalsIgnoreCase(COMMAND_SEARCH)) {
			analyzeAndSearch(parameterList);
			previousCommand = COMMAND_SEARCH;
			searchToTrue();
		} else if (command.equalsIgnoreCase(COMMAND_DONE)) {
			if(userInputInteger > -1) {
				if (getSearchState() == true) {					
					markDone(searchResults.get(userInputInteger).index());				
					return 4;
				} else if (getSearchState() == false) {
					markDone(userInputInteger);
				}
			}
		} else if (command.equalsIgnoreCase(COMMAND_UNDONE)) {
			if(userInputInteger > -1) {
				if (getSearchState() == true) {					
					markDone(searchResults.get(userInputInteger).index());				
					return 4;
				} else if (getSearchState() == false) {
					markDone(userInputInteger);
				}
			}
			
		} else if (command.equalsIgnoreCase(COMMAND_UNDO)) {
			undoLast();
			previousCommand = COMMAND_UNDONE;
			return 6;
		} else if (command.equalsIgnoreCase(COMMAND_EXIT)) {
			System.exit(0);
			return 7;
		}
		else 
			return 5;
		}
		catch(Exception e)
		{
			return 9;
		}
		
		
		try {				
			//Save to file before each operation
			ListOfEvent.syncDataToDatabase();
		} catch (Exception e) {
			ListOfEvent.formatListOfEvent();
			return 10;
		}
		return 11;
		
		
	}

	private static void undoLast() {
		ListOfActionArchive.undo();		
	}

	private static String getUpdateEventinString(int index) {
	String event = ListOfEvent.get(index).composeContentToDisplayInString();
	String[] parameters = event.split("\\..");
	String out = STRING_NULL;
	for(int i = 0; i< parameters.length; i++) {
		out+= (parameters + " ");		
	}
	return out;	
	}

	
	public static void analyzeAndSearch(String[] parameterList){ 
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
		/*int size = ListOfEvent.size();
		for (int i = 0; i < size; i++) {
			if (searchWords.isEmpty())
				break;
			boolean isChecked = false;
			String searchCheck = STRING_NULL;
			String[] tags = ListOfEvent.get(i).getEventHashTag().split("#");
			for (int j = 0; j < tags.length; j++) {
				searchCheck += tags[j];
				searchCheck += ".";
			}
			searchCheck += ListOfEvent.get(i).getEventName();
			for (int k = 0; k < searchWords.size(); k++) {
				if (searchCheck.toLowerCase().contains(
						searchWords.get(k).toLowerCase())
						&& isChecked == false) {
					searchResults.add(new EventForSort(i, ListOfEvent.get(i)));
					break;
				}
			}
		}*/
		searchResults.addAll(ListOfEvent.searchInNameAndHashTags(searchWords));
	}

	public static void markNotDone(int index) {
		ListOfEvent.markUndone(index);
	}

	public static void markDone(int index) {
		ListOfEvent.markDone(index);
	}

	public static Event analyzeAddInput(String[] parameterList) {

		String eventToAdd = Logic.getEventString(parameterList);		
		return ListOfEvent.add(eventToAdd);

	}
		
	public static ArrayList<String> printDataBase() {

		//String str = STRING_NULL;
		// Collections.sort(ListOfEvent.getCurrentListOfEvent(), sortByDate);
		/*ListOfEvent.sortByTime();
		for (int i = 0; i < ListOfEvent.size(); i++) {
			if (!ListOfEvent.get(i).getClass().getName()
					.equals("FloatingEvent")) {
				str += i + ".."
						+ ListOfEvent.get(i).composeContentToDisplayInString();
				str += '\n';
			}
		}
		return str;*/
		ListOfEvent.sortByTime();
		return ListOfEvent.getListOfEventToDisplayInString();
	}

	public static ArrayList<String> printFloatingDataBase() {

		/*String str = STRING_NULL;
		//Collections.sort(ListOfEvent.getCurrentListOfEvent(), sortByPriority);
		// ListOfEvent.sortByPriority();
		for (int i = 0; i < ListOfEvent.size(); i++) {
			if (ListOfEvent.get(i).getClass().getName().equals("FloatingEvent")) {
				str += i + ".."
						+ ListOfEvent.get(i).composeContentToDisplayInString();
				str += '\n';
			}
		}*/
		//return str;
		ListOfEvent.sortByTime();
		return ListOfEvent.getListOfFloatingEventsInString(); 
	}

	public static String printPriorityDataBase() {

		String str = STRING_NULL;		
		ListOfEvent.sortByPriority();
		for (int i = 0; i < ListOfEvent.size(); i++) {
			if (!ListOfEvent.get(i).getClass().getName()
					.equals("FloatingEvent")) {
				str += i + ".."
						+ ListOfEvent.get(i).composeContentToDisplayInString();
				str += '\n';
			}
		}
		return str;
	}

	public static String printSearchResults() {
		String temp = STRING_NULL;
		for (int i = 0; i < searchResults.size(); i++) {
			temp += i+1;
			temp += ".\t";
			temp += searchResults.get(i).event().composeContentToDisplayInString();
			temp += "\n";
		}
		return temp;
	}

	public static void loadDatabase() throws Exception {
		ListOfEvent.setUpDataFromDatabase();
	}

	public static void formatDatabase() throws Exception {
		ListOfEvent.formatDatabase();
	}

	// public static void syncDatabase() throws IOException {
	// ListOfEvent.syncDataToDatabase();
	// }


}
