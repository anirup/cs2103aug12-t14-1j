import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FloatingEvent extends Event{
	private static RandomAccessFile randomAccessFile;
	
	public static final String FLOATING_EVENT_INDICATOR = "floating";
	
	public FloatingEvent() {
		super();
	}
	
	public FloatingEvent(String eventID, String eventName, String[] hashTag, Clock reminder, boolean isDone) {
		super(eventID, eventName, hashTag, reminder, isDone);
	}

	public FloatingEvent(Event event) {
		super(event.getEventID(), event.getEventName(), event.getEventHashTag(), event.getEventReminder(), event.isDone());
	}
	
	public void writeEventToFile(File file) throws IOException{
		randomAccessFile = new RandomAccessFile(file, "rw");
		seekWritePosition();
		writeToFile();
		randomAccessFile.close();
		return;
	}

	private void writeToFile() throws IOException {
		String eventContent = composeEventContent();
		randomAccessFile.writeChars(FLOATING_EVENT_INDICATOR);
		randomAccessFile.writeChars(eventContent);		
	}
	
	private void seekWritePosition() throws IOException {
		long writePosition = randomAccessFile.length();	
		randomAccessFile.seek(writePosition);
	}
}
