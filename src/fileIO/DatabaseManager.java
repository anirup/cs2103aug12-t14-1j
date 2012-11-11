package fileIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import event.Event;
import event.ListOfEvent;
  
public class DatabaseManager {
	private static ArrayList<String> listOfEventInString = new ArrayList<String>();
	private static String fileName;

	public static void setUpDataFromDatabase(String nameOfFile) throws Exception {
		fileName = nameOfFile;
		listOfEventInString = FileIO.setUpDatabase(fileName);
		setUpListOfEventFromString();
	}
	
	public static void syncDataToDatabase(ArrayList<String> listOfEventInString, String nameOfFile) throws IOException {
		fileName = nameOfFile;
		FileIO.syncToDatabase(listOfEventInString, fileName);
	}
	
	private static void setUpListOfEventFromString() throws IOException {
		Iterator<String> iterator = listOfEventInString.iterator();
		while(iterator.hasNext()) {
			String currentLine = iterator.next();
			Event newEvent = ListOfEvent.add(currentLine);
		}
		return;
	}
	
	private static void formatDatabase() throws IOException {
		FileIO.formatDatabase();
	}
} 
