import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class DatabaseManager {
	private static final String fileName = "What2Do.txt";

	private static BufferedReader reader;
	private static File database;
	
	// this is the method u will use at the start of the program to retrieve data from the text file
	public static LinkedList<Event> setUpDatabase() throws IOException {
		database = new File(fileName);
		
		if (!database.exists()) {
			database.createNewFile();
		}
		
		reader = new BufferedReader(new FileReader(fileName));
		
		return retrieveDatabase();
	}
	
	// you guys dont need to use the methods below, so I set them to private
	
	private static LinkedList<Event> retrieveDatabase() throws IOException {
		LinkedList<Event> listOfEvent = new LinkedList<Event>();
		
		String currentLine;
		
		while((currentLine = reader.readLine()) != null) {
			Event newEvent = readFileAndRetrieveEvent(currentLine);
			listOfEvent.add(newEvent);
		}
		
		return listOfEvent;
	}
	
	private static Event readFileAndRetrieveEvent(String currentLine) throws IOException {
		Event newEvent = null;
		
		if (currentLine.equals(FloatingEvent.FLOATING_EVENT_INDICATOR)) {
			newEvent = retrieveFloatingEventFromFile();
		} else if (currentLine.equals(TimedEvent.TIMED_EVENT_INDICATOR)) {
			newEvent = retrieveTimedEventFromFile();
		} else if (currentLine.equals(DeadlineEvent.DEADLINE_EVENT_INDICATOR)) {
			newEvent = retrieveDeadLineEventFromFile();
		}	
		return newEvent;
	}
	
	private static Event retrieveDeadLineEventFromFile() throws IOException {
		Event newEvent = retrieveEventBasicInfo();
		Clock newEventTime = retrieveTime();
		
		Event newDeadlineEvent = new DeadlineEvent(newEvent, newEventTime);
		
		return newDeadlineEvent;
	}

	private static Event retrieveFloatingEventFromFile() throws IOException {		
		Event newEvent = retrieveEventBasicInfo();

		Event newFloatingEvent = new FloatingEvent(newEvent);
		
		return newFloatingEvent;
	}
	
	private static Event retrieveTimedEventFromFile() throws IOException {
		Event newEvent = retrieveEventBasicInfo();
		Clock newEventStartTime = retrieveTime();
		Clock newEventEndTime = retrieveTime();
		
		Event newTimedEvent = new TimedEvent(newEvent, newEventStartTime, newEventEndTime);
		
		return newTimedEvent;
	}
	
	private static Event retrieveEventBasicInfo() throws IOException {
		String eventID = reader.readLine();
		String eventName = reader.readLine();
		String[] eventHashTag = reader.readLine().split(" ");
		Clock eventReminder = retrieveTime();
		boolean isDone = retrieveEventIsDone();
		
		Event newEvent = new Event(eventID, eventName, eventHashTag, eventReminder, isDone);
		return newEvent;
	}
	
	private static boolean retrieveEventIsDone() throws IOException {
		String isDone = reader.readLine();
		
		if (isDone.equalsIgnoreCase("true")) {
			return true;
		}
		
		return false;
	}
	
	private static Clock retrieveTime() throws IOException {
		String time = reader.readLine();
		String dateFormat = reader.readLine();
	
		Clock newTime = new Clock(time, dateFormat);
		return newTime;
	}
	
	public static void updateDatabase(Event eventToUpdate) throws IOException {
		eventToUpdate.writeEventToFile(database);
	}
}
