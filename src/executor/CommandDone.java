package executor;

import logAndException.Log;
import logAndException.MessageHandler;
import actionArchive.ActionArchiveMarkDone;
import actionArchive.ListOfActionArchive;
import event.Event;
import event.ListOfEvent;

public class CommandDone extends Command {

	public CommandDone(String userInputString, boolean searchState,
			int searchSize, int shownEventSize) {
		super(userInputString, searchState, searchSize, shownEventSize);
	}

	public void execute() throws Exception {

		int splitError = this.splitInput();
		if (splitError != -1) {
			returnVal = splitError;
			return;
		}
		
		if (isValidIndex()) {
			if (searchState == true) {
				if (this.userInputInteger < this.searchSize) {
					Event doneEvent = ListOfEvent
							.markDoneSearch(userInputInteger);
					ListOfActionArchive
							.add(new ActionArchiveMarkDone(doneEvent));
				} else {
					Log.toLog(2, MessageHandler.getMessage(19));
					returnVal = 19;
					return;
				}
			} else if (searchState == false) {
				if (this.userInputInteger < this.shownEventSize) {
					Event doneEvent = ListOfEvent
							.markDoneList(this.userInputInteger);
					ListOfActionArchive
							.add(new ActionArchiveMarkDone(doneEvent));
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
		
		Log.toLog(0, MessageHandler.getMessage(3));
		ListOfEvent.notifyObservers();
		returnVal = 3;
		return;
	}
}
