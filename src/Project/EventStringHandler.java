package Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class EventStringHandler {
	private static ArrayList<Event> listOfEvent = new ArrayList<Event>();
	private static ArrayList<String> listOfEventInString = new ArrayList<String>();
	private static String fileName;
	
	public static ArrayList<Event> getCurrentListOfEvent() {
		return listOfEvent;
	}
	
	public static void setUpDataFromDatabase(String nameOfFile) throws Exception {
		fileName = nameOfFile;
		listOfEventInString = DatabaseManager.setUpDatabase(fileName);
		setUpListOfEventFromString();
	}
	
	public static void syncDataToDatabase(ArrayList<String> listOfEventInString, String nameOfFile) throws IOException {
		fileName = nameOfFile;
		DatabaseManager.syncToDatabase(listOfEventInString, fileName);
	}
	
	private static void setUpListOfEventFromString() throws IOException {
		Iterator<String> iterator = listOfEventInString.iterator();
		while(iterator.hasNext()) {
			String currentLine = iterator.next();
			Event newEvent = ListOfEvent.getEventFromString(currentLine);
			if(newEvent != null) {
				listOfEvent.add(newEvent);
				if(!ListOfEvent.checkEventID(getCurrentListOfEvent())) {
					formatDatabase();
					return;
				}
			} else {
				formatDatabase();
				return;
			}
		}
		return;
	}
	
	public static void formatDatabase() throws IOException {
		DatabaseManager.formatDatabase();
		listOfEvent.clear();
	}
} 
