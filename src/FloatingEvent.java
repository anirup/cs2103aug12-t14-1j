import org.joda.time.DateTime;

public class FloatingEvent extends Event{	
	
	public FloatingEvent() {
		super();
	}
	
	public FloatingEvent(String eventID, String eventName, String hashTag, DateTime reminder, boolean isDone) {
		super(eventID, eventName, hashTag, reminder, isDone);
	}

	public FloatingEvent(Event event) {
		super(event.getEventID(), event.getEventName(), event.getEventHashTag(), event.getEventReminder(), event.isDone());
	}
	
	public String toString() {
		String content = super.toString() + 
				"invalid" + SPLITTER + "invalid" + SPLITTER + "invalid" + SPLITTER;
		return content;
	}
	
	public void parse(String eventContent) {
		
	}
	
	public DateTime getEventTime() {
		return super.getEventTime();
	}
	
	public boolean searchInHashTag(String keyWord) {
		return super.searchInHashTag(keyWord);
	}
	
	public boolean seachInName(String keyWord) {
		return super.seachInName(keyWord);
	}
	
	public boolean searchInTime(Clock time) {
		return false;
	}
	
	public int getEventType() {
		return FLOATING_TYPE;
	}
	
	public boolean isClashedWith(Event anotherEvent) {
		return false;
	}
	
	public String[] composeContentToDisplay() {
		String[] content = super.composeContentToDisplay();
		return content;
	}
	
	public boolean isBefore(Event anotherEvent) {
		return true;
	}
	
	public boolean isInDay(DateTime day) {
		return false;
	}
}

