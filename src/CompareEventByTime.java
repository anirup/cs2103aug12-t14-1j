
import java.util.Comparator;

public class CompareEventByTime implements Comparator<Event>{

	private static final String PRIORITY_LOW = "Low";
	private static final String PRIORITY_NORMAL = "Normal";
	private static final String PRIORITY_HIGH = "high";
	
	@Override
	public int compare(Event a, Event b) {
		if (!a.getEventEndTime().toString()
				.equals(b.getEventEndTime().toString()))
			return getDateOrder(a, b);
		else
			return returnPriorityValue(getEventPriority(a))
					- returnPriorityValue(getEventPriority(b));

	}
	
	private static int returnPriorityValue(String p) {
		if (p.equalsIgnoreCase(PRIORITY_HIGH))
			return 0;
		else if (p.equalsIgnoreCase(PRIORITY_NORMAL))
			return 1;
		else if (p.equalsIgnoreCase(PRIORITY_LOW))
			return 2;
		return 1;
	}

	private static int getDateOrder(Event a, Event b) {
		if (a.getEventEndTime().isBefore(b.getEventEndTime()))
			return -1;
		else if (a.getEventEndTime().isAfter(b.getEventEndTime()))
			return 1;
		else
			return 0;
	}

	private static String getEventPriority(Event a) {
		return a.getEventHashTag().split("#")[1].trim().toLowerCase();
	}
	
}
