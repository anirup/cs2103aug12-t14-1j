import org.joda.time.DateTime;

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
		return super.getEventTime();
	}
	
	public boolean isClashedWith(Event anotherEvent) {
		return false;
	}
	
	public String composeContentToDisplay() {
		String content = super.composeContentToDisplay();
		return content;
	}
	
	public boolean isBefore(Event anotherEvent) {
		return true;
	}
	
	public boolean isInDay(DateTime day) {
		return false;
	}
	
	public int getEventType() {
		return FLOATING_TYPE;
	}
}

