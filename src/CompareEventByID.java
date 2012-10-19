import java.util.Comparator;
public class CompareEventByID implements Comparator<Event>{

	@Override
	public int compare(Event firstEvent, Event secondEvent) {
		String firstEventID = firstEvent.getEventID();
		String secondEventID = secondEvent.getEventID();
		
		return firstEventID.compareToIgnoreCase(secondEventID);
	}
}
