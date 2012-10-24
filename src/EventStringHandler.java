import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class EventStringHandler {
	private static ArrayList<Event> listOfEvent = new ArrayList<Event>();
	private static ArrayList<String> listOfEventInString = new ArrayList<String>();
	private static String fileName;
	private static final int END_TIME_FIELD = 6;
	private static final int START_TIME_FIELD = 5;
	private static final String INVALID_TIME = "invalid";
	
	public static ArrayList<Event> getCurrentListOfEvent() {
		return listOfEvent;
	}
	
	public static void setUpDataFromDatabase(String nameOfFile) throws Exception {
		fileName = nameOfFile;
		listOfEventInString = DatabaseManager.setUpDatabase(fileName);
		setUpListOfEventInString();
	}
	
	public static void syncDataToDatabase(ArrayList<Event> list, String nameOfFile) throws IOException {
		listOfEvent = list;
		fileName = nameOfFile;
		DatabaseManager.syncToDatabase(getAllEventContent(), fileName);
	}
	
	private static ArrayList<String> getAllEventContent() {
		ArrayList<String> listOfEventToString = new ArrayList<String>();
		for(int index = 0; index < listOfEvent.size(); index++) {
			listOfEventToString.add(listOfEvent.get(index).toString());
		}
		return listOfEventToString;
	}
	
	private static void setUpListOfEventInString() {
		Iterator<String> iterator = listOfEventInString.iterator();
		
		while(iterator.hasNext()) {
			String currentLine = iterator.next();
			Event newEvent = getEventFromString(currentLine);
			listOfEvent.add(newEvent);
		}
		return;
	}
	
	public static Event getEventFromString(String eventInString) {
		String[] eventContent = eventInString.split("\\..");
		if(isFloatingEvent(eventContent)) {
			return getFloatingEvent(eventContent);
		} else if(isDeadlineEvent(eventContent)) {
			return getDeadlineEvent(eventContent);
		} else {
			return getTimedEvent(eventContent);
		}
	}
	
	private static boolean isFloatingEvent(String[] eventContent) {
		if(eventContent[END_TIME_FIELD].equalsIgnoreCase(INVALID_TIME) && 
				eventContent[START_TIME_FIELD].equalsIgnoreCase(INVALID_TIME)) {
			return true;
		}
		return false;
	}
	
	private static boolean isDeadlineEvent(String[] eventContent) {
		if(eventContent[END_TIME_FIELD].equalsIgnoreCase(INVALID_TIME)) {
			return true;
		} 
		return false;
	}
	
	private static Event getFloatingEvent(String[] eventContent) {
		Event newEvent = new FloatingEvent();
		newEvent.parse(eventContent);
		return newEvent;
	}
	
	private static Event getDeadlineEvent(String[] eventContent) {
		Event newEvent = new DeadlineEvent();
		newEvent.parse(eventContent);
		return newEvent;
	}
	
	private static Event getTimedEvent(String[] eventContent) {
		Event newEvent = new TimedEvent();
		newEvent.parse(eventContent);
		return newEvent;
	}
}
