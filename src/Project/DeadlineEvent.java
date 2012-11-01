package Project;

import org.joda.time.DateTime;

public class DeadlineEvent extends Event{
	private DateTime _eventTime;
	private DateTime _eventReminder;
		
	public DeadlineEvent() {
		_eventTime = null;
	}
	
	public DeadlineEvent(String eventID, String eventName, 
			String hashTag, DateTime reminder, boolean isDone, DateTime time) {
		super(eventID, eventName, hashTag, reminder, isDone);
		_eventTime = time;
	}
	
	public DeadlineEvent(Event event, DateTime time) {
		super(event.getEventID(), event.getEventName(), event.getEventHashTag(), 
				event.getEventReminder(), event.isDone());
		_eventTime = time;
	}
	
	public DateTime getEventTime() {
		return _eventTime;
	}
	
	public boolean searchInHashTag(String keyWord) {
		return super.searchInHashTag(keyWord);
	}
	
	public boolean seachInName(String keyWord) {
		return super.seachInName(keyWord);
	}
	
	public int getEventType() {
		return DEADLINE_TYPE;
	}
	
	public boolean searchInTime(DateTime time) {
		return Clock.searchTime(time, _eventTime, _eventTime);
	}
	
	public boolean isBefore(Event anotherEvent) {
		return super.isBefore(anotherEvent);
	}
	
	public void parse(String[] contentToExtract) {
		super.parse(contentToExtract);
		_eventTime = Event.extractTime(contentToExtract, Event.INDEX_FOR_EVENT_END_TIME);
		_eventReminder = Event.extractTime(contentToExtract, INDEX_FOR_EVENT_REMINDER_TIME);
	}
	
	public String[] composeContentToDisplay() {
		String[] content = super.composeContentToDisplay();
		content[INDEX_FOR_EVENT_START_TIME] =Clock.toString(_eventTime);
		content[INDEX_FOR_EVENT_REMINDER_TIME] = content + SPLITTER + Clock.toString(_eventReminder);
		return content;
	}
	public String composeContentToDisplayInString() {
		String content = super.composeContentToDisplayInString();
		content = content + SPLITTER + Clock.toString(_eventTime)+ SPLITTER + Clock.toString(_eventReminder);
		return content;
	}
	
	public boolean isInDay(DateTime day) {
		return Clock.isInDay(day, _eventTime);
	}
	
	public boolean isClashedWith(Event anotherEvent) {
		return false;
	}
	
	public String toString() {
		String eventContent = super.toString();
		eventContent = eventContent + Clock.toString(_eventReminder) + SPLITTER;
		eventContent = eventContent + Clock.toString(_eventTime) + SPLITTER; 
		eventContent = eventContent + "invalid" + SPLITTER;
		return eventContent;
	}
}
