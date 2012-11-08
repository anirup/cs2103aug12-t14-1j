package event;

import executor.Executor;
import fileIO.DatabaseManager;
import global.Clock;
import global.StringOperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import org.joda.time.DateTime;

import alarm.AlarmType;
import alarm.ListOfAlarm;

public class ListOfEvent {
	private static ArrayList<Event> listOfEvent = new ArrayList<Event>();
	private static ArrayList<Event> searchResults = new ArrayList<Event>();
	private static ArrayList<ListOfEventObserver> listOfObserver= new ArrayList<ListOfEventObserver>();
	private static final String fileName = "What2Do.txt";
	private static final String displayFormat = "%1$d..%2$s";
	private static final int INDEX_OF_PRIORITY = 2;
	private static final int INDEX_OF_EVENT_ISDONE = 4;
	private static final int INDEX_OF_REMINDER_TIME = 5;
	private static final int INDEX_OF_COMPLETED_TIME = 8;
	private static ArrayList<String> feedback = new ArrayList<String>();
	private static final String STRING_NULL = "";

	
	public static void addObserver(ListOfEventObserver observer) {
		listOfObserver.add(observer);
	}
	
	public static void notifyObservers() {
		int numberOfObserver = listOfObserver.size();
		for(int index = 0; index < numberOfObserver; index++) {
			listOfObserver.get(index).updateListOfEvent();
		}	
	}
	
	public static void setUpDataFromDatabase() throws Exception {
		addObserver(Executor.getInstance());
		addObserver(ListOfAlarm.getInstance());
		DatabaseManager.setUpDataFromDatabase(fileName);
		notifyObservers();
	}
	
	public static void syncDataToDatabase() throws IOException {
		ArrayList<String> listOfEventInString = new ArrayList<String>();
		listOfEventInString = getContentToSyncToDatabase();
		DatabaseManager.syncDataToDatabase(listOfEventInString, fileName);
	}
	
	public void clearFeedback() {
		feedback.clear();
		return;
	}
	
	public static ArrayList<String> getFeedback() {
		return feedback;
	}
	
	public static int size() {
		return listOfEvent.size();
	}
	
	public static void markDoneSearch(int position)
	{
		searchResults.get(position).markDone();
		int index = indexOf(searchResults.get(position));
		markDoneList(index);
	}
	
	public static void markUndoneSearch(int position)
	{
		searchResults.get(position).markUndone();
		int index = indexOf(searchResults.get(position));
		markUndoneList(index);
	}
	
	public static void markUndoneList(int position) {
		listOfEvent.get(position).markUndone();
	}
	
	public static void markDoneList(int position) {
		listOfEvent.get(position).markDone();
	}
	
	public static void markUndoneList(Event event) {
		int index = indexOf(event);
		assert index >= 0;
		listOfEvent.get(index).markUndone();
	}
	
	public static void add(Event newEvent) {
		listOfEvent.add(newEvent);
	}
	
	public static Event add(String eventInString) {
		Event newEvent = getEventFromString(eventInString);
		if(newEvent != null) {
			isClashedWithExistingEvents(newEvent);
			isBeforeCurrentTime(newEvent);
			listOfEvent.add(newEvent);
		}
		return newEvent;
	}
	
	private static void isClashedWithExistingEvents(Event newEvent) {
		for(int index = 0; index < listOfEvent.size(); index++) {
			Event currentEvent = listOfEvent.get(index);
			if(currentEvent.isClashedWith(newEvent)) {
				feedback.add("The new Event is Clashed with exixting Events. Do you want to add?");
				return;
			}
		}
	}

	private static void isBeforeCurrentTime(Event newEvent) {
		if(newEvent.getEventType() == Event.FLOATING_TYPE) {
			return;
		} else if (newEvent.getEventEndTime().isBeforeNow()) {
			feedback.add("The new Event has already passed. Do you want to add?");
			return;
		}
	}
	
	public static Event get(int position) {
		return listOfEvent.get(position);
	}
	
	public static Event removeList(int position) {
		return remove(position, listOfEvent);
	}	
	
	public static Event removeSearch(int position) {
		return remove(position, searchResults);
	}
	
	private static Event remove(int position, ArrayList<Event> list) {
		assert list.size() > position;
		return list.remove(position);
	}
	
	public static Event remove(Event eventToRemove) {
		int index = indexOf(eventToRemove);
		return remove(index, listOfEvent);
	}
	
