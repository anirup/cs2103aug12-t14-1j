package fileIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import event.ListOfEvent;
  
public class DatabaseManager {
	private static ArrayList<String> listOfEventInString = new ArrayList<String>();
	private FileIO fileIO;
	
	public DatabaseManager(String fileName) throws IOException {
		fileIO = new FileIO(fileName);
	}
	
	public void setUpDataFromDatabase() throws Exception {
		listOfEventInString.clear();
		listOfEventInString = fileIO.setUpDatabase();
		setUpListOfEventFromString();
	}
	
	public void syncDataToDatabase(ArrayList<String> listOfEventInString) throws IOException {
		fileIO.syncToDatabase(listOfEventInString);
	}
	
	private void setUpListOfEventFromString() throws IOException {
		Iterator<String> iterator = listOfEventInString.iterator();
		while(iterator.hasNext()) {
			String currentLine = iterator.next();
			ListOfEvent.add(currentLine);
		}
		return;
	}
	
	public void formatDatabase() throws IOException {
		if(fileIO != null) {
			fileIO.formatDatabase();			
		}
	}
} 
