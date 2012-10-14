import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;


public class TimeAnalyzerTest {
	private static String input;
	@Before 
	public void setUp() {
		input = "7.00am 9-9";
		TimeAnalyzer.setInput(input);
		PatternLib.setUpPattern();
	}
	/*
	@Test
	public void testPrepareSteps() {
		TimeAnalyzer.toLowerCase();
		assertEquals("Test toLowerCase()", "7am    this     mon", TimeAnalyzer.getInput());
		TimeAnalyzer.removeExtraSpace();
		assertEquals("Test removeExtraSpace()", "7am this mon", TimeAnalyzer.getInput());
		TimeAnalyzer.addDateTimeSeparator();
		assertEquals("Test removeExtraSpace()", "7am, this, mon", TimeAnalyzer.getInput());
		TimeAnalyzer.removeSpace();
		assertEquals("Test removeSpace()", "7am,this,mon", TimeAnalyzer.getInput());
		TimeAnalyzer.correctTime();
		assertEquals("Test removeSpace()", "7:00am,this,mon", TimeAnalyzer.getInput());
		TimeAnalyzer.correctDate();
		assertEquals("Test removeSpace()", "7:00am,this,mon", TimeAnalyzer.getInput());
		TimeAnalyzer.removeDateTimeSeparator();
		assertEquals("Test removeSpace()", "7:00am this mon", TimeAnalyzer.getInput());
	}
	*/
	
	@Test
	public void testPrepare() {
		TimeAnalyzer.prepareInputToAnalyze();
		assertEquals("test prepareInputToAnalyze()", "7:00am 9/9", TimeAnalyzer.getInput());
	}
	
	@Test
	public void testAnalyzer() {
		DateTime dt = TimeAnalyzer.analyzeTime("7am this fri");
		assertEquals("test analyzeTime()", 12, dt.getDayOfMonth());
		assertEquals("test isMatch()", 8, PatternLib.isMatch("7:00am this mon"));
	}
}
