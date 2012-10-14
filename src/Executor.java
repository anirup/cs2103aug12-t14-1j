import java.io.IOException;
import java.util.*;
import org.joda.time.Duration;
import org.joda.time.DateTime;
public class Executor {

	private static final String FORMAT_DATE = "yyyy-MM-ddTHH:mmZ";
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_UPDATE = "update";
	private static final String COMMAND_DONE = "done";
	private static final String COMMAND_SEARCH = "search";
	private static final String COMMAND_EXIT = "exit";
	private static final String COMMAND_UNDONE = "undone";
	private static final String COMMAND_UNDO = "undo";
	private static final String TIME_ZONE = "+8:00";
	
	private static Vector<Event> searchResults = new Vector<Event>();;
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
	public static void analyze(String userInput) throws IOException {
		
		String[] parameterList = userInput.split("\\..");
		String command = Logic.getCommand(parameterList);
		if (command.equalsIgnoreCase(COMMAND_ADD)) {
			ListOfArchive.add(new ActionArchiveAdd(analyzeAddInput(parameterList)));
			searchToFalse();
			previousCommand = COMMAND_ADD;
		} else if (command.equalsIgnoreCase(COMMAND_DELETE)) {
			if (getSearchState() == true && previousCommand == COMMAND_DELETE) {
				int index = Logic.getInteger(parameterList);
				ListOfArchive.add(new ActionArchiveDelete(ListOfEvent.get(index)));
				ListOfEvent.remove(index);
				previousCommand = COMMAND_DELETE;
				searchToFalse();
			} else if (getSearchState() == false) {
				analyzeAndSearch(parameterList);	
				previousCommand = COMMAND_DELETE;
				searchToTrue();
			}
		} else if (command.equalsIgnoreCase(COMMAND_UPDATE)) {
			if (getSearchState() == true && previousCommand == COMMAND_UPDATE) {
				int index = Logic.getInteger(parameterList);
				updateEvent(index);
				previousCommand = COMMAND_UPDATE;
				searchToFalse();
			} else if (getSearchState() == false) {
				analyzeAndSearch(parameterList);
				previousCommand = COMMAND_UPDATE;
				searchToTrue();
			} 
		} else if (command.equalsIgnoreCase(COMMAND_SEARCH)) {
			analyzeAndSearch(parameterList);
			previousCommand = COMMAND_SEARCH;
		} else if (command.equalsIgnoreCase(COMMAND_DONE)) {
			if (getSearchState() == true && previousCommand == COMMAND_DONE) {
				int index = Logic.getInteger(parameterList);
				markDone(index);
				previousCommand = COMMAND_DONE;
				searchToFalse();
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
			} else if (getSearchState() == false) {
				analyzeAndSearch(parameterList);
				previousCommand = COMMAND_UNDONE;
				searchToTrue();
			}
		} else if (command.equalsIgnoreCase(COMMAND_UNDO)) {
			undoLast();
			previousCommand = COMMAND_UNDONE;
		}else if(command.equalsIgnoreCase(COMMAND_EXIT)) {
			DatabaseManager.syncToDatabase(ListOfEvent.getListOfEventInString());
			System.exit(0);
		}
		//save file , exit

	}

	public static void undoLast() {
		ListOfArchive.undo();
	}

	public static void analyzeAndSearch(String[] parameterList) { // keywords // have to
		
		searchResults.clear();
		Vector<String> searchWords = new Vector<String>();
		searchWords = Logic.getHashTags(parameterList);
		String[] tempArr=(Logic.getKeyWords(parameterList)).split("\\s+");
		searchWords.addAll(Arrays.asList(tempArr)); 
		for(int i = 0; i <ListOfEvent.size(); i++ ) {
			boolean isChecked = false;
			String searchCheck = ".";
			for(int j=0;j<ListOfEvent.get(j).getEventHashTag().length;j++) {
				searchCheck+=ListOfEvent.get(j).getEventHashTag()[j];
				searchCheck+=".";
			}
			searchCheck+=ListOfEvent.get(i).getEventName();
			for(int k=0;k<searchWords.size();k++){
				if(searchCheck.contains(searchWords.get(k))&&isChecked==false){
					searchResults.add(ListOfEvent.get(i));
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

		
	public static Event analyzeAddInput(String[] parameterList) {
		String priority, keywords,id;
		Duration reminderTime;
		id=Logic.getEventID();
		keywords = Logic.getKeyWords(parameterList);
		priority = Logic.getPriority(parameterList);
		reminderTime = Logic.getReminderTime(parameterList);
		Vector<String> resultHashTags = Logic.getHashTags(parameterList);
		resultHashTags.add(0,priority);
		String[] hashArray = new String[resultHashTags.size()];
		resultHashTags.toArray(hashArray);
		String startTimeDate=Logic.getStartTime(parameterList);
		String endTimeDate=Logic.getEndTime(parameterList);
		Event eventToAdd;
		if(startTimeDate.equalsIgnoreCase(null)&&endTimeDate.equalsIgnoreCase(null))
		{
			//DateTime utc = new DateTime(System.currentTimeMillis(), DateTimeZone.);
			eventToAdd = new FloatingEvent(id,keywords,hashArray,null,false);
			ListOfEvent.add(eventToAdd);
		}
		
		else if(startTimeDate.equalsIgnoreCase(null)&&!(endTimeDate.equalsIgnoreCase(null))) {
			
			Clock time = new Clock(endTimeDate, FORMAT_DATE);
			DateTime reminder = time.toDate().minusSeconds((int)reminderTime.getStandardSeconds());
			String reminderString = reminder.getYear()+"-"+reminder.getMonthOfYear() + "-"+ reminder.getDayOfMonth()+"T"+reminder.getHourOfDay()+":"+reminder.getMinuteOfHour()+TIME_ZONE;
			eventToAdd = new DeadlineEvent(id,keywords,hashArray,new Clock(reminderString,FORMAT_DATE),false,time);
			ListOfEvent.add(eventToAdd);
		}
		else {
			
			Clock starting = new Clock(startTimeDate,FORMAT_DATE);
			Clock ending = new Clock(endTimeDate,FORMAT_DATE);
			DateTime reminder = ending.toDate().minusSeconds((int)reminderTime.getStandardSeconds());
			String reminderString = reminder.getYear()+"-"+reminder.getMonthOfYear() + "-"+ reminder.getDayOfMonth()+"T"+reminder.getHourOfDay()+":"+reminder.getMinuteOfHour()+TIME_ZONE;
			eventToAdd = new TimedEvent(id,keywords,hashArray,new Clock(reminderString,FORMAT_DATE),false,starting,ending);
			ListOfEvent.add(eventToAdd);
		}
		
		return eventToAdd;
	}

	public static void updateEvent(int index) {

		
	}
	
	public String printDataBase() {
		
		String str = "";
		for(int i = 0; i<ListOfEvent.size(); i++) {
			str += ListOfEvent.get(i).toString();
			str += '\n';
		}
		return str;
	}
}
