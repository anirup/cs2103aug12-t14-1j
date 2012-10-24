import java.util.LinkedList;

public class ListOfActionArchive {
	private static LinkedList<ActionArchive> listOfUserLog = new LinkedList<ActionArchive>();
	
	public static void add(ActionArchive userLog) {
		listOfUserLog.add(userLog);
		return;
	}
	
	public static ActionArchive getLast() {
		return listOfUserLog.getLast();
	}

	public static String undo() {
		ActionArchive lastActionArchive = listOfUserLog.pollLast();
		return lastActionArchive.rollBack();
	}	
}
