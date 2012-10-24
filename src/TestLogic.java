import static org.junit.Assert.*;
import java.util.Vector;
import org.junit.Test;

public class TestLogic {

	String command="add     abcd#high 5am today 6am today r-1min22h";
	String[] parameterList= {"add","meeting#abcd#abcde","h","r-1hr2min","-1","-1"};


	@Test
	public void testSplit() {
		PatternLib.setUpPattern();

		Vector<String> ab=Logic.splitInput(command);
		System.out.println(ab.toString());
	}

	@Test
	public void testGetTime() {
		assertEquals("test getcommand", "add", Logic.getCommand(parameterList));

	}

	@Test
	public void testGet1() {
		assertEquals("test getkeyword", "meeting",
				Logic.getKeyWords(parameterList));
	}

	@Test
	public void testGet2() {
		assertEquals("test gethashtags", "abcd",
				Logic.getHashTags(parameterList).get(0));

	}

	@Test
	public void testGet4() {
		assertEquals("test gethashtags", "abcde",
				Logic.getHashTags(parameterList).get(1));
	}

/*	@Test
	public void testGet3() {
		assertEquals("test getpriority", "HIGH",
				Logic.getPriority(parameterList));
	}

	@Test
	public void testGet5() {
		Logic.getKeyWords(parameterList);
		Logic.getPriority(parameterList);
		Logic.getReminderTime(parameterList);
		Logic.getCommand(parameterList);
		assertEquals("test getstarttime", null,
				Logic.getEndTime(parameterList));
	}

	@Test
	public void testGet6() {
		Duration a = new Duration(3720000);
		assertEquals("test getreminder", a,
				Logic.getReminderTime(parameterList));
	}
*/
}
