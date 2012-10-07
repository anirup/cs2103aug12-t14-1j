
public class Event {
	protected String _eventID;
	protected String _eventName;
	protected String[] _eventHashTag;
	protected Clock _eventReminder;
	protected boolean _isDone;
	
	public static final String SPLITTER = "..";
	protected final String NEW_LINE = "\n";
	public static final String SPACE = " ";
	
	protected static final int INDEX_FOR_EVENT_ID = 0;
	protected static final int INDEX_FOR_EVENT_NAME = 1;
	protected static final int INDEX_FOR_EVENT_HASHTAG = 2;
	protected static final int INDEX_FOR_EVENT_REMINDER_TIME = 3;
	protected static final int INDEX_FOR_EVENT_REMINDER_DATEFORMAT = 4;
	protected static final int INDEX_FOR_EVENT_ISDONE = 5;
	protected static final int INDEX_FOR_EVENT_START_TIME = 6;
	protected static final int INDEX_FOR_EVENT_START_TIME_DATEFORMAT = 7;
	protected static final int INDEX_FOR_EVENT_END_TIME = 8;
	protected static final int INDEX_FOR_EVENT_END_TIME_DATEFORMAT = 9;
	
	private static StringBuilder eventContent;

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
	
	public void parse(String[] contentToExtract) {		
		extractEventID(contentToExtract);
		extractEventName(contentToExtract);
		extractEventHashTag(contentToExtract);
		_eventReminder = extractTime(contentToExtract, INDEX_FOR_EVENT_REMINDER_TIME, INDEX_FOR_EVENT_REMINDER_DATEFORMAT);
		extractEventIsDone(contentToExtract);
		return;
	}
	
	private void extractEventID(String[] contentToExtract) {
		_eventID = contentToExtract[INDEX_FOR_EVENT_ID];
	}
	
	private void extractEventName(String[] contentToExtract) {
		_eventName = contentToExtract[INDEX_FOR_EVENT_NAME];
	}
	
	private void extractEventHashTag(String[] contentToExtract) {
		_eventHashTag = contentToExtract[INDEX_FOR_EVENT_HASHTAG].split(SPACE);
	}
	
	protected static Clock extractTime(String[] contentToExtract, int indexTime, int indexTimeFormat) {
		return new Clock(contentToExtract[indexTime], contentToExtract[indexTimeFormat]);
	}
	
	private void extractEventIsDone(String[] contentToExtract) {
		String isDone = contentToExtract[INDEX_FOR_EVENT_ISDONE];
		if (isDone.trim().equalsIgnoreCase("true")) {
			_isDone = true;
		} else {
			_isDone = false;
		}
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
	
	private void appendEventContent() {
		eventContent = new StringBuilder();
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
		String content = _eventReminder.toString();
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
