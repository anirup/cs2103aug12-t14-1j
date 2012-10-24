import org.joda.time.DateTime;

public class TimedEvent extends Event{
	private DateTime _eventStartTime;
	private DateTime _eventEndTime;
	private DateTime _eventReminder;
	
	public TimedEvent() {
		super();
		_eventStartTime = null;
	}
	
	public TimedEvent(String eventID, String eventName, String hashTag, DateTime reminder, 
			boolean isDone, DateTime startTime, DateTime endTime) {
		super(eventID, eventName, hashTag, reminder, isDone);
		_eventStartTime = startTime;	
		_eventEndTime = endTime;
	}
	
	public TimedEvent(Event event, DateTime startTime, DateTime endTime) {
		super(event.getEventID(), event.getEventName(), event.getEventHashTag(),
				event.getEventReminder(), event.isDone());
		_eventStartTime = startTime;
		_eventEndTime = endTime;
	}
	
	public DateTime getEventStartTime() {
		return _eventStartTime;
	}
	
	public DateTime getEventEndTime() {
		return _eventEndTime;
	}

	public DateTime getEventTime() {
		return _eventStartTime;
	}
	
	public void parse(String[] contentToExtract) {
		super.parse(contentToExtract);
		_eventReminder = Event.extractTime(contentToExtract, INDEX_FOR_EVENT_REMINDER_TIME);
		_eventStartTime = Event.extractTime(contentToExtract, INDEX_FOR_EVENT_START_TIME);
		_eventEndTime = Event.extractTime(contentToExtract, INDEX_FOR_EVENT_END_TIME);	
	}
	
	public String toString() {
		String eventContent = super.toString();
		eventContent = eventContent + Clock.toString(_eventReminder) + SPLITTER;
		eventContent = eventContent + Clock.toString(_eventStartTime) +SPLITTER;
		eventContent = eventContent + Clock.toString(_eventEndTime) + SPLITTER;
		return eventContent;
	}
	
	public String[] composeContentToDisplay() {
		String[] content = super.composeContentToDisplay();
		content[INDEX_FOR_EVENT_START_TIME] = Clock.toString(_eventStartTime);
		content[INDEX_FOR_EVENT_END_TIME] = Clock.toString(_eventEndTime);
		content[INDEX_FOR_EVENT_REMINDER_TIME] = content + SPLITTER + Clock.toString(_eventReminder);
		return content;
	}
	
	public String composeContentToDisplayInString() {
		String content = super.composeContentToDisplayInString();
		content = content + SPLITTER + Clock.toString(_eventStartTime)+ SPLITTER + Clock.toString(_eventEndTime)+ SPLITTER + Clock.toString(_eventReminder);
		return content;
	}
	
	public boolean searchInHashTag(String keyWord) {
		return super.searchInHashTag(keyWord);
	}
	
	public boolean seachInName(String keyWord) {
		return super.seachInName(keyWord);
	}
	
	public boolean searchInTime(DateTime time) {
		return Clock.searchTime(time, _eventStartTime, _eventEndTime);
	}
	
	public boolean isBefore(Event anotherEvent) {
		return super.isBefore(anotherEvent);
	}
	
	public boolean isInDay(DateTime day) {
		return Clock.isInDay(day, _eventStartTime) || Clock.isInDay(day, _eventEndTime) 
				|| (Clock.isBefore(_eventStartTime, day) && Clock.isBefore(day, _eventEndTime));
	}
	
	public boolean isClashedWith(Event anotherEvent) {
		return Clock.isClashed(_eventStartTime, _eventEndTime, 
				anotherEvent.getEventStartTime(), anotherEvent.getEventEndTime());
	}
}
