import java.util.LinkedList;


public class ListOfArchive {
	private static LinkedList<ActionArchive> listOfUserLog = new LinkedList<ActionArchive>();
	
	public static void add(ActionArchive userLog) {
		listOfUserLog.add(userLog);
		return;
	}
	
	public static ActionArchive getLast() {
		return listOfUserLog.pollLast();
	}

	public static String undo() {
		ActionArchive lastUserLog = getLast();
		return lastUserLog.rollBack();
	}	
}
