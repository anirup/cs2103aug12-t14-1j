
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;


public class ListOfEvent {
	private static LinkedList<Event> listOfEvent = new LinkedList<Event>();
	private static LinkedList<String> listOfEventInString = new LinkedList<String>();
	
	private static final int FLOATING_TYPE = 4;
	private static final int DEADLINE_TYPE = 6;
	private static final int TIMED_TYPE = 8;
	
	public static LinkedList<Event> getCurrentListOfEvent() {
		return listOfEvent;
	}
	
	public static LinkedList<String> getListOfEventInString() {
		return listOfEventInString;
	}
	
	public static void setUpDataFromDatabase() throws Exception {
		DatabaseManager.setUpDatabase();
		listOfEventInString = DatabaseManager.retrieveDatabase();
		setUpListOfEvent();
	}
	
	public static void syncDataToDatabase() throws IOException {
		DatabaseManager.syncToDatabase(listOfEventInString);
	}
	
	public static int size() {
		return listOfEvent.size();
	}
	
	public static void markDone(int position)
	{
		listOfEvent.get(position).markDone();
		listOfEventInString.remove(position);
		listOfEventInString.add(position, listOfEvent.get(position).toString());
	}
	
	public static void markUndone(int position) {
		listOfEvent.get(position).markUndone();
		listOfEventInString.remove(position);
		listOfEventInString.add(position, listOfEvent.get(position).toString());
	}	
	
	public static void add(Event newEvent) {
		listOfEvent.add(newEvent);
		listOfEventInString.add(newEvent.toString());
	}
	
	public static Event get(int position) {
		return listOfEvent.get(position);
	}
	
	public static Event remove(int position) {
		listOfEventInString.remove(position);
		return listOfEvent.remove(position);
	}	
	
	public static boolean remove(Event eventToBeDeleted) {
		int indexOfEventToBeDeleted = indexOf(eventToBeDeleted);
		listOfEventInString.remove(indexOfEventToBeDeleted);
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
			eventToUpdate = new DeadlineEvent(newEventID, newEventName, newHashTag, newReminder, isDone, newStartTime);
		} else {
			eventToUpdate = new TimedEvent(newEventID, newEventName, newHashTag, newReminder, isDone, newStartTime, newEndTime);
		}
		
		listOfEvent.remove(position);
		listOfEvent.add(position, eventToUpdate);
		listOfEventInString.remove(position);
		listOfEventInString.add(position, eventToUpdate.toString());
		return eventToUpdate;
	}
	
	public static Event update(int position, Event eventToUpdate) {
		Event removedEvent = listOfEvent.remove(position);
		listOfEvent.add(position, eventToUpdate);
		listOfEventInString.remove(position);
		listOfEventInString.add(position, eventToUpdate.toString());
		return removedEvent;
	}
	
	public static boolean update(Event eventToReplace, Event eventToBeReplaced) {
		int indexOfEventToBeReplaced = indexOf(eventToBeReplaced);
		listOfEventInString.remove(indexOfEventToBeReplaced);
		listOfEventInString.add(indexOfEventToBeReplaced, eventToReplace.toString());
	
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
	
	private static void setUpListOfEvent() {
		Iterator<String> iterator = listOfEventInString.iterator();
		
		while(iterator.hasNext()) {
			String[] currentLine = iterator.next().split(Event.SPLITTER);
			int eventType = currentLine.length;
			if (eventType == FLOATING_TYPE) {
				Event newEvent = new FloatingEvent();
				newEvent.parse(currentLine);
				listOfEvent.add(newEvent);
			} else if (eventType == DEADLINE_TYPE) {
				Event newEvent = new DeadlineEvent();
				newEvent.parse(currentLine);
				listOfEvent.add(newEvent);
			} else if (eventType == TIMED_TYPE) {
				Event newEvent = new TimedEvent();
				newEvent.parse(currentLine);
				listOfEvent.add(newEvent);
			}
		}
		return;
	}
	
	public static ArrayList<String> getListOfEventToDisplay() {
		ListOfEvent.sortByTime();
		ArrayList<String> listToDisplay = new ArrayList<String>();
		Iterator<Event> iterator = listOfEvent.iterator();
		while(iterator.hasNext()) {
			Event currentEvent = iterator.next();
			if(!currentEvent.isDone()) {
				listToDisplay.add(currentEvent.composeContentToDisplay());
			}
		}
		
		return listToDisplay;
	}
}
