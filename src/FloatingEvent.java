
public class FloatingEvent extends Event{	
	
	public FloatingEvent() {
		super();
	}
	
	public FloatingEvent(String eventID, String eventName, String[] hashTag, Clock reminder, boolean isDone) {
		super(eventID, eventName, hashTag, reminder, isDone);
	}

	public FloatingEvent(Event event) {
		super(event.getEventID(), event.getEventName(), event.getEventHashTag(), event.getEventReminder(), event.isDone());
	}
	
	public String toString() {
		return super.toString();
	}
	
	public void parse(String eventContent) {
		
	}
	
	public Clock getEventTime() {
		Clock eventTime = new Clock();
		return eventTime;
	}
}
