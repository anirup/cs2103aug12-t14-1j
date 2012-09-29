
import java.text.ParseException;
import java.util.Comparator;

public class CompareEventByTime implements Comparator<Event>{

	@Override
	public int compare(Event firstEvent, Event secondEvent) {
		Clock timeOfFirstEvent = firstEvent.getEventTime();
		Clock timeOfSecondEvent = secondEvent.getEventTime();
		
		try {
			return timeOfFirstEvent.compareTo(timeOfSecondEvent);
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		
	}

}
