import java.io.File;
import java.io.IOException;

public class Event implements Comparable<Event>{
	protected String _eventID;
	protected String _eventName;
	protected String[] _eventHashTag;
	protected Clock _eventReminder;
	protected boolean _isDone;
	
	protected static final String SPLITTER = "";
	protected static final String NEW_LINE = "\n";
	protected static final String SPACE = " ";
	
	private static StringBuilder eventContent = new StringBuilder();
	
	public Event() {
		_eventID = null;
		_eventName = null;
		_eventHashTag = null;
		_eventReminder = null;
		_isDone = false;
	}
	
	public Event(String eventID, String eventName, String[] eventHashTag, Clock eventReminder, boolean isDone) {
		_eventID = eventID;
		_eventName = eventName;
		_eventHashTag = eventHashTag;
		_eventReminder = eventReminder;
		_isDone = isDone;
		
		return;
	}
	
	public String getEventID() {
		return _eventID;
	}
	
	public void markDone() {
		_isDone = true;
		return;
	}
	
	public String getEventName() {
		return _eventName;
	}
	
	public String[] getEventHashTag() {
		return _eventHashTag;
	}
	
	public Clock getEventReminder() {
		return _eventReminder;
	}
	
	public boolean isDone() {
		return _isDone;
	}

	public String composeEventContent() {		
		appendEventContent();
		
		return eventContent.toString();
	}
	
	public boolean isSameEvent(Event anotherEvent) {
		String currentEventID = this.getEventID();
		String anotherEventID = anotherEvent.getEventID();
		
		return currentEventID.equalsIgnoreCase(anotherEventID);
	}
	
	public int compareTo(Event event) {
		return this.getEventID().compareTo(event.getEventID());
	}
	
	private void appendEventContent() {
		appendEventID();
		appendEventName();
		appendEventHashTag();
		appendEventReminder();		
		appendIsDone();
	}
	
	private void appendEventID() {
		eventContent.append(_eventID);
	}
	
	private void appendEventReminder() {
		String contentToAppend = composeContent(_eventReminder.toString(), NEW_LINE);
		eventContent.append(contentToAppend);
	}

	private void appendEventHashTag() {
		for (int position = 0; position < _eventHashTag.length; position++) {
			String hashTagToAppend = _eventHashTag[position];
			String contentToAppend = composeContent(hashTagToAppend, SPACE);
			eventContent.append(contentToAppend);
		}
		eventContent.append(NEW_LINE);
		return;
	}

	private void appendEventName() {
		String contentToAppend = composeContent(_eventName, NEW_LINE);
		eventContent.append(contentToAppend);
	}
	
	private void appendIsDone() {
		String contentToAppend = Boolean.toString(_isDone);
		eventContent.append(contentToAppend);
	}

	public String composeContent(String content, String splitter) {
		return content + splitter;
	}

	public void writeEventToFile(File database) throws IOException {
		return;
	}
}
