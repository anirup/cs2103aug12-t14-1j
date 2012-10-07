import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;


public class TestEvent {
	
	Event floatingEvent;
	Event timedEvent;
	Event deadlineEvent;
	Clock startTime = new Clock("2010 May 05", "yyyy MMM dd");
	Clock endTime = new Clock("2012 May 05", "yyyy MMM dd");
	Clock reminder = new Clock("2010 May 05", "yyyy MMM dd");
	String[] id = new String[30];
	String name = "meeting";
	String hashTagUnsplitted = "impt high ";
	String[] hashTag = hashTagUnsplitted.split(" "); 
	boolean isDone = false;
	String[] floating = new String[10]; 
	String[] deadline = new String[10];
	String[] timed = new String[10];
	LinkedList<Event> testList = new LinkedList<Event>();
	
	@Before
	public void setUp() {
		for(int i = 0; i < 30; i++) {
			id[i] = " ";
			for(int j = 0; j <= i; j++) {
				id[i] = id[i] + "la";
			}
		}
		
		for(int i = 0; i < 10; i++) {
			floating[i] = id[i] + ".." + name + ".." + hashTagUnsplitted + ".." + "2010 May 05..yyyy MMM dd.." + "false..";	
			testList.add(new FloatingEvent(id[i], name, hashTag, reminder, isDone));
		}
		
		for(int i = 10; i < 20; i++) {
			deadline[i-10] = id[i] + ".." + name + ".." + hashTagUnsplitted + ".." + "2010 May 05..yyyy MMM dd.." + "false.." + "2010 May 05..yyyy MMM dd..";
			testList.add(new DeadlineEvent(id[i], name, hashTag, reminder, isDone, startTime));
		}
		
		for(int i = 20; i < 30; i++) {
			timed[i-20] = id[i] + ".." + name + ".." + hashTagUnsplitted + ".." + "2010 May 05..yyyy MMM dd.." + "false.." + "2010 May 05..yyyy MMM dd..2012 May 05..yyyy MMM dd..";
			testList.add(new TimedEvent(id[i], name, hashTag, reminder, isDone, startTime, endTime));
		}	
	}
	
	@Test
	public void testToString() {
		assertEquals("test toString()", floating[0], testList.get(0).toString());
		assertEquals("test toString() 2", deadline[0], testList.get(10).toString());
		assertEquals("test toString() 3", timed[0], testList.get(20).toString());
	}
	
	@Test
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
	public void testParse() {
		Event newFloating = new FloatingEvent();
		newFloating.parse(floating[0].split("\\.."));
		assertEquals("test Parse()", floating[0], newFloating.toString());
		Event newDeadline = new DeadlineEvent();
		newDeadline.parse(deadline[0].split("\\.."));
		assertEquals("test Parse() 2", deadline[0], newDeadline.toString());
		Event newTimed = new TimedEvent();
		newTimed.parse(timed[0].split("\\.."));
		assertEquals("test Parse() 3", timed[0], newTimed.toString());	
		
	}

	
}

