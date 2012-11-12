package executor;

import actionArchive.ListOfActionArchive;
import event.ListOfEvent;

public class CommandUndo extends Command {

	public CommandUndo(String userInputString, boolean searchState,
			int searchSize, int shownEventSize) {
		super(userInputString, searchState, searchSize, shownEventSize);
	}

	public void execute() throws Exception {

		int splitError = this.splitInput();
		if (splitError != -1) {
			setLogAndMessage(LOG_ERROR, splitError);			
			return;
		}					
		String undoMessage = ListOfActionArchive.undo();
		if(undoMessage=="") {		
			setLogAndMessage(LOG_ERROR, ERROR_UNDO);
			return;
		}
		
		ListOfEvent.notifyObservers();
		
		try {
			// Save to file before each operation
			ListOfEvent.syncDataToDatabase();
		} catch (Exception e) {
			ListOfEvent.formatListOfEvent();
			setLogAndMessage(LOG_ERROR, ERROR_DATABASE);
			return;
		}
		setLogAndMessage(LOG_MESSAGE, SUCCESSFUL_UNDO);
		return;
	}

}
