package executor;

import logAndException.Log;
import logAndException.MessageHandler;
import logic.LogicAnalyzer;
import actionArchive.ActionArchiveAdd;
import actionArchive.ListOfActionArchive;
import event.Event;
import event.ListOfEvent;

public class CommandAdd extends Command{
	
	public CommandAdd(String userInputString, boolean searchState, int searchSize, int shownEventSize){
		super(userInputString, searchState,  searchSize, shownEventSize);		
	}
	
	private static Event analyzeAddInput(String[] parameterList) {

		String eventToAdd = LogicAnalyzer
				.getAddUpdateEventString(parameterList);
		return ListOfEvent.add(eventToAdd);

	}
		
	public void execute() throws Exception {
		
		int splitError = this.splitInput();
		if (splitError!= -1) {
			returnVal = splitError;
			return;
		}
		
		Event newEvent = analyzeAddInput(parameterList);
		if (newEvent != null) {
			ListOfActionArchive.add(new ActionArchiveAdd(newEvent));
			try {
				// Saving to file after add
				ListOfEvent.syncDataToDatabase();
				Log.toLog(0, MessageHandler.getMessage(0));
				ListOfEvent.notifyObservers();
				returnVal = 0;
				return;
			} catch (Exception e) {				
				Log.toLog(2, MessageHandler.getMessage(10));
				returnVal = 10;
				return;
			}			
			
		} else {			
			Log.toLog(2, MessageHandler.getMessage(9));
			returnVal =  9;
			return;
		}
	}

}
