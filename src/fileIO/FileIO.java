package fileIO;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
 
public class FileIO {
	private static String fileName;
	private static BufferedReader reader;
	private static File database;
	private static BufferedWriter writer;
	
	public static ArrayList<String> setUpDatabase(String nameOfFile) throws IOException {
		fileName = nameOfFile;
		database = new File(fileName);
		if (!database.exists()) {
			database.createNewFile();
		}
		reader = new BufferedReader(new FileReader(fileName));
		return retrieveDatabase();
	}
	
	public static void syncToDatabase(ArrayList<String> currentListOfEvent, String nameOfFile) throws IOException {
		fileName = nameOfFile;
		setUpBufferedWriter();
		Iterator<String> iterator = currentListOfEvent.iterator();
		while(iterator.hasNext()) {
			String content = iterator.next();
			writeToDatabase(content);
		}
		closeBufferedWriter();
		
		return;
	}
	
	public static ArrayList<String> retrieveDatabase() throws IOException {
		ArrayList<String> listOfEvent = new ArrayList<String>();
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

	public static void formatDatabase() throws IOException {
		setUpBufferedWriter();
		closeBufferedWriter();
	}
}
