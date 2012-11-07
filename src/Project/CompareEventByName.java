package Project;


import java.util.Comparator;


public class CompareEventByName implements Comparator<Event>{

	@Override
	public int compare(Event firstEvent, Event secondEvent) {
		String nameOfFirstEvent = firstEvent.getEventName();
		String nameOfSecondEvent = secondEvent.getEventName();
		
		return nameOfFirstEvent.compareToIgnoreCase(nameOfSecondEvent);
	}
	
}
