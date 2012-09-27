import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class ListOfEvent {
	private static List<Event> listOfEvent = new LinkedList<Event>();
	
	public static List<Event> getCurrentListOfEvent() {
		return listOfEvent;
	}
	
	public static int size() {
		return listOfEvent.size();
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
	
	public static void sortByTime() {
		Collections.sort(listOfEvent);
	}
	
	// IMPT: at the end of all use case, u might want to sync the current list of events to the text file 
	public static void syncToDatabase() throws IOException {
		for (int position = 0; position < listOfEvent.size(); position++) {
			Event eventToUpdate = listOfEvent.get(position);
			DatabaseManager.updateDatabase(eventToUpdate);
		}
		return;
	}
}
