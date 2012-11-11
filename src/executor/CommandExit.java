package executor;

import event.ListOfEvent;
import logAndException.Log;
import logAndException.MessageHandler;

public class CommandExit extends Command {
	
	public CommandExit(String userInputString, boolean searchState,
			int searchSize, int shownEventSize) {
		super(userInputString, searchState, searchSize, shownEventSize);
	}
	
	public void execute() throws Exception {
		
		try {
			// Save to file before each operation
			ListOfEvent.syncDataToDatabase();
		} catch (Exception e) {
			ListOfEvent.formatListOfEvent();
			Log.toLog(2, MessageHandler.getMessage(10));
			returnVal = 10;
			return;
		}
		Log.toLog(0, MessageHandler.getMessage(7));
		System.exit(0);
	}

}
