
public class DeadlineEvent extends Event{
	private Clock _eventTime;
	
	public static final String DEADLINE_EVENT_INDICATOR = "deadline";
	
	public DeadlineEvent() {
		_eventTime = null;
	}
	
	public DeadlineEvent(String eventID, String eventName, String[] hashTag, Clock reminder, boolean isDone, Clock time) {
		super(eventID, eventName, hashTag, reminder, isDone);
		_eventTime = time;
	}
	
	public DeadlineEvent(Event event, Clock time) {
		super(event.getEventID(), event.getEventName(), event.getEventHashTag(), event.getEventReminder(), event.isDone());
		_eventTime = time;
	}
	
	public Clock getEventTime() {
		return _eventTime;
	}
	
	public void parse(String[] contentToExtract) {
		super.parse(contentToExtract);
		_eventTime = Event.extractTime(contentToExtract, Event.INDEX_FOR_EVENT_START_TIME, Event.INDEX_FOR_EVENT_START_TIME_DATEFORMAT);
	}
	
	
	
	public String toString() {
		StringBuilder eventContent = new StringBuilder();	
		eventContent.append(super.toString());
		appendEventTime(eventContent);
		return eventContent.toString();
	}
	
	private void appendEventTime(StringBuilder eventContent) {
		String contentToAppend = _eventTime.toString();
		eventContent.append(contentToAppend);
		
		return;
	}
}
