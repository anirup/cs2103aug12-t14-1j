import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;


public class DeadlineEvent extends Event{
	private Clock _eventTime;
	
	private static RandomAccessFile randomAccessFile;
	public static final String DEADLINE_EVENT_INDICATOR = "deadline";
	
	public DeadlineEvent() {
		_eventTime = null;
	}
	
	public DeadlineEvent(String eventID, String eventName, String[] hashTag, Clock reminder, Clock time, boolean isDone) {
		super(eventID, eventName, hashTag, reminder, isDone);
		_eventTime = time;
	}
	
	public DeadlineEvent(Event event, Clock time) {
		super(event.getEventID(), event.getEventName(), event.getEventHashTag(), event.getEventReminder(), event.isDone());
		_eventTime = time;
	}
	
	public Clock getEventTime() {
		return _eventTime;
	}
	
	public int compareTo(TimedEvent anotherEvent) throws ParseException {
		return this.getEventTime().toDate().compareTo(anotherEvent.getEventStartTime().toDate());
	}
	
	public int compareTo(DeadlineEvent anotherEvent) throws ParseException {
		return this.getEventTime().toDate().compareTo(anotherEvent.getEventTime().toDate());
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
		randomAccessFile.writeChars(DEADLINE_EVENT_INDICATOR);
		randomAccessFile.writeChars(eventContent);	
		return;
	}
	
	private void seekWritePosition() throws IOException {
		long writePosition = randomAccessFile.length();	
		randomAccessFile.seek(writePosition);
		return;
	}
	
	public String composeEventContent() {
		StringBuilder timedEventContent = new StringBuilder();
		
		String taskContent = super.composeEventContent();
		timedEventContent.append(taskContent);
		
		appendEventTimeToStringBuilder(timedEventContent);
		
		return timedEventContent.toString();
	}

	private void appendEventTimeToStringBuilder(StringBuilder timedTaskContent) {
		String contentToAppend = _eventTime.toString();
		timedTaskContent.append(contentToAppend);
		
		return;
	}
}
