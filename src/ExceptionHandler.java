import java.util.Vector;

public class ExceptionHandler {
	private static Vector<String> listOfExceptions;
	public static void setUpList()
	{	
		listOfExceptions=new Vector<String>();
		listOfExceptions.add("Event Added Successfully");
		listOfExceptions.add("Event Deleted Successfully");
		listOfExceptions.add("Event updated Successfully");
		listOfExceptions.add("Event marked as COMPLETE Successfully");
		listOfExceptions.add("Event marked as INCOMPLETE Successfully");
		listOfExceptions.add("Command Not Found");
		listOfExceptions.add("Undo Successful");
		listOfExceptions.add("Saving Your Data and Exiting");
		listOfExceptions.add("Clash Exists with another Event - Add anyway ?");
		listOfExceptions.add("Could not perform Action - Please Check the Command entered");
		listOfExceptions.add("Database corrupted - Formating Now");
		listOfExceptions.add("Command performed Successfully");
		
	}
	public static String getException(int index)
	{
		return listOfExceptions.get(index);
	}
}
