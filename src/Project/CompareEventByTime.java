package Project;


import java.util.Comparator;


public class CompareEventByTime implements Comparator<Event>{
	
	@Override
	public int compare(Event a, Event b) {
		if (getDateOrder(a,b) != 0) {
			return getDateOrder(a,b);
		}
		Comparator<Event> cmp = new CompareEventByPriority();
		return cmp.compare(a, b);
	}
	
	private static int getDateOrder(Event a, Event b) {
		if (a.getEventEndTime().isBefore(b.getEventEndTime()))
			return -1;
		else if (a.getEventEndTime().isAfter(b.getEventEndTime()))
			return 1;
		else
			return 0;
	}

	
}
