import java.util.Vector;

public class ExceptionHandler {
	private static Vector<String> listOfExceptions;
	public static void setUpList()
	{
		listOfExceptions.add("");
	}
	public static String getException(int index)
	{
		return listOfExceptions.get(index);
	}


}
