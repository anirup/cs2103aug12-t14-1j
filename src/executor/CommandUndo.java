package executor;

import actionArchive.ListOfActionArchive;
import logAndException.Log;
import logAndException.MessageHandler;
import event.ListOfEvent;

public class CommandUndo extends Command {

	public CommandUndo(String userInputString, boolean searchState,
			int searchSize, int shownEventSize) {
		super(userInputString, searchState, searchSize, shownEventSize);
	}

	public void execute() throws Exception {

		int splitError = this.splitInput();
		if (splitError != -1) {
			returnVal = splitError;
			return;
		}					
		String undoMessage = ListOfActionArchive.undo();
		if(undoMessage=="") {		
			Log.toLog(2, MessageHandler.getMessage(21));
			returnVal = 21;
			return;
		}
		Log.toLog(0, MessageHandler.getMessage(6));
		ListOfEvent.notifyObservers();
		
		try {
			// Save to file before each operation
			ListOfEvent.syncDataToDatabase();
		} catch (Exception e) {
			ListOfEvent.formatListOfEvent();
			Log.toLog(2, MessageHandler.getMessage(10));
			returnVal = 10;
			return;
		}
		
		returnVal = 6;
		return;
	}

}
