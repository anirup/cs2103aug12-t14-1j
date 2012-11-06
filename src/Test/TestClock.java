package Test;


import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Before;
import org.junit.Test;

import Project.Clock;


public class TestClock {
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void testToString() {		
		DateTime date = new DateTime(2007, 7, 7, 7, 7);
		assertEquals("test toString()", "07:07 07/07/2007", Clock.toString(date));
	}

	@Test 
	public void testIsBefore() {
		DateTime date = new DateTime(2007, 7, 7, 7, 7);
		DateTime anotherDate = new DateTime(2007, 7, 7, 7, 8);

		assertEquals("test isBefore(Clock)", true, Clock.isBefore(date, anotherDate));
		assertEquals("test isBefore(Clock)", false, Clock.isBefore(anotherDate, date));
		assertEquals("test isBefore(DateTime", true, Clock.isBefore(date, DateTime.now()));
	}
	
	@Test
	public void testIsInDay() {
		DateTime first = new DateTime(2007, 07, 07, 17, 0);
		DateTime second = new DateTime(2007, 07, 07, 16, 0);
		DateTime third = new DateTime(2007, 07, 10, 0, 0);
		DateTime date = new DateTime(2007, 07, 07, 0, 0);
		assertEquals("test isInDay()", true, Clock.isInDay(first, date));
		assertEquals("test isInDay()", false, Clock.isInDay(third, date));
		assertEquals("test isInDay()", true, Clock.isInDay(second, date));
	}
	
	@Test
	public void testToDate() {
		String dateInString = "00:00 07/07/2007";
		
		DateTime dt = new DateTime(2007, 7, 7, 0, 0);
		
		assertEquals("test ToDate()", dt.toString(DateTimeFormat.forPattern("HH:mm dd/MM/yyyy")), 
				Clock.parseTimeFromString(dateInString).toString(DateTimeFormat.forPattern("HH:mm dd/MM/yyyy")));
	}
}
