
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;


public class ListOfEvent {
	private static LinkedList<Event> listOfEvent = new LinkedList<Event>();
	
	public static LinkedList<Event> getCurrentListOfEvent() {
		return listOfEvent;
	}
	
	public static int size() {
		return listOfEvent.size();
	}
	public static void markDone(int position)
	{
		listOfEvent.get(position).markDone();
	}
	public static void markUndone(int position)
	{
		listOfEvent.get(position).markUndone();
	}	
	public static void updateListOfEvent(LinkedList<Event> newListOfEvent) {
		listOfEvent.clear();
		listOfEvent = newListOfEvent;
		return;
	}
	
	public static void add(Event newEvent) {
		listOfEvent.add(newEvent);
	}
	
	public static Event get(int position) {
		return listOfEvent.get(position);
	}
	
	public static Event remove(int position) {
		return listOfEvent.remove(position);
	}	
	
	public static boolean remove(Event eventToBeDeleted) {
		int indexOfEventToBeDeleted = indexOf(eventToBeDeleted);
		
		if (indexOfEventToBeDeleted != -1) {
			remove(indexOfEventToBeDeleted);
			return true;
		}
		
		return false;
	}
	
	public static Event update(int position, String newEventID, String newEventName, String[] newHashTag, Clock newReminder, Clock newStartTime, Clock newEndTime, boolean isDone) {
		Event eventToUpdate = null;
		
		if (newStartTime == null) {
			eventToUpdate = new FloatingEvent(newEventID, newEventName, newHashTag, newReminder, isDone);
		} else if (newEndTime == null){
			eventToUpdate = new DeadlineEvent(newEventID, newEventName, newHashTag, newReminder, newStartTime, isDone);
		} else {
			eventToUpdate = new TimedEvent(newEventID, newEventName, newHashTag, newReminder, newStartTime, newEndTime, isDone);
		}
		
		listOfEvent.remove(position);
		listOfEvent.add(position, eventToUpdate);
		
		return eventToUpdate;
	}
	
	public static Event update(int position, Event eventToUpdate) {
		Event removedEvent = listOfEvent.remove(position);
		listOfEvent.add(position, eventToUpdate);
		
		return removedEvent;
	}
	
	public static boolean update(Event eventToReplace, Event eventToBeReplaced) {
		int indexOfEventToBeReplaced = indexOf(eventToBeReplaced);
		
		if (indexOfEventToBeReplaced != -1) {
			update(indexOfEventToBeReplaced, eventToReplace);
			return true;
		}
		
		return false;
	}
	
	public static int indexOf(Event event) {
		for (int index = 0; index < listOfEvent.size(); index++) {
			Event currentEvent = listOfEvent.get(index);
			if (currentEvent.isSameEvent(event)) {
				return index;
			}
		}	
		return -1;
	}
	
	public static LinkedList<Event> searchInName(String keyWord) {
		LinkedList<Event> result = new LinkedList<Event>();
		
		Iterator<Event> iterator = listOfEvent.iterator();
		
		while(iterator.hasNext()) {
			Event currentEvent = iterator.next();
			if (currentEvent.searchInName(keyWord)) {
				result.add(currentEvent);
			}
		}
		
		return result;
	}
	
	public static LinkedList<Event> searchInHashTag(String keyWord) {
		LinkedList<Event> result = new LinkedList<Event>();
		
		Iterator<Event> iterator = listOfEvent.iterator();
		
		while(iterator.hasNext()) {
			Event currentEvent = iterator.next();
			if (currentEvent.searchInHashTag(keyWord)) {
				result.add(currentEvent);
			}
		}
		
		return result;
	}
	
	public static LinkedList<Event> searchInTime(Clock keyWord) {
		LinkedList<Event> result = new LinkedList<Event>();
		
		Iterator<Event> iterator = listOfEvent.iterator();
		
		while(iterator.hasNext()) {
			Event currentEvent = iterator.next();
			if (currentEvent.searchInTime(keyWord)) {
				result.add(currentEvent);
			}
		}
		
		return result;
	}
	
	public static void sortByName() {
		Comparator<Event> cmp = new CompareEventByName();
		
		Collections.sort(listOfEvent, cmp);
	}
	
	public static void sortByID() {
		Comparator<Event> cmp = new CompareEventByID();
		
		Collections.sort(listOfEvent, cmp);
	}
	
	public static void sortByTime() {
		Comparator<Event> cmp = new CompareEventByTime();
		
		Collections.sort(listOfEvent, cmp);
	}
}
