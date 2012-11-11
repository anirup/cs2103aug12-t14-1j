package executor;

import java.util.ArrayList;

import logAndException.Log;
import logAndException.MessageHandler;
import logic.LogicAnalyzer;
import actionArchive.ActionArchiveUpdate;
import actionArchive.ListOfActionArchive;
import event.Event;
import event.ListOfEvent;

public class CommandUpdate extends Command {

	public CommandUpdate(String userInputString, boolean searchState,
			int searchSize, int shownEventSize) {
		super(userInputString, searchState, searchSize, shownEventSize);
	}

	public void execute() throws Exception {

		int splitError = this.splitInput();
		if (splitError != -1) {
			returnVal = splitError;
			return;
		}

		int updateIndex = -1;

		try {
			updateIndex = Integer
					.parseInt(parameterList[parameterList.length - 1]) - 1;
		} catch (Exception e) {
			Log.toLog(0, MessageHandler.getMessage(2));
			returnVal = 9;
			return;
		}

		ArrayList<Event> changedEvents;
		String eventToAdd = LogicAnalyzer
				.getAddUpdateEventString(parameterList);

		if (eventToAdd.equals("")) {
			Log.toLog(2, MessageHandler.getMessage(16));
			returnVal = 16;
			return;
		}

		if (searchState == false) {
			if (updateIndex < this.shownEventSize) {
				changedEvents = ListOfEvent.updateList(updateIndex, eventToAdd);
			} else {
				Log.toLog(2, MessageHandler.getMessage(19));
				returnVal = 19;
				return;
			}

		} else {
			if (updateIndex < searchSize) {
				changedEvents = ListOfEvent.updateSearch(updateIndex,
						eventToAdd);
			} else {
				Log.toLog(2, MessageHandler.getMessage(19));
				returnVal = 19;
				return;
			}
		}

		if (!changedEvents.isEmpty()) {
			ListOfActionArchive.add(new ActionArchiveUpdate(changedEvents
					.get(0), changedEvents.get(1)));
		}
		updateIndex = -1;

		try {
			// Save to file before each operation
			ListOfEvent.syncDataToDatabase();
		} catch (Exception e) {
			ListOfEvent.formatListOfEvent();
			Log.toLog(2, MessageHandler.getMessage(10));
			returnVal = 10;
			return;
		}

		Log.toLog(0, MessageHandler.getMessage(2));
		ListOfEvent.notifyObservers();
		returnVal = 2;
		return;
	}

}