	public static ArrayList<Event> updateSearch(int position, String eventToUpdate) {
		assert position <= searchResults.size();
		ArrayList<Event> update = new ArrayList<Event>();
		Event removedEvent = searchResults.remove(position);
		Event updatedEvent = getEventFromString(eventToUpdate);
		if(updatedEvent != null) {
			searchResults.add(updatedEvent);
			update(removedEvent, updatedEvent);
			update.add(removedEvent);
			update.add(updatedEvent);			
			return update;
		}
		return null;
	}
	
	public static ArrayList<Event> updateList(int position, String eventToUpdate) {
		ArrayList<Event> update = new ArrayList<Event>();
		Event removedEvent = listOfEvent.remove(position);
		Event updatedEvent = getEventFromString(eventToUpdate);
		listOfEvent.add(position,updatedEvent);
	//	update(removedEvent, updatedEvent);
		update.add(removedEvent);
		update.add(updatedEvent);
		return update;
	}
	
	public static boolean update(Event eventToReplace, Event eventToBeReplaced) {
		int indexOfEventToBeReplaced = indexOf(eventToBeReplaced);
		if (indexOfEventToBeReplaced != -1) {
			listOfEvent.remove(indexOfEventToBeReplaced);
			listOfEvent.add(indexOfEventToBeReplaced, eventToReplace);
			return true;
		}
		return false;
	}
	
	public static void searchInNameAndHashTags(Vector<String> searchWords) {
		searchResults.clear();
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
						&& isChecked == false && !searchWords.get(k).trim().equalsIgnoreCase("")) {
					searchResults.add(ListOfEvent.get(i));
					break;
				}
			}
		}
	}
	
