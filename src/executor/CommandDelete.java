package executor;

import logAndException.Log;
import logAndException.MessageHandler;
import actionArchive.ActionArchiveDelete;
import actionArchive.ListOfActionArchive;
import event.ListOfEvent;

public class CommandDelete extends Command {

	public CommandDelete(String userInputString, boolean searchState,
			int searchSize, int shownEventSize) {
		super(userInputString, searchState, searchSize, shownEventSize);
	}

	public void execute() throws Exception {

		int splitError = this.splitInput();
		if (splitError != -1) {
			returnVal = splitError;
			return;
		}
		
		assert (userInputInteger>-3);
		if (isValidIndex()) {
			if (searchState == true) {
				if (userInputInteger < this.searchSize) {
					ListOfActionArchive.add(new ActionArchiveDelete(ListOfEvent
							.removeSearch(userInputInteger)));
				} else {
					Log.toLog(2, MessageHandler.getMessage(19));
					returnVal = 19;
					return;
				}
			} else {
				if (userInputInteger < this.shownEventSize) {
					ListOfActionArchive.add(new ActionArchiveDelete(ListOfEvent
							.removeList(userInputInteger)));
				} else {
					Log.toLog(2, MessageHandler.getMessage(19));
					returnVal = 19;
					return;
				}
			}
		} else {
			Log.toLog(2, MessageHandler.getMessage(20));
			returnVal = 20;
			return;
		}
		
		try {
			// Save to file before each operation
			ListOfEvent.syncDataToDatabase();
		} catch (Exception e) {
			ListOfEvent.formatListOfEvent();
			Log.toLog(2, MessageHandler.getMessage(10));
			returnVal = 10;
			return;
		}
		
		Log.toLog(0, MessageHandler.getMessage(1));
		ListOfEvent.notifyObservers();
		returnVal = 1;
		return;
	}

}
