public class TimedEvent extends Event{
	private Clock _eventStartTime;
	private Clock _eventEndTime;
	private static StringBuilder timedEventContent;
	
	public TimedEvent() {
		super();
		_eventStartTime = null;
	}
	
	public TimedEvent(String eventID, String eventName, String[] hashTag, Clock reminder, boolean isDone, Clock startTime, Clock endTime) {
		super(eventID, eventName, hashTag, reminder, isDone);
		_eventStartTime = startTime;	
		_eventEndTime = endTime;
	}
	
	public TimedEvent(Event event, Clock startTime, Clock endTime) {
		super(event.getEventID(), event.getEventName(), event.getEventHashTag(), event.getEventReminder(), event.isDone());
		_eventStartTime = startTime;
		_eventEndTime = endTime;
	}
	
	public Clock getEventStartTime() {
		return _eventStartTime;
	}
	
	public Clock getEventEndTime() {
		return _eventEndTime;
	}

	public Clock getEventTime() {
		return _eventStartTime;
	}

	public String toString() {
		String eventContent = super.toString();
		timedEventContent = new StringBuilder(eventContent);
		appendEventTime();
		
		return timedEventContent.toString();
		
	}
	
	public void parse(String[] contentToExtract) {
		super.parse(contentToExtract);
		_eventStartTime = Event.extractTime(contentToExtract, INDEX_FOR_EVENT_START_TIME, INDEX_FOR_EVENT_START_TIME_DATEFORMAT);
		_eventEndTime = Event.extractTime(contentToExtract, INDEX_FOR_EVENT_END_TIME, INDEX_FOR_EVENT_END_TIME_DATEFORMAT);	
	}
	
	private void appendEventTime() {
		String contentToAppend = _eventStartTime.toString();
		timedEventContent.append(contentToAppend);
		
		contentToAppend = _eventEndTime.toString();
		timedEventContent.append(contentToAppend);
		
		return;
	}
}
