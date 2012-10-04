import java.util.LinkedList;


public class ListOfUserLog {
	private static LinkedList<UserLog> listOfUserLog = new LinkedList<UserLog>();
	
	public static void add(UserLog userLog) {
		listOfUserLog.add(userLog);
		return;
	}
	
	public static UserLog getLast() {
		return listOfUserLog.pollLast();
	}

	public static String undo() {
		UserLog lastUserLog = getLast();
		return lastUserLog.rollBack();
	}	
}
