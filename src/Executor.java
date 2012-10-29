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
	//private static final String INPUT_SPLITTER = "\\..";
	private static final String EXPRESSION_WHITESPACE = "\\s+";
	//private static final String FORMAT_DATE = "yyyy-MM-dd'T'hh:mmZ";
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_UPDATE = "update";
	private static final String COMMAND_DONE = "done";
	private static final String COMMAND_SEARCH = "search";
	private static final String COMMAND_EXIT = "exit";
	private static final String COMMAND_UNDONE = "undone";
	private static final String COMMAND_UNDO = "undo";
	//private static final String TIME_ZONE = "+8:00";

	private static Vector<EventForSort> searchResults = new Vector<EventForSort>();
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

	public static int analyze(String userInput) throws IOException {
		PatternLib.setUpPattern();
		Vector<String> parameters=new Vector<String>();
		// String[] parameters = userInput.split(INPUT_SPLITTER);
		try
		{
			parameters = Logic.splitInput(userInput);
		}
		catch(Exception e)
		{
			if(Logic.getMessage()==0)
				return 9;
			else
				return Logic.getMessage();
		}
		String[] parameterList = { "-1", "-1", "-1", "-1", "-1", "-1" };
		Logic.setUp();
		try
		{
		ListOfEvent.syncDataToDatabase();
		}
		catch(Exception e)
		{
			return 10;
		}
		//Collections.sort(ListOfEvent.getCurrentListOfEvent(), sortByDate);
		ListOfEvent.sortByTime();
		for (int i = 0; i < parameters.size(); i++)
			parameterList[i] = parameters.get(i);
		try
		{
		String command = Logic.getCommand(parameterList);
		if (command.equalsIgnoreCase(COMMAND_ADD)
				|| command.equalsIgnoreCase(SHORTHAND_ADD)) {
			// ListOfActionArchive.add(new ActionArchiveAdd(
			analyzeAddInput(parameterList);
			searchToFalse();
			ListOfEvent.syncDataToDatabase();
			previousCommand = COMMAND_ADD;
			return 0;
		} else if (command.equalsIgnoreCase(COMMAND_DELETE)
				|| command.equalsIgnoreCase(SHORTHAND_DELETE)) {
			if (getSearchState() == true
					&& (previousCommand == COMMAND_DELETE || previousCommand == COMMAND_SEARCH)) {
				int index = Logic.getInteger(parameterList);
				 ListOfActionArchive.add(new ActionArchiveDelete(ListOfEvent
				 .remove(index)));
				//ListOfEvent.remove(index);
				ListOfEvent.syncDataToDatabase();
				previousCommand = COMMAND_DELETE;
				searchToFalse();
				return 1;
			} else if (getSearchState() == false) {
				analyzeAndSearch(parameterList);
				previousCommand = COMMAND_DELETE;
				searchToTrue();
			} 
		} else if (command.equalsIgnoreCase(COMMAND_UPDATE)
				|| command.equalsIgnoreCase(SHORTHAND_UPDATE)) {
			if (getSearchState() == true && previousCommand == COMMAND_UPDATE) {
				int index = Logic.getInteger(parameterList);
				// ListOfActionArchive.add(new ActionArchiveUpdate(null, null));
				updateEvent(index);
				previousCommand = COMMAND_UPDATE;
				searchToFalse();
				return 2;
			} else if (getSearchState() == false) {
				analyzeAndSearch(parameterList);
				previousCommand = COMMAND_UPDATE;
				searchToTrue();
			}
		} else if (command.equalsIgnoreCase(COMMAND_SEARCH)) {
			analyzeAndSearch(parameterList);
			previousCommand = COMMAND_SEARCH;
			searchToTrue();
		} else if (command.equalsIgnoreCase(COMMAND_DONE)) {
			if (getSearchState() == true && previousCommand == COMMAND_DONE) {
				int index = Logic.getInteger(parameterList);
				markDone(index);
				previousCommand = COMMAND_DONE;
				searchToFalse();
				return 4;
			} else if (getSearchState() == false) {
				analyzeAndSearch(parameterList);
				previousCommand = COMMAND_DONE;
				searchToTrue();
			}
		} else if (command.equalsIgnoreCase(COMMAND_UNDONE)) {
			if (getSearchState() == true && previousCommand == COMMAND_UNDONE) {
				int index = Logic.getInteger(parameterList);
				markNotDone(index);
				previousCommand = COMMAND_UNDONE;
				searchToFalse();
				return 3;
			} else if (getSearchState() == false) {
				analyzeAndSearch(parameterList);
				previousCommand = COMMAND_UNDONE;
				searchToTrue();
			}
		} else if (command.equalsIgnoreCase(COMMAND_UNDO)) {
			// undoLast();
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
		// save file , exit
		return 11;
	}

	/*
	 * public static void undoLast() { ListOfActionArchive.undo(); }
	 */
	public static void analyzeAndSearch(String[] parameterList) { // keywords //
																	// have to

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

	public static boolean analyzeAddInput(String[] parameterList) {
		
		String eventToAdd = Logic.getEventString(parameterList);
		ListOfEvent.add(eventToAdd);
		return true;
		
	}

	public static void updateEvent(int index) {

	}

	private static int returnPriorityValue(String p) {
		if (p.equalsIgnoreCase(PRIORITY_HIGH))
			return 0;
		else if (p.equalsIgnoreCase(PRIORITY_NORMAL))
			return 1;
		else if (p.equalsIgnoreCase(PRIORITY_LOW))
			return 2;
		return 1;
	}

	private static int getDateOrder(Event a, Event b) {
		if (a.getEventEndTime().isBefore(b.getEventEndTime()))
			return -1;
		else if (a.getEventEndTime().isAfter(b.getEventEndTime()))
			return 1;
		else
			return 0;
	}

	private static Comparator<Event> sortByPriority = new Comparator<Event>() {
		public int compare(Event a, Event b) {
			if (!getEventPriority(a).equals(
					getEventPriority(b)))
				return returnPriorityValue(getEventPriority(a))
						- returnPriorityValue(getEventPriority(b));
			else
				return getDateOrder(a, b);
		}
	};

	private static Comparator<Event> sortByDate = new Comparator<Event>() {
		public int compare(Event a, Event b) {
			if (!a.getEventEndTime().toString()
					.equals(b.getEventEndTime().toString()))
				return getDateOrder(a, b);
			else
				return returnPriorityValue(getEventPriority(a))
						- returnPriorityValue(getEventPriority(b));

		}
	};

	public static String printDataBase() {

		String str = STRING_NULL;
		//Collections.sort(ListOfEvent.getCurrentListOfEvent(), sortByDate);
		ListOfEvent.sortByTime();
		for (int i = 0; i < ListOfEvent.size(); i++) {
			if (!ListOfEvent.get(i).getClass().getName()
					.equals("FloatingEvent")) {
				str += i+".." +ListOfEvent.get(i).composeContentToDisplayInString();
				str += '\n';
			}
		}
		return str;
	}

	public static String printFloatingDataBase() {

		String str = STRING_NULL;
		Collections.sort(ListOfEvent.getCurrentListOfEvent(), sortByPriority);
		//ListOfEvent.sortByPriority();
		for (int i = 0; i < ListOfEvent.size(); i++) {
			if (ListOfEvent.get(i).getClass().getName().equals("FloatingEvent")) {
				str += i+".." +ListOfEvent.get(i).composeContentToDisplayInString();
				str += '\n';
			}
		}
		return str;
	}

	public static String printPriorityDataBase() {

		String str = STRING_NULL;
		//Collections.sort(ListOfEvent.getCurrentListOfEvent(), sortByPriority);
		ListOfEvent.sortByPriority();
		for (int i = 0; i < ListOfEvent.size(); i++) {
			if (!ListOfEvent.get(i).getClass().getName()
					.equals("FloatingEvent")) {
				str += i+".." + ListOfEvent.get(i).composeContentToDisplayInString();
				str += '\n';
			}
		}
		return str;
	}

	public static String printSearchResults() {
		String temp = STRING_NULL;
		for (int i = 0; i < searchResults.size(); i++) {
			temp += searchResults.get(i).index();
			temp += ".\t";
			temp += searchResults.get(i).event().composeContentToDisplayInString();
			temp += "\n";
		}
		return temp;
	}

	public static void loadDatabase() throws Exception {
		ListOfEvent.setUpDataFromDatabase();
	}

	public static void syncDatabase() throws IOException {
		ListOfEvent.syncDataToDatabase();
	}
	

	private static String getEventPriority(Event a) {
		return a.getEventHashTag().split("#")[1].trim().toLowerCase();
	}
}
