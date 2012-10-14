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

	public void testToDate() {
		Clock clockTest = new Clock(dateInString, dateFormat);
		
		DateTime dt = new DateTime(2010, 5, 5, 23, 59);
		
		assertEquals("test ToDate()", dt, clockTest.toDate());
	}
	
	public void testCompareTo() {
		Clock clockTest = new Clock(dateInString, dateFormat);
		Clock clockTest2 = new Clock(dateInString, dateFormat);
		Clock clockTest3 = new Clock("2012 May 06 00 00", "yyyy MMM dd HH mm");
		Clock clockTest4 = new Clock("2010 May 04 00 00", "yyyy MMM dd");
		assertEquals("test compareTo()", -1, clockTest.compareTo(clockTest3));
		assertEquals("test compareTo()", 0, clockTest.compareTo(clockTest2));
		assertEquals("test compareTo()", 0, clockTest.compareTo(clockTest4));
		
		DateTime dt1 = new DateTime(2012, 5, 5, 0, 0, 0);
		DateTime dt2 = new DateTime(2010, 5, 5, 0, 0, 0);
		
		assertEquals("test compareTo()", true, dt1.isBefore(dt2));
	}
}
