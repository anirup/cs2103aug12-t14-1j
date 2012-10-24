import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;


public class TestClock {
	String dateFormat;
	String dateInString;
	String clockTestToString;
	
	@Before
	public void setUp() {
		DateTime date = new DateTime(2010, 5, 5, 23, 59);
		dateFormat = "yyyy MMM dd HH mm";
		
		DateTimeFormatter dft = DateTimeFormat.forPattern(dateFormat);
		
		dateInString = date.toString(dft);
		
		clockTestToString = "2010 May 05 23 59.." + dateFormat + "..";
	}
	
	@Test
	public void testToString() {
		Clock clockTest = new Clock(dateInString, dateFormat);
		
		assertEquals("test toString()", clockTestToString, clockTest.toString());
	}

	@Test 
	public void testIsBefore() {
		Clock first = new Clock("25/05/2007", "dd/MM/yyyy");
		Clock second = new Clock("7:00 27/05/2007", "HH:mm dd/MM/yyyy");
		assertEquals("test isBefore(Clock)", true, first.isBefore(second));
		assertEquals("test isBefore(DateTime", true, first.isBefore(DateTime.now()));
	}
	
	@Test
	public void testIsInDay() {
		Clock first = new Clock("07/07/2007", "dd/MM/yyyy");
		Clock second = new Clock("7:00am 07/07/2007", "hh:mmaa dd/MM/yyyy");
		Clock third = new Clock("10/07/2007", "dd/MM/yyyy");
		DateTime date = new DateTime(2007, 07, 07, 0, 0);
		assertEquals("test isInDay()", true, first.isInDay(date));
		assertEquals("test isInDay()", false, third.isInDay(date));
		assertEquals("test isInDay()", true, second.isInDay(date));
	}
	
	@Test
	public void testToDate() {
		Clock clockTest = new Clock(dateInString, dateFormat);
		
		DateTime dt = new DateTime(2010, 5, 5, 23, 59);
		
		assertEquals("test ToDate()", dt, clockTest.toDate());
	}
	
	
}
