package event;

import java.util.Comparator;


public class CompareEventByPriority implements Comparator<Event>{

	private static final String PRIORITY_LOW = "Low";
	private static final String PRIORITY_NORMAL = "Normal";
	private static final String PRIORITY_HIGH = "high";
	
	public int compare(Event firstEvent, Event secondEvent) {
		int firstEventPriority = returnPriorityValue(firstEvent);
		int secondEventPriority = returnPriorityValue(secondEvent);
		return firstEventPriority - secondEventPriority;
	}
	
	private static int returnPriorityValue(Event event) {
		String p = event.getPriority();
		if (p.equalsIgnoreCase(PRIORITY_HIGH))
			return 0;
		else if (p.equalsIgnoreCase(PRIORITY_NORMAL))
			return 1;
		else if (p.equalsIgnoreCase(PRIORITY_LOW))
			return 2;
		return 1;
	}


}
