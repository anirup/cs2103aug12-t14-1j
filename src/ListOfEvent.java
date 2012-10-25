import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

import org.joda.time.DateTime;


public class ListOfEvent {
	private static ArrayList<Event> listOfEvent = new ArrayList<Event>();
	private static final String fileName = "What2Do.txt";
	private static final String displayFormat = "%1$d..%2$s";
	private static final String STRING_NULL = "";
	
	public static ArrayList<Event> getCurrentListOfEvent() {
		return listOfEvent;
	}
	
	public static void setUpDataFromDatabase() throws Exception {
		EventStringHandler.setUpDataFromDatabase(fileName);
		listOfEvent = EventStringHandler.getCurrentListOfEvent();
	}
	
	public static void syncDataToDatabase() throws IOException {
		EventStringHandler.syncDataToDatabase(listOfEvent, fileName);
	}
	
	public static int size() {
		return listOfEvent.size();
	}
	
	public static void markDone(int position)
	{
		ListOfEvent.get(position).markDone();
	}
	
	public static void markUndone(int position) {
		ListOfEvent.get(position).markUndone();
	}
	
	public static void add(Event newEvent) {
		listOfEvent.add(newEvent);
	}
	
	public static void add(String eventInString) {
		Event newEvent = EventStringHandler.getEventFromString(eventInString);
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
	
	public static ArrayList<Event> searchInName(String keyWord) {
		ArrayList<Event> result = new ArrayList<Event>();
		
		Iterator<Event> iterator = listOfEvent.iterator();
		
		while(iterator.hasNext()) {
			Event currentEvent = iterator.next();
			if (currentEvent.seachInName(keyWord)) {
				result.add(currentEvent);
			}
		}
		
		return result;
	}
	
	public static ArrayList<Event> searchInHashTag(String keyWord) {
		ArrayList<Event> result = new ArrayList<Event>();
		Iterator<Event> iterator = listOfEvent.iterator();
		while(iterator.hasNext()) {
			Event currentEvent = iterator.next();
			if (currentEvent.searchInHashTag(keyWord)) {
				result.add(currentEvent);
			}
		}
		return result;
	}
	
	public static ArrayList<EventForSort> searchInNameAndHashTags(Vector<String> searchWords) {
		
		ArrayList<EventForSort> searchResults = new ArrayList<EventForSort>();
		int size = ListOfEvent.size();
		for (int i = 0; i < size; i++) {
			if (searchWords.isEmpty())
				break;
			boolean isChecked = false;
			String searchCheck = STRING_NULL;
			String[] tags = ListOfEvent.get(i).getEventHashTag().split("#");
			for (int j = 0; j < tags.length; j++) {
				searchCheck += tags[j];
				searchCheck += ".";
			}
			searchCheck += ListOfEvent.get(i).getEventName();
			for (int k = 0; k < searchWords.size(); k++) {
				if (searchCheck.toLowerCase().contains(
						searchWords.get(k).toLowerCase())
						&& isChecked == false) {
					searchResults.add(new EventForSort(i, ListOfEvent.get(i)));
					break;
				}
			}
		}
		return searchResults;
	}
	
	public static ArrayList<Event> searchInTime(Clock keyWord) {
		ArrayList<Event> result = new ArrayList<Event>();
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
	
	public static void sortByPriority() {
		Comparator<Event> cmp = new CompareEventByPriority();		
		Collections.sort(listOfEvent, cmp);
	}

	public static ArrayList<String[]> getListOfEventInDay(DateTime date) {
		ArrayList<String[]> listToDisplay = new ArrayList<String[]>();
		for(int index = 0; index < listOfEvent.size(); index++) {
			Event currentEvent = listOfEvent.get(index);
			if(currentEvent.isInDay(date)) {
				listToDisplay.add(currentEvent.composeContentToDisplay());
			}
		}
		return listToDisplay;
	}
	
	public static ArrayList<String[]> getListOfEventToDisplay(int eventType) {
		ArrayList<String[]> listToDisplay = new ArrayList<String[]>();
		for(int index = 0; index < listOfEvent.size(); index++) {
			Event currentEvent = listOfEvent.get(index);
			if(currentEvent.getEventType() == eventType) {
				listToDisplay.add(currentEvent.composeContentToDisplay());
			}
		}
		return listToDisplay;
	}
	
	public static ArrayList<String[]> getListOfEventToDisplaySortedByTime() {
		ListOfEvent.sortByTime();
		ArrayList<String[]> listToDisplay = new ArrayList<String[]>();
		Iterator<Event> iterator = listOfEvent.iterator();
		while(iterator.hasNext()) {
			Event currentEvent = iterator.next();
			if(!currentEvent.isDone()) {
				listToDisplay.add(currentEvent.composeContentToDisplay());
			}
		}
		return listToDisplay;
	}
	
	public static ArrayList<String> getListOfEventToDisplayInString() {
		ArrayList<String> listToDisplay = new ArrayList<String>();

		for(int index = 0; index < listOfEvent.size(); index++) {
			String contentToDisplay = listOfEvent.get(index).composeContentToDisplayInString();
			contentToDisplay = String.format(displayFormat, index + 1, contentToDisplay);
			listToDisplay.add(contentToDisplay);
		}
		return listToDisplay;
	}
}
