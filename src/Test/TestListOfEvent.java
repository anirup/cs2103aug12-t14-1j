package Test; 

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import event.Event;
import event.Event.PRIORITY_TYPE;
import event.ListOfEvent;
import global.Clock;


public class TestListOfEvent {
	String[] id = {"1", "2", "3", "4", "5"};
	String[] name = {"assignment", "project", "homework", "tutorial", "facebook time"};
	String[] priority = {"high", "low", "normal", "low", "high"};
	String[] hashTag = {"#ma3264", "#cs2103", "#ma3233 #ma3236", "#all", "#play"};
	
	ArrayList<DateTime> start = new ArrayList<DateTime>();
	ArrayList<DateTime> end = new ArrayList<DateTime>();
	ArrayList<DateTime> reminder = new ArrayList<DateTime>();
	ArrayList<DateTime> completed = new ArrayList<DateTime>();
	
	Event floating, deadline, timed, clashTimed1, clashTimed2, clashTimed3, clashTimed4;
	
	@Before
	public void setUp() {
		start.add(new DateTime(2000, 9, 7, 10, 10));
		end.add(start.get(0).plusHours(1));
		reminder.add(start.get(0).minusMinutes(5));
		completed.add(start.get(0));
		
		start.add(new DateTime(2012, 11, 7, 22, 14));
		end.add(start.get(1).plusHours(2));
		reminder.add(start.get(0).minusMinutes(10));
		completed.add(new DateTime(1970, 1, 1, 0, 0));
		
		start.add(new DateTime(2012, 11, 7, 22, 10));
		end.add(start.get(2).plusHours(3));
		reminder.add(start.get(0).minusMinutes(15));
		completed.add(new DateTime(1970, 1, 1, 0, 0));
		
		start.add(new DateTime(2012, 11, 7, 21, 15));
		end.add(start.get(3).plusHours(2));
		reminder.add(start.get(0).minusMinutes(20));
		completed.add(start.get(3));
		
		start.add(new DateTime(2012, 11, 7, 22, 15));
		end.add(start.get(4).plusHours(1));
		reminder.add(start.get(0).minusMinutes(25));
		completed.add(new DateTime(1970, 1, 1, 0, 0));
		
		start.add(new DateTime(2012, 11, 7, 22, 10));
		end.add(start.get(1).plusHours(2));
		reminder.add(start.get(0).minusMinutes(10));
		completed.add(new DateTime(1970, 1, 1, 0, 0));
		
		floating = new Event("01", "floating", PRIORITY_TYPE.HIGH, "#floating", Clock.getBigBangTime(), Clock.getBigBangTime(), Clock.getBigBangTime());
		deadline = new Event("02", "deadline", PRIORITY_TYPE.NORMAL, "#deadline", reminder.get(0), start.get(0), Clock.getBigBangTime());
		timed = new Event("03", "timed", PRIORITY_TYPE.LOW, "#timed", reminder.get(1), start.get(1), end.get(1));
		clashTimed1 = new Event ("04", "clashed timed1", PRIORITY_TYPE.LOW, "#timed", reminder.get(1), start.get(2), end.get(2));
		clashTimed2 = new Event ("05", "clashed timed2", PRIORITY_TYPE.LOW, "#timed", reminder.get(1), start.get(3), end.get(3));
		clashTimed3 = new Event ("06", "clashed timed3", PRIORITY_TYPE.LOW, "#timed", reminder.get(1), start.get(4), end.get(4));
													
	}
	
	@After
	public void clearUp() {
		
	}
	
	@Test
	public void testAdd() {
		String floatingInString = "01..floating..LOW..#work #impt..false..invalid..invalid..invalid..invalid";
		String deadlineInString = "02..deadline..HIGH..#work #impt..false..00:05 01/01/1970..05:00 15/10/2012..invalid..invalid";
		String timedInString = "03..timed..NORMAL..#work #impt..false..00:05 01/01/1970..05:00 15/10/2012..19:00 15/10/2012..invalid";
		ListOfEvent.add(floatingInString);
		ListOfEvent.add(deadlineInString);
		ListOfEvent.add(timedInString);
		String invalidReminder = "04..project..LOW..#work #impt..false..00:05 01/01/1970..invalid..invalid..invalid";
		String invalidPriority = "05..project..high LOW..#work #impt..false..invalid..invalid..invalid..invalid";
		String invalidIsDone = "05..project..high LOW..#work #impt..falses ..invalid..invalid..invalid..invalid";
		String invalidEventID = "01..lalala..high LOW..#work #impt..false..invalid..invalid..invalid..invalid";
		String invalidTime = "05..project..high LOW..#work #impt..false..invalid..invalid..kaka..invalid";
		String invalidString = "..";
		ListOfEvent.add(invalidReminder);
		ListOfEvent.add(invalidPriority);
		ListOfEvent.add(invalidIsDone);
		ListOfEvent.add(invalidEventID);
		ListOfEvent.add(invalidTime);
		ListOfEvent.add(invalidString);
		
		assertEquals("test add()1", 3, ListOfEvent.size());
		assertEquals("test add()2", floatingInString, ListOfEvent.get(0).toString());
		assertEquals("test add()3", deadlineInString, ListOfEvent.get(1).toString());
		assertEquals("test add()4", timedInString, ListOfEvent.get(2).toString());
	}
	
	@Test
	public void testSearch() {
		
	}
}