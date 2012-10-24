import org.joda.time.DateTime;


public class Event {
	protected String _eventID;
	protected String _eventName;
	protected String _eventHashTag;
	protected boolean _isDone;
	
	public static final String SPLITTER = "..";
	protected final String NEW_LINE = "\n";
	public static final String SPACE = " ";
	
	protected static final int INDEX_FOR_EVENT_ID = 0;
	protected static final int INDEX_FOR_EVENT_NAME = 1;
	protected static final int INDEX_FOR_EVENT_HASHTAG = 2;
	protected static final int INDEX_FOR_EVENT_ISDONE = 3;
	protected static final int INDEX_FOR_EVENT_REMINDER_TIME = 4;
	protected static final int INDEX_FOR_EVENT_START_TIME = 5;
	protected static final int INDEX_FOR_EVENT_END_TIME = 6;

	public Event() {
		_eventID = null;
		_eventName = null;
		_eventHashTag = null;
		_isDone = false;
	}
	
	public Event(String eventID, String eventName, String eventHashTag, DateTime eventReminder, boolean isDone) {
		_eventID = eventID;
		_eventName = eventName;
		_eventHashTag = eventHashTag;
		_isDone = isDone;
		return;
	}
	
	public void parse(String[] contentToExtract) {		
		_eventID = contentToExtract[INDEX_FOR_EVENT_ID];
		_eventName = contentToExtract[INDEX_FOR_EVENT_NAME];
		_eventHashTag = contentToExtract[INDEX_FOR_EVENT_HASHTAG];
		extractEventIsDone(contentToExtract);
		return;
	}

	protected static DateTime extractTime(String[] contentToExtract, int indexTime) {
		return Clock.parseTimeFromString(contentToExtract[indexTime]);
	}
	
	private void extractEventIsDone(String[] contentToExtract) {
		String isDone = contentToExtract[INDEX_FOR_EVENT_ISDONE];
		if (isDone.trim().equalsIgnoreCase("true")) {
			_isDone = true;
		} else {
			_isDone = false;
		}
	}
	
	public boolean isBefore(Event anotherEvent) {
		return Clock.isBefore(this.getEventTime(), anotherEvent.getEventTime());
	}
	
	public boolean isClashedWith(Event anotherEvent) {
		return false;
	}
	
	public boolean isInDay(DateTime day) {
		return false;
	}
	
	public boolean searchInHashTag(String keyWord) {
		if(_eventHashTag.length() == 0) {
			return false;
		} 
		keyWord = "#" + keyWord;
		String[] hashTag = _eventHashTag.split(" ");
		
		for(int index = 0; index < hashTag.length; index++) {
			if(hashTag[index].equalsIgnoreCase(keyWord)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean seachInName(String keyWord) {
		return _eventName.contains(keyWord);
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
	
	public String getEventHashTag() {
		return _eventHashTag;
	}
	
	public DateTime getEventReminder() {
		return Clock.getBigBangTime();
	}
	
	public boolean isDone() {
		return _isDone;
	}
	
	public DateTime getEventTime() {
		return Clock.getBigBangTime();
	}
	
	public DateTime getEventStartTime() {
		return getEventTime();
	}
	
	public DateTime getEventEndTime() {
		return getEventTime();
	}
	
	public boolean isSameEvent(Event anotherEvent) {
		String currentEventID = this.getEventID();
		String anotherEventID = anotherEvent.getEventID();
		
		return currentEventID.equalsIgnoreCase(anotherEventID);
	}

	public String[] composeContentToDisplay() {
		String[] content = new String[7];
		content[INDEX_FOR_EVENT_ID] = _eventID;
		content[INDEX_FOR_EVENT_NAME] = _eventName;
		content[INDEX_FOR_EVENT_HASHTAG] = _eventHashTag;
		content[INDEX_FOR_EVENT_ISDONE] = StringOperation.booleanToString(_isDone);
		content[INDEX_FOR_EVENT_REMINDER_TIME] = "";
		content[INDEX_FOR_EVENT_START_TIME] = "";
		content[INDEX_FOR_EVENT_END_TIME] = "";
		return content;
	}
	public String composeContentToDisplayInString() {
		String content = _eventName + SPLITTER + _eventHashTag;
		return content;
	}
	
	public String toString() {
		String content = _eventID + SPLITTER + _eventName + SPLITTER + _eventHashTag + SPLITTER + 
				 Boolean.toString(_isDone) + SPLITTER;
		return content;
	}
}