//	public static void searchInNameAndHashTag(ArrayList<String> searchWords) {
//		searchResults = new ArrayList<Event>();
//		int numberOfSearchWords = searchWords.size();
//		for(int indexOfSearchWord = 0; indexOfSearchWord < numberOfSearchWords; indexOfSearchWord++) {
//			String currentSearchWord = searchWords.get(indexOfSearchWord);
//			searchForAWordInNameAndHashTag(currentSearchWord);
//		}
//		return;
//	}
//	
//	private static void searchForAWordInNameAndHashTag(String searchWord) {
//		int currentNumberOfEvents = listOfEvent.size();
//		for(int indexEvent = 0; indexEvent < currentNumberOfEvents; indexEvent++) {
//			Event currentEvent = listOfEvent.get(indexEvent);
//			if(currentEvent.seachInName(searchWord) 
//					|| currentEvent.searchInHashTag(searchWord)) {
//				searchResults.add(currentEvent);
//			}
//		}
//		return;
//	}

	public static ArrayList<AlarmType> setUpListOfReminder() {
		ArrayList<AlarmType> reminderList = new ArrayList<AlarmType>();
		for(int index = 0; index < listOfEvent.size(); index++) {
			Event currentEvent = listOfEvent.get(index);
			DateTime eventReminder = currentEvent.getEventReminder();
			if(!Clock.isSameTime(Clock.getBigBangTime(), eventReminder) 
					&& !currentEvent.isDone() && !Clock.isBefore(eventReminder, DateTime.now())) {
				AlarmType newAlarm = new AlarmType(currentEvent.getEventName(), eventReminder);
				reminderList.add(newAlarm);
			}
		}
		return reminderList;
	}
	
	public static void sortList() {
		listOfEvent = sort(listOfEvent);
	}
	
	public static ArrayList<Event> sort(ArrayList<Event> list) {
		Comparator<Event> cmp = new CompareEventByTime();		
		Collections.sort(list, cmp);
		return list;
	}
	
	private static ArrayList<String> toDisplay(ArrayList<Event> list) {
		ArrayList<String> listToDisplay = new ArrayList<String>();

		for(int index = 0; index < list.size(); index++) {
			Event currentEvent = list.get(index);
			if(currentEvent.getEventType() != Event.FLOATING_TYPE) {
				String contentToDisplay = currentEvent.composeContentToDisplayInString();
				contentToDisplay = String.format(displayFormat,  index + 1, contentToDisplay);
				listToDisplay.add(contentToDisplay);
			}
		}
		return listToDisplay;
	}
	
	private static ArrayList<String> toDisplayForSearch(ArrayList<Event> list) {
		ArrayList<String> listToDisplay = new ArrayList<String>();

		for(int index = 0; index < list.size(); index++) {
			Event currentEvent = list.get(index);
			
				String contentToDisplay = currentEvent.composeContentToDisplayInString();
				contentToDisplay = String.format(displayFormat,  index + 1, contentToDisplay);
				listToDisplay.add(contentToDisplay);
			
		}
		return listToDisplay;
	}

	private static ArrayList<String> floatingToDisplay() {
		ArrayList<String> listToDisplay = new ArrayList<String>();
		for(int index = 0; index < listOfEvent.size(); index++) {
			Event currentEvent = listOfEvent.get(index);
			if(currentEvent.getEventType() == Event.FLOATING_TYPE) {
				String contentToDisplay = listOfEvent.get(index).composeContentToDisplayInString();
				contentToDisplay = String.format(displayFormat, index + 1, contentToDisplay);
				listToDisplay.add(contentToDisplay);
			}
		}
		return listToDisplay;
	}
	
	public static Event getEventFromString(String eventInString) {
		String[] eventContent = eventInString.split("\\..");
		if(!isValidString(eventContent)) {
			return null;
		}
		Event newEvent = new Event();
		newEvent.parse(eventContent);
		if(!isValidNewEvent(newEvent)) {
			return null;
		}
		return newEvent;
	}
	
	private static boolean isValidNewEvent(Event newEvent) {
		if(isObsoleteEvent(newEvent) || !isValidEventTime(newEvent) || !checkEventID(newEvent)) {
			return false;
		}
		return true;
	}
	
	private static boolean isValidEventTime(Event newEvent) {
		DateTime reminder = newEvent.getEventReminder();
		DateTime start = newEvent.getEventStartTime();
		DateTime end = newEvent.getEventEndTime();
		if(!Clock.isBigBangTime(reminder) && Clock.isBigBangTime(start)) {
			feedback.add("Error: Floating events cant have reminder");
			return false;
		} else if(!Clock.isBigBangTime(end) && Clock.isBigBangTime(start))  {
			return false;
		} else if(Clock.isBefore(end, start) && !Clock.isBigBangTime(end)) {
			feedback.add("Error: End time is before start time");
			return false;
		}
		return true;
	}

	private static boolean isObsoleteEvent(Event event) {
		DateTime completedTime = event.getTimeCompleted();
		if(Clock.isBefore(completedTime, DateTime.now().minusMonths(1)) && !Clock.isBigBangTime(completedTime)) {
			return true;
		}
		return false;
	}
	
	private static boolean isValidString(String[] eventContent) {
		if(eventContent.length != 9) {
			return false;
		} 
		String isDone = eventContent[INDEX_OF_EVENT_ISDONE];
		if(!StringOperation.isValidIsDone(isDone)) {
			return false;
		}
		for(int timeFieldIndex = INDEX_OF_REMINDER_TIME; timeFieldIndex <= INDEX_OF_COMPLETED_TIME; timeFieldIndex++) {
			if(!Clock.isValidTimeInString(eventContent[timeFieldIndex])) {
				return false;
			}
		}
		String priority = eventContent[INDEX_OF_PRIORITY];
		if(!(priority.trim().equalsIgnoreCase("high") || priority.trim().equalsIgnoreCase("low") ||
				priority.trim().equalsIgnoreCase("normal"))) {
			return false;
		}
		return true;
	}
	
	
	private static ArrayList<String> getContentToSyncToDatabase() {
		ArrayList<String> listOfEventToString = new ArrayList<String>();
		for(int index = 0; index < listOfEvent.size(); index++) {
			listOfEventToString.add(listOfEvent.get(index).toString());
		}
		return listOfEventToString;
	}

	private static int indexOf(Event event) {
		for (int index = 0; index < listOfEvent.size(); index++) {
			Event currentEvent = listOfEvent.get(index);
			if (currentEvent.isSameEvent(event)) {
				return index;
			}
		}	
		return -1;
	}
	
	public static boolean checkEventID(Event newEvent) {
		for(int index = 0; index < listOfEvent.size(); index++) {
			if(newEvent.isSameEvent(listOfEvent.get(index))) {
				return false;
			}
		}
		return true;
	}

	public static ArrayList<String> getSearchResultsToDisplayInString() {
		searchResults = sort(searchResults);
		ArrayList<String> searchResultsToDisplay = toDisplayForSearch(searchResults);
		
		return searchResultsToDisplay;
	}
	
	public static ArrayList<String> getListOfEventToDisplayInString() {
		listOfEvent = sort(listOfEvent);
		return toDisplay(listOfEvent);
	}
	
	public static ArrayList<String> getListOfFloatingEventToDisplayInString() {
		return floatingToDisplay();
	}
	
	public static void formatListOfEvent() throws Exception {
		listOfEvent.clear();
		searchResults.clear();
		setUpDataFromDatabase();
	} 	
}
