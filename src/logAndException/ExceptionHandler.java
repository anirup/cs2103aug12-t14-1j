package logAndException;
 
import java.util.Vector;

public class ExceptionHandler {
	private static Vector<String> listOfExceptions;
	public static void setUpList()
	{	
		listOfExceptions=new Vector<String>();
		listOfExceptions.add("Event Added Successfully.");//0
		listOfExceptions.add("Event Deleted Successfully.");//1
		listOfExceptions.add("Event updated Successfully.");//2
		listOfExceptions.add("Event marked as COMPLETE Successfully.");//3
		listOfExceptions.add("Event marked as INCOMPLETE Successfully.");//4
		listOfExceptions.add("Error: Command Not Found.");//5
		listOfExceptions.add("Undo Successful.");//6
		listOfExceptions.add("Saving Your Data and Exiting.");//7
		listOfExceptions.add("Clash Exists with another Event - Add anyway ?");//8
		listOfExceptions.add("Error: Could not perform Action - Please Check the Command entered.");//9
		listOfExceptions.add("Error: Database corrupted - Formating Now.");//10
		listOfExceptions.add("Command performed Successfully.");//11
		listOfExceptions.add("Error: Illegal Command Entered.");//12
		listOfExceptions.add("Error: Illegal Content Entered.Please Check Your START/END/REMINDER Field Formats.");//13
		listOfExceptions.add("Error: Illegal Content Entered.Please Check Your Keywords.");//14
		listOfExceptions.add("Switched to Original View");//15
		listOfExceptions.add("Error: No Keywords Entered");//16
		listOfExceptions.add("Switched to Upcoming Events View");//17
		listOfExceptions.add("Error: Update Format Wrong.");//18
		listOfExceptions.add("Error: No Event at Entered Index.");//19
		listOfExceptions.add("Error: Illegal Keyword Entered.Please Enter a Valid Integer");//20
	}
	
	public static String getException(int index)
	{
		return listOfExceptions.get(index);
	}
}
