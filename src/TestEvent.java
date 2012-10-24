import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestEvent {
	
	@Test 
	public void testParse() {
		String newEvent = "01..project..#work #high ..false..00:05 01/01/1970..17:00 09/09/2010..invalid..";
		Event newDeadline = new DeadlineEvent();
		newDeadline.parse(newEvent.split("\\.."));
		assertEquals("test parse floating", "01..project..#work #high..false..00:05 01/01/1970..17:00 09/09/2010",
				newDeadline.toString());
		
	}
	
	@Test
	public void testParseFromString() {
		String event = "01..project..#work #impt..false..00:05 01/01/1970..05:00 15/10/2012..invalid..";
		
		Event newEvent = EventStringHandler.getEventFromString(event);
		assertEquals("test parse()", "01..project..#work #impt..false.." +
				"00:05 01/01/1970..05:00 15/10/2012..invalid..", newEvent.toString());
		
		
		event = "02..project..#work #impt..false..00:05 01/01/1970..05:00 15/10/2012..19:00 15/10/2012..";
		newEvent = EventStringHandler.getEventFromString(event);
		assertEquals("test parse()", "02..project..#work #impt..false.." +
				"00:05 01/01/1970..05:00 15/10/2012..19:00 15/10/2012..", newEvent.toString());
		
		event = "03..project..#work #impt..false..invalid..invalid..invalid..";
		newEvent = EventStringHandler.getEventFromString(event);
		assertEquals("test parse()", "03..project..#work #impt..false.." +
				"invalid..invalid..invalid..", newEvent.toString());
	}
}



