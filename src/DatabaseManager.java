import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedList;


public class DatabaseManager {
	private static final String fileName = "What2Do.txt";

	private static BufferedReader reader;
	private static File database;
	private static RandomAccessFile randomAccessFile;
	private static String[] eventContent;
	
	private static final int INDEX_FOR_EVENT_ID = 0;
	private static final int INDEX_FOR_EVENT_NAME = 1;
	private static final int INDEX_FOR_EVENT_HASHTAG = 2;
	private static final int INDEX_FOR_EVENT_REMINDER_TIME = 3;
	private static final int INDEX_FOR_EVENT_REMINDER_DATEFORMAT = 4;
	private static final int INDEX_FOR_EVENT_ISDONE = 5;
	private static final int INDEX_FOR_EVENT_START_TIME = 6;
	private static final int INDEX_FOR_EVENT_START_TIME_DATEFORMAT = 7;
	private static final int INDEX_FOR_EVENT_END_TIME = 8;
	private static final int INDEX_FOR_EVENT_END_TIME_DATEFORMAT = 9;
	
	private static final int LENGTH_OF_FLOATING_EVENT = 6;
	private static final int LENGTH_OF_DEADLINE_EVENT = 8;
	private static final int LENGTH_OF_TIMED_EVENT = 10;
	
	// below are the two methods to set up the database at the start of the program, 
	// and a sync method to sync current list of event to the database events
	public static LinkedList<Event> setUpDatabase() throws IOException {
		database = new File(fileName);
		
		if (!database.exists()) {
			database.createNewFile();
		}
		
		reader = new BufferedReader(new FileReader(fileName));
		
		return retrieveDatabase();
	}
	
	public static void syncToDatabase() throws IOException {
		LinkedList<Event> currentListOfEvent = ListOfEvent.getCurrentListOfEvent();
		setUpRandomAccessFile();
	
		Iterator<Event> iterator = currentListOfEvent.iterator();
		
		while(iterator.hasNext()) {
			Event eventToSync = iterator.next();
			writeToDatabase(eventToSync);
		}
		
		closeRandomAccessFile();
		
		return;
	}
	
	//----------------------------------------------------------------------------------------------	
	private static LinkedList<Event> retrieveDatabase() throws IOException {
		LinkedList<Event> listOfEvent = new LinkedList<Event>();
		
		String currentLine;
		
		while((currentLine = reader.readLine()) != null) {
			Event newEvent = retrieveEvent(currentLine);
			listOfEvent.add(newEvent);
		}
		
		return listOfEvent;
	}
	
	private static Event retrieveEvent(String currentLine) throws IOException {
		eventContent = currentLine.split(Event.SPLITTER);
		Event newEvent = null;
		
		if (eventContent.length == LENGTH_OF_FLOATING_EVENT) {
			newEvent = retrieveFloatingEventFromFile();
		} else if (eventContent.length == LENGTH_OF_TIMED_EVENT) {
			newEvent = retrieveTimedEventFromFile();
		} else if (eventContent.length == LENGTH_OF_DEADLINE_EVENT) {
			newEvent = retrieveDeadLineEventFromFile();
		}	
		return newEvent;
	}
	
	private static Event retrieveDeadLineEventFromFile() throws IOException {
		Event newEvent = retrieveEventBasicInfo();
		String eventTime = eventContent[INDEX_FOR_EVENT_START_TIME];
		String eventTimeDateFormat = eventContent[INDEX_FOR_EVENT_START_TIME_DATEFORMAT];
		Clock newEventTime = retrieveTime(eventTime, eventTimeDateFormat);
		
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
		String eventStartTime = eventContent[INDEX_FOR_EVENT_START_TIME];
		String eventStartTimeDateFormat = eventContent[INDEX_FOR_EVENT_START_TIME_DATEFORMAT];
		String eventEndTime = eventContent[INDEX_FOR_EVENT_END_TIME];
		String eventEndTimeDateFormat = eventContent[INDEX_FOR_EVENT_END_TIME_DATEFORMAT];
		
		Clock newEventStartTime = retrieveTime(eventStartTime, eventStartTimeDateFormat);
		Clock newEventEndTime = retrieveTime(eventEndTime, eventEndTimeDateFormat);
		Event newTimedEvent = new TimedEvent(newEvent, newEventStartTime, newEventEndTime);
		return newTimedEvent;
	}
	
	private static Event retrieveEventBasicInfo() throws IOException {
		String eventID = eventContent[INDEX_FOR_EVENT_ID];
		String eventName = eventContent[INDEX_FOR_EVENT_NAME];
		String[] eventHashTag = eventContent[INDEX_FOR_EVENT_HASHTAG].split(Event.SPACE);
		String reminderTime = eventContent[INDEX_FOR_EVENT_REMINDER_TIME];
		String reminderDateFormat = eventContent[INDEX_FOR_EVENT_REMINDER_DATEFORMAT];
		Clock eventReminder = retrieveTime(reminderTime, reminderDateFormat);
		boolean isDone = retrieveEventIsDone();
		
		Event newEvent = new Event(eventID, eventName, eventHashTag, eventReminder, isDone);
		return newEvent;
	}
	
	private static boolean retrieveEventIsDone() throws IOException {
		String isDone = eventContent[INDEX_FOR_EVENT_ISDONE];
		if (isDone.trim().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}
	
	private static Clock retrieveTime(String time, String dateFormat) throws IOException {
		Clock newTime = new Clock(time, dateFormat);
		return newTime;
	}
	
	
	private static void closeRandomAccessFile() throws IOException {
		randomAccessFile.close();
	}

	private static void setUpRandomAccessFile() throws IOException {
		randomAccessFile = new RandomAccessFile(database, "rw");
		randomAccessFile.setLength(0);
	}
	
	private static void writeToDatabase(Event eventToUpdate) throws IOException {
		randomAccessFile.writeChars(eventToUpdate.toString());
		randomAccessFile.close();
	}
}
