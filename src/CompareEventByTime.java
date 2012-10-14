
import java.util.Comparator;

public class CompareEventByTime implements Comparator<Event>{

	@Override
	public int compare(Event firstEvent, Event secondEvent) {
		Clock timeOfFirstEvent = firstEvent.getEventTime();
		Clock timeOfSecondEvent = secondEvent.getEventTime();
		return timeOfFirstEvent.compareTo(timeOfSecondEvent);	
	}

}
