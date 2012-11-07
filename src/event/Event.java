package event;
import global.Clock;

import org.joda.time.DateTime;


public class Event {
	private String _eventID;
	private String _eventName;
	private String _eventPriority;
	private String _eventHashTag;
	private DateTime _eventReminder;
	private DateTime _eventStartTime;
	private DateTime _eventEndTime;
	private DateTime _timeCompleted;
	private boolean _isDone;
	
	public static final String SPLITTER = "..";
	protected final String NEW_LINE = "\n";
	public static final String SPACE = " ";
	
	protected static final int INDEX_FOR_EVENT_ID = 0;
	protected static final int INDEX_FOR_EVENT_NAME = 1;
	protected static final int INDEX_FOR_EVENT_PRIORITY = 2;
	protected static final int INDEX_FOR_EVENT_HASHTAG = 3;
	protected static final int INDEX_FOR_EVENT_ISDONE = 4;
	protected static final int INDEX_FOR_EVENT_REMINDER_TIME = 5;
	protected static final int INDEX_FOR_EVENT_START_TIME = 6;
	protected static final int INDEX_FOR_EVENT_END_TIME = 7;
	protected static final int INDEX_FOR_COMPLETED_TIME = 8;
	
	public static final int FLOATING_TYPE = 0;
	public static final int DEADLINE_TYPE = 1;
	public static final int TIMED_TYPE = 2;

	public Event() {
		_eventID = null;
		_eventName = null;
		_eventHashTag = null;
		_isDone = false;
		_eventReminder = Clock.getBigBangTime();
		_eventStartTime = Clock.getBigBangTime();
		_eventEndTime = Clock.getBigBangTime();
		_eventPriority = null;
		_timeCompleted = Clock.getBigBangTime();;
	}
	
	public Event(String eventID, String eventName, String eventPriority, String eventHashTag,  boolean isDone,
			DateTime eventReminder, DateTime eventStartTime, DateTime eventEndTime) {
		_eventID = eventID;
		_eventName = eventName;
		_eventPriority = eventPriority;
		_eventHashTag = eventHashTag;
		_isDone = isDone;
		_eventReminder = eventReminder;
		_eventStartTime = eventStartTime;
		_eventEndTime = eventEndTime;
		return;
	}
	
	public void parse(String[] contentToExtract) {		
		assert contentToExtract.length == 9;
		_eventID = contentToExtract[INDEX_FOR_EVENT_ID];
		_eventName = contentToExtract[INDEX_FOR_EVENT_NAME];
		_eventPriority = contentToExtract[INDEX_FOR_EVENT_PRIORITY];
		_eventHashTag = contentToExtract[INDEX_FOR_EVENT_HASHTAG];
		_isDone = extractEventIsDone(contentToExtract[INDEX_FOR_EVENT_ISDONE]);
		_eventReminder = Clock.parseTimeFromString(contentToExtract[INDEX_FOR_EVENT_REMINDER_TIME]);
		_eventStartTime = Clock.parseTimeFromString(contentToExtract[INDEX_FOR_EVENT_START_TIME]);
		_eventEndTime = Clock.parseTimeFromString(contentToExtract[INDEX_FOR_EVENT_END_TIME]);
		_timeCompleted = Clock.parseTimeFromString(contentToExtract[INDEX_FOR_COMPLETED_TIME]);
		return;
	}
	
	public int getEventType() {
		if(Clock.isBigBangTime(_eventStartTime) && Clock.isBigBangTime(_eventEndTime)) {
			return FLOATING_TYPE;
		} else if(Clock.isBigBangTime(_eventEndTime)) {
			return DEADLINE_TYPE;
		} 
		return TIMED_TYPE;
	}
	
	public boolean isBefore(Event anotherEvent) {
		return Clock.isBefore(this.getEventStartTime(), anotherEvent.getEventStartTime());
	}
	
	public boolean isClashedWith(Event anotherEvent) {
		DateTime firstStart = this._eventStartTime;
		DateTime firstEnd = this._eventEndTime;
		DateTime secondStart = anotherEvent._eventStartTime;
		DateTime secondEnd = anotherEvent._eventEndTime;
		if((Clock.isBefore(firstStart, secondEnd) && Clock.isBefore(secondStart, firstStart)) 
				|| (Clock.isBefore(firstEnd, secondEnd) && Clock.isBefore(secondStart, firstEnd))
				|| (Clock.isBefore(secondStart, firstEnd) && Clock.isBefore(firstStart, secondEnd))) {
			return true;
		} 
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
	
	public String getPriority() {
		return _eventPriority;
	}
	
	public DateTime getTimeCompleted() {
		return _timeCompleted;
	}
	
	public String getEventID() {
		return _eventID;
	}
	
	public void markDone() {
		_timeCompleted = DateTime.now();
		_isDone = true;
		return;
	}
	
	public void markUndone(){
		_timeCompleted = Clock.getBigBangTime();
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
		return _eventReminder;
	}
	
	public boolean isDone() {
		return _isDone;
	}
	
	public DateTime getEventStartTime() {
		return _eventStartTime;
	}
	
	public DateTime getEventEndTime() {
		return _eventEndTime;
	}
	
	public boolean isSameEvent(Event anotherEvent) {
		String currentEventID = this.getEventID();
		String anotherEventID = anotherEvent.getEventID();
		return currentEventID.equalsIgnoreCase(anotherEventID);
	}

	public String composeContentToDisplayInString() {
		String content = _eventName;
		content= content + SPLITTER + _eventPriority;
		content= content + SPLITTER + _eventHashTag;
		content= content + SPLITTER + Clock.toStringToDisplay(_eventStartTime);
		content= content + SPLITTER + Clock.toStringToDisplay(_eventEndTime);
		content= content + SPLITTER + Clock.toStringToDisplay(_eventReminder);
		return content;
	}
	
	public String toString() {
		String content = _eventID + SPLITTER + _eventName + SPLITTER + _eventPriority + SPLITTER + 
				_eventHashTag + SPLITTER + Boolean.toString(_isDone) + SPLITTER + Clock.toString(_eventReminder) +  
				 SPLITTER + Clock.toString(_eventStartTime)  + SPLITTER + Clock.toString(_eventEndTime)
				 + SPLITTER + Clock.toString(_timeCompleted);
		return content;
	}
	
	private boolean extractEventIsDone(String isDone) {
		if (isDone.trim().equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}
}
