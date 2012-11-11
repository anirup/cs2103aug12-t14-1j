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
	private String fileName;
	private BufferedReader reader;
	private File database;
	private static BufferedWriter writer;
	
	public FileIO(String name) throws IOException {
		fileName = name;
		database = new File(fileName);
		if (!database.exists()) {
			database.createNewFile();
		}
		reader = new BufferedReader(new FileReader(fileName));
	}
	
	public ArrayList<String> setUpDatabase() throws IOException {
		return retrieveDatabase();
	}
	
	public void clearFile() throws IOException {
		FileWriter writer = new FileWriter(database);
		writer.close();
		
	}
	
	public void syncToDatabase(ArrayList<String> currentListOfEvent) throws IOException {
		database = new File(fileName);
		if (!database.exists()) {
			database.createNewFile();
		}
		setUpBufferedWriter();
		Iterator<String> iterator = currentListOfEvent.iterator();
		while(iterator.hasNext()) {
			String content = iterator.next();
			writeToDatabase(content);
		}
		closeBufferedWriter();
		
		return;
	}
	
	public void writeContinue(ArrayList<String> currentListOfEvent) throws IOException {
		FileWriter writer = new FileWriter(database, true);
		writer.write("-------------------------------------\n");
		for(int index = 0; index < currentListOfEvent.size(); index++) {
			writer.write(currentListOfEvent.get(index) + "\n");
		}
		writer.close();
		return;
	}
	
	private ArrayList<String> retrieveDatabase() throws IOException {
		ArrayList<String> listOfEvent = new ArrayList<String>();
		String currentLine;
		while((currentLine = reader.readLine()) != null) {
			listOfEvent.add(currentLine);
		}
		
		return listOfEvent;
	}
	
	private void closeBufferedWriter() throws IOException {
		writer.close();
	}

	private void setUpBufferedWriter() throws IOException {
		writer = new BufferedWriter(new FileWriter(fileName));
	}
	
	private void writeToDatabase(String line) throws IOException {
		writer.write(line + "\n");
	}

	public void formatDatabase() throws IOException {
		setUpBufferedWriter();
		closeBufferedWriter();
	}
}
