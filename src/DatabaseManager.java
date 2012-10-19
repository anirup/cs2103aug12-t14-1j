import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;


public class DatabaseManager {
	private static final String fileName = "What2Do.txt";

	private static BufferedReader reader;
	private static File database;
	private static BufferedWriter writer;
	
	public static void setUpDatabase() throws IOException {
		database = new File(fileName);
		
		if (!database.exists()) {
			database.createNewFile();
		}
		
		reader = new BufferedReader(new FileReader(fileName));
		
		return;
	}
	
	public static void syncToDatabase(LinkedList<String> currentListOfEvent) throws IOException {
	
		setUpBufferedWriter();
	
		Iterator<String> iterator = currentListOfEvent.iterator();
		
		while(iterator.hasNext()) {
			String content = iterator.next();
			writeToDatabase(content);
		}
		
		closeBufferedWriter();
		
		return;
	}
	
	public static LinkedList<String> retrieveDatabase() throws IOException {
		LinkedList<String> listOfEvent = new LinkedList<String>();
		String currentLine;
		while((currentLine = reader.readLine()) != null) {
			listOfEvent.add(currentLine);
		}
		
		return listOfEvent;
	}
	
	private static void closeBufferedWriter() throws IOException {
		writer.close();
	}

	private static void setUpBufferedWriter() throws IOException {
		writer = new BufferedWriter(new FileWriter(fileName));
	}
	
	private static void writeToDatabase(String line) throws IOException {
		writer.write(line + "\n");
	}
}
