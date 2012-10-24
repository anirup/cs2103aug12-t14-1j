/*import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestListOfEvent {
	@Before
	public void setUp() throws Exception {
		ListOfEvent.setUpDataFromDatabase();
		PatternLib.setUpPattern();
	}
	
	@After
	public void cleanUp() throws IOException {
		ListOfEvent.syncDataToDatabase();
	}
	
	@Test
	public void test() {
		
		CommandAnalyzerAdd cmd = new CommandAnalyzerAdd("add project #high #impt");
		String newEvent = cmd.getCommandContent();
		newEvent = "01.." + newEvent;
		assertEquals("test getContent", "01..project..#high #impt..false..invalid..invalid..invalid..", newEvent);
		Event event = EventStringHandler.getEventFromString(newEvent);
		ListOfEvent.add(event);
		cmd = new CommandAnalyzerAdd("add assignment r- 5 min #high #impt 5am this mon");
		newEvent = cmd.getCommandContent();
		newEvent = "02.." + newEvent;
		assertEquals("test getContent", "02..assignment..#high #impt ..false.." +
				"00:05 01/01/1970..05:00 15/10/2012..invalid..", newEvent);
		event = EventStringHandler.getEventFromString(newEvent);
		ListOfEvent.add(event);
		cmd = new CommandAnalyzerAdd("add tutorial r- 5 min #high #impt 5pm to 7pm this mon");
		newEvent = cmd.getCommandContent();
		newEvent = "03.." + newEvent;
		event = EventStringHandler.getEventFromString(newEvent);
		ListOfEvent.add(event);
		ListOfEvent.sortByTime();
		assertEquals("test size", 3, ListOfEvent.size());
	}

}*/