
public class Event implements Comparable<Event>{
	protected String _eventID;
	protected String _eventName;
	protected String[] _eventHashTag;
	protected Clock _eventReminder;
	protected boolean _isDone;
	
	public static final String SPLITTER = "||";
	protected final String NEW_LINE = "\n";
	public static final String SPACE = " ";
	
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
	
	public boolean searchInName(String keyWord) {
		return false;
	}
	
	public boolean searchInHashTag(String keyWord) {
		return false;
	}
	
	public boolean searchInTime(Clock keyWord) {
		return false;
	}
	
	public String getEventID() {
		return _eventID;
	}
	
	public void markDone() {
		_isDone = true;
		return;
	}
	public void markUndone(){
		_isDone = false;
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
	
	public Clock getEventTime() {
		return null;
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
		String content = addSplitter(_eventID, SPLITTER);
		eventContent.append(content);
	}
	
	private void appendEventReminder() {
		String content = addSplitter(_eventReminder.toString(), SPLITTER);
		eventContent.append(content);
	}

	private void appendEventHashTag() {
		for (int position = 0; position < _eventHashTag.length; position++) {
			String hashTagToAppend = _eventHashTag[position];
			String contentToAppend = addSplitter(hashTagToAppend, SPACE);
			eventContent.append(contentToAppend);
		}
		eventContent.append(SPLITTER);
		return;
	}

	private void appendEventName() {
		String content = addSplitter(_eventName, SPLITTER);
		eventContent.append(content);
	}
	
	private void appendIsDone() {
		String eventStatus = Boolean.toString(_isDone);
		String content = addSplitter(eventStatus, SPLITTER);
		eventContent.append(content);
	}

	protected static String addSplitter(String content, String splitter) {
		return content + splitter;
	}

	public String toString() {
		appendEventContent();
		
		return eventContent.toString();
	}
}
