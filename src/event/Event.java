package event;
import global.Clock;

import org.joda.time.DateTime;

public class Event {
	private String _eventID;
	private String _eventName;
	private PRIORITY_TYPE _eventPriority;
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
	protected static final int INDEX_OF_EVENT_ISDONE = 4;
	protected static final int INDEX_FOR_EVENT_REMINDER_TIME = 5;
	protected static final int INDEX_FOR_EVENT_START_TIME = 6;
	protected static final int INDEX_FOR_EVENT_END_TIME = 7;
	protected static final int INDEX_FOR_COMPLETED_TIME = 8;
	
	public static final int FLOATING_TYPE = 0;
	public static final int DEADLINE_TYPE = 1;
	public static final int TIMED_TYPE = 2;

	public enum PRIORITY_TYPE {
		HIGH, NORMAL, LOW, INVALID;
	}
	
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
	
	public Event(String eventID, String name, PRIORITY_TYPE priority, String hashTag, DateTime reminder, DateTime start, DateTime end) {
		_eventID = eventID;
		_eventName = name;
		_eventPriority = priority;
		_eventHashTag = hashTag;
		_isDone = false;
		_eventReminder = reminder;
		_eventStartTime = start;
		_eventEndTime = end;
		_timeCompleted = Clock.getBigBangTime();;
	}

	public void parse(String[] contentToExtract) {		
		assert contentToExtract.length == 9;
		_eventID = contentToExtract[INDEX_FOR_EVENT_ID];
		_eventName = contentToExtract[INDEX_FOR_EVENT_NAME];
		_eventPriority = extractEventPriority(contentToExtract[INDEX_FOR_EVENT_PRIORITY]);
		_eventHashTag = contentToExtract[INDEX_FOR_EVENT_HASHTAG];
		_isDone = extractEventIsDone(contentToExtract[INDEX_OF_EVENT_ISDONE]);
		_eventReminder = Clock.parseTimeFromString(contentToExtract[INDEX_FOR_EVENT_REMINDER_TIME]);
		_eventStartTime = Clock.parseTimeFromString(contentToExtract[INDEX_FOR_EVENT_START_TIME]);
		_eventEndTime = Clock.parseTimeFromString(contentToExtract[INDEX_FOR_EVENT_END_TIME]);
		_timeCompleted = Clock.parseTimeFromString(contentToExtract[INDEX_FOR_COMPLETED_TIME]);
		return;
	}
	
	private PRIORITY_TYPE extractEventPriority(String priority) {
		if(priority.trim().equalsIgnoreCase("high")) {
			return PRIORITY_TYPE.HIGH;
		} else if(priority.trim().equalsIgnoreCase("normal")) {
			return PRIORITY_TYPE.NORMAL;
		} else if(priority.trim().equalsIgnoreCase("low")) {
			return PRIORITY_TYPE.LOW;
		}
		return PRIORITY_TYPE.INVALID;
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
	
	public PRIORITY_TYPE getPriority() {
		return _eventPriority;
	}
	
	public DateTime getTimeCompleted() {
		return _timeCompleted;
	}
	
	public String getEventID() {
		return _eventID;
	}
	
	private String getPriorityInString() {
		switch(_eventPriority) {
		case HIGH:
			return "HIGH";
		case NORMAL:
			return "NORMAL";
		case LOW:
			return "LOW";
		default:
			return "NORMAL";
		}
	}
	
	public void markDone() {
		if(Clock.isBefore(_eventEndTime, DateTime.now()) && Clock.isBigBangTime(_eventEndTime)) {
			_timeCompleted = _eventEndTime;
		} else {
			_timeCompleted = DateTime.now();
		}
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
		content= content + SPLITTER + getPriorityInString();
		content= content + SPLITTER + _eventHashTag;
		content= content + SPLITTER + Boolean.toString(_isDone);
		content= content + SPLITTER + reminderToStringToDisplay();
		content= content + SPLITTER + Clock.toStringToDisplay(_eventStartTime);
		content= content + SPLITTER + Clock.toStringToDisplay(_eventEndTime);
		return content;
	}
	
	private String reminderToStringToDisplay() {
		if(this.getEventType() == 0) {
			return "";
		}
		DateTime date = _eventStartTime.minus(_eventReminder.getMillis());
		long dateInMillis = date.getMillis(); 
		long minutes = 60*1000;
		long hour = 60*minutes;
		long day = 60*hour;
		if(dateInMillis/day > 0) {
			return Long.toString(dateInMillis/day) + " days";
		} else if(dateInMillis/hour > 0) {
			return Long.toString(dateInMillis/hour) + " hours";
		} else {
			return Long.toString(dateInMillis/minutes) + " mins";
		}
	}
	
	public String toString() {
		String content = _eventID + SPLITTER + _eventName + SPLITTER + getPriorityInString() + SPLITTER + 
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
