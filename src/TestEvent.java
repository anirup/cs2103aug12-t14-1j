/*import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class TestEvent {
	CommandAnalyzerAdd cmd;
	@Before
	public void setUp() {
		PatternLib.setUpPattern();
	}
	
	@Test 
	public void testParse() {
		cmd = new CommandAnalyzerAdd("add project #work #high r- 5 min 5pm 9Sep2010");
		String newEvent = "01.." + cmd.getCommandContent();
		assertEquals("get Command content",  "01..project..#work #high ..false.." +
				"00:05 01/01/1970..17:00 09/09/2010..invalid..",
				newEvent);
		Event newDeadline = new DeadlineEvent();
		newDeadline.parse(newEvent.split("\\.."));
		assertEquals("test parse floating", "01..project..#work #high..false..00:05 01/01/1970..17:00 09/09/2010",
				newDeadline.toString());
		
	}
	
	@Test
	public void testParseFromString() {
		cmd = new CommandAnalyzerAdd("add project r- 5 min 5am this mon #work #impt");
		String event = cmd.getCommandContent();
		
		assertEquals("test parse()", "project..#work #impt..false..00:05 01/01/1970..05:00 15/10/2012..invalid..", event);
		
		event = "01" + ".." + event;
		Event newEvent = EventStringHandler.getEventFromString(event);
		assertEquals("test parse()", "01..project..#work #impt..false.." +
				"00:05 01/01/1970..05:00 15/10/2012..invalid..", newEvent.toString());
		
		cmd = new CommandAnalyzerAdd("add project r- 5 min 5am to 7pm this mon #work #impt");
		
		event = "02.." + cmd.getCommandContent();
		assertEquals("test parse()", "02..project..#work #impt..false.." +
				"00:05 01/01/1970..05:00 15/10/2012..19:00 15/10/2012..", event);
		newEvent = EventStringHandler.getEventFromString(event);
		assertEquals("test parse()", "02..project..#work #impt..false.." +
				"00:05 01/01/1970..05:00 15/10/2012..19:00 15/10/2012..", newEvent.toString());
		
		cmd = new CommandAnalyzerAdd("add project #work #impt");
		event = "03.." + cmd.getCommandContent();
		assertEquals("test parse()", "03..project..#work #impt..false..invalid..invalid..invalid..", event);
		newEvent = EventStringHandler.getEventFromString(event);
		assertEquals("test parse()", "03..project..#work #impt..false.." +
				"invalid..invalid..invalid..", newEvent.toString());
	}
}*/

