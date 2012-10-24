import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;


public class TestEvent {
	
	Event floatingEvent;
	Event timedEvent;
	Event deadlineEvent;
	Clock startTime = new Clock("10:00 20/7/2007", "HH:mm dd/MM/yyyy");
	Clock endTime = new Clock("11:00 20/7/2007", "HH:mm dd/MM/yyyy");
	Clock deadlineTime = new Clock("9:30 20/7/2007", "HH:mm dd/MM/yyyy");
	Clock reminder = new Clock("9:00 20/7/2007", "HH:mm dd/MM/yyyy");
	String nameFloating = "finish tutorial";
	String nameDeadline = "finish assignment";
	String nameTimed = "lecture";
	String hashTagUnsplitted = "impt high ";
	String[] hashTag = hashTagUnsplitted.split(" "); 
	boolean isDone = false;
	LinkedList<Event> testList = new LinkedList<Event>();
	String floatingToString;
	String deadlineToString;
	String timedToString;
	
	@Before
	public void setUp() {
		floatingEvent = new FloatingEvent("01", nameFloating, hashTag, reminder, false);
		deadlineEvent = new DeadlineEvent("02", nameDeadline, hashTag, reminder, false, deadlineTime);
		timedEvent = new TimedEvent("03", nameTimed, hashTag, reminder, false, startTime, endTime);
		floatingToString = "01..finish tutorial..impt high ..9:00 20/7/2007..HH:mm dd/MM/yyyy..false..";
		deadlineToString = "02..finish assignment..impt high ..9:00 20/7/2007..HH:mm dd/MM/yyyy..false..9:30 20/7/2007..HH:mm dd/MM/yyyy..";
		timedToString =  "03..lecture..impt high ..9:00 20/7/2007..HH:mm dd/MM/yyyy..false..10:00 20/7/2007..HH:mm dd/MM/yyyy..11:00 20/7/2007..HH:mm dd/MM/yyyy..";
		
	}
	
	@Test
	public void testToString() {
		assertEquals("test toString() floating", floatingToString, floatingEvent.toString());
		assertEquals("test toString() deadline", deadlineToString, deadlineEvent.toString());
		assertEquals("test toString() timed", timedToString, timedEvent.toString());

	}
	
	@Test
	public void testIsInDay() {
		DateTime date = new DateTime(2007, 07, 20, 00, 00);
		assertEquals("test isInDay1", false, floatingEvent.isInDay(date));
		assertEquals("test isInDay2", true, deadlineEvent.isInDay(date));
		assertEquals("test isInDay3", true, timedEvent.isInDay(date));
		Clock newStartTime = new Clock("10:30 19/07/2007", "HH:mm dd/MM/yyyy");
		Clock newEndTime = new Clock("11:30 21/07/2007", "HH:mm dd/MM/yyyy");
		
		Event newTimedEvent = new TimedEvent("04", "lecture 2", hashTag, reminder, false, newStartTime, newEndTime);
		assertEquals("test isInDay4", true, newTimedEvent.isInDay(date));
	}
	
	@Test
	public void testSort() {
		testList.add(timedEvent);
		testList.add(floatingEvent);
		testList.add(deadlineEvent);
		Comparator<Event> comparator = new CompareEventByName();
		Collections.sort(testList, comparator);
		assertEquals("test sort Event by name1", deadlineToString, testList.get(0).toString());
		assertEquals("test sort Event by name2", floatingToString, testList.get(1).toString());
		assertEquals("test sort Event by name3", timedToString, testList.get(2).toString());
		
		comparator = new CompareEventByTime();
		Collections.sort(testList, comparator);
		assertEquals("test sort Event by time1", deadlineToString, testList.get(1).toString());
		assertEquals("test sort Event by time2", floatingToString, testList.get(0).toString());
		assertEquals("test sort Event by time3", timedToString, testList.get(2).toString());
		
		comparator = new CompareEventByID();
		Collections.sort(testList, comparator);
		assertEquals("test sort Event by ID1", deadlineToString, testList.get(1).toString());
		assertEquals("test sort Event by ID2", floatingToString, testList.get(0).toString());
		assertEquals("test sort Event by ID3", timedToString, testList.get(2).toString());
	}

	public void testSetUp() throws Exception {
		ListOfEvent.setUpDataFromDatabase();
		Iterator<Event> iter = testList.iterator();
		while(iter.hasNext()) {
			ListOfEvent.add(iter.next());
		}
		ListOfEvent.remove(0);
		ListOfEvent.remove(3);
		ListOfEvent.sortByID();
		ListOfEvent.syncDataToDatabase();	
	}
	
	@Test
	public void testIsClashedWith() {
		assertEquals("test isClashedWith()1", false, timedEvent.isClashedWith(floatingEvent));
		assertEquals("test isClashedWith()2", false, timedEvent.isClashedWith(deadlineEvent));
		Clock newStartTime = new Clock("10:30 20/07/2007", "HH:mm dd/MM/yyyy");
		Clock newEndTime = new Clock("11:30 20/07/2007", "HH:mm dd/MM/yyyy");
		
		Event newTimedEvent = new TimedEvent("04", "lecture 2", hashTag, reminder, false, newStartTime, newEndTime);
		assertEquals("test isClashedWith()3", true, timedEvent.isClashedWith(newTimedEvent));
	}
	
	@Test
	public void testComparator() {
		Comparator<Event> comp = new CompareEventByTime();
		assertEquals("test comparator", 1, comp.compare(timedEvent, deadlineEvent));
		assertEquals("test comparator", 1, comp.compare(deadlineEvent, floatingEvent));
	}
	@Test 
	public void testComposeContentToDisplay() {
		assertEquals("test composeContentToDisplay()", "finish tutorial..#impt #high ..9:00 20/7/2007", floatingEvent.composeContentToDisplay());
	}

	
}

