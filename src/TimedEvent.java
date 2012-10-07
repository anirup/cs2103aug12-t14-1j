public class TimedEvent extends Event{
	private Clock _eventStartTime;
	private Clock _eventEndTime;
	
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
		StringBuilder eventContent = new StringBuilder();
		
		eventContent.append(super.toString());
		appendEventTime(eventContent);
		
		return eventContent.toString();
		
	}
	
	public void parse(String[] contentToExtract) {
		super.parse(contentToExtract);
		_eventStartTime = Event.extractTime(contentToExtract, INDEX_FOR_EVENT_START_TIME, INDEX_FOR_EVENT_START_TIME_DATEFORMAT);
		_eventEndTime = Event.extractTime(contentToExtract, INDEX_FOR_EVENT_END_TIME, INDEX_FOR_EVENT_END_TIME_DATEFORMAT);	
	}
	
	private void appendEventTime(StringBuilder timedTaskContent) {
		String contentToAppend = _eventStartTime.toString();
		timedTaskContent.append(contentToAppend);
		
		contentToAppend = super.addSplitter(_eventEndTime.toString(), super.NEW_LINE);
		timedTaskContent.append(contentToAppend);
		
		return;
	}
}
