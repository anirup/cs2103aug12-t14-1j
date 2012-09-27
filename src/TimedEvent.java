import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;


public class TimedEvent extends Event{
	private Clock _eventStartTime;
	private Clock _eventEndTime;
	
	private static RandomAccessFile randomAccessFile;
	public static final String TIMED_EVENT_INDICATOR = "timed";
	
	public TimedEvent() {
		super();
		_eventStartTime = null;
	}
	
	public TimedEvent(String eventID, String eventName, String[] hashTag, Clock reminder, Clock startTime, Clock endTime, boolean isDone) {
		super(eventID, eventName, hashTag, reminder, isDone);
		_eventStartTime = startTime;	
		_eventEndTime = endTime;
	}
	
	public TimedEvent(Event event, Clock startTime, Clock endTime) {
		super(event.getEventID(), event.getEventName(), event.getEventHashTag(), event.getEventReminder(), event.isDone());
		_eventStartTime = startTime;
		_eventEndTime = endTime;
	}
	
	public int compareTo(TimedEvent anotherEvent) throws ParseException {
		return this.getEventStartTime().toDate().compareTo(anotherEvent.getEventStartTime().toDate());
	}
	
	public int compareTo(DeadlineEvent anotherEvent) throws ParseException {
		return this.getEventStartTime().toDate().compareTo(anotherEvent.getEventTime().toDate());
	}
	
	public Clock getEventStartTime() {
		return _eventStartTime;
	}
	
	public Clock getEventEndTime() {
		return _eventEndTime;
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
		randomAccessFile.writeChars(TIMED_EVENT_INDICATOR);
		randomAccessFile.writeChars(eventContent);		
	}
	
	private void seekWritePosition() throws IOException {
		long writePosition = randomAccessFile.length();	
		randomAccessFile.seek(writePosition);
	}

	public String composeEventContent() {
		StringBuilder timedEventContent = new StringBuilder();
		
		String taskContent = super.composeEventContent();
		timedEventContent.append(taskContent);
		
		appendEventTime(timedEventContent);
		
		return timedEventContent.toString();
	}

	private void appendEventTime(StringBuilder timedTaskContent) {
		String contentToAppend = _eventStartTime.toString();
		timedTaskContent.append(contentToAppend);
		
		contentToAppend = _eventEndTime.toString();
		timedTaskContent.append(contentToAppend);
		
		return;
	}
}
