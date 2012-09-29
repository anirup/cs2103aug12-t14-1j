import java.text.ParseException;

public class DeadlineEvent extends Event{
	private Clock _eventTime;
	
	public static final String DEADLINE_EVENT_INDICATOR = "deadline";
	
	public DeadlineEvent() {
		_eventTime = null;
	}
	
	public DeadlineEvent(String eventID, String eventName, String[] hashTag, Clock reminder, Clock time, boolean isDone) {
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
	
	public int compareTo(TimedEvent anotherEvent) throws ParseException {
		return this.getEventTime().toDate().compareTo(anotherEvent.getEventStartTime().toDate());
	}
	
	public int compareTo(DeadlineEvent anotherEvent) throws ParseException {
		return this.getEventTime().toDate().compareTo(anotherEvent.getEventTime().toDate());
	}

	public int compareTo(FloatingEvent anotherEvent) {
		return 1;
	}
	public String toString() {
		StringBuilder eventContent = new StringBuilder();	
		eventContent.append(super.toString());
		appendEventTime(eventContent);
		return eventContent.toString();
	}
	
	private void appendEventTime(StringBuilder eventContent) {
		String contentToAppend = super.addSplitter(_eventTime.toString(), super.NEW_LINE);
		eventContent.append(contentToAppend);
		
		return;
	}
}
