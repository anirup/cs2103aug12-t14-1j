
import java.util.Comparator;

public class CompareEventByTime implements Comparator<Event>{

	@Override
	public int compare(Event firstEvent, Event secondEvent) {
		if (firstEvent.isBefore(secondEvent)) {
			return -1;
		} else {
			return 1;
		}
	}

}
