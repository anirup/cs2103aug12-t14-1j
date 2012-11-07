package actionArchive;

import event.Event;
import event.ListOfEvent;


public class ActionArchiveUpdate extends ActionArchive{
	private Event _eventAfterUpdated;
	private Event _eventBeforeUpdated; 
	
	private static final String MESSAGE_UNDO_UPDATE = "Undo: Update %1$s by %2$s";
	
	public ActionArchiveUpdate(Event eventBeforeUpdated, Event eventAfterUpdated) {
		_eventAfterUpdated = eventAfterUpdated;
		_eventBeforeUpdated = eventBeforeUpdated;
	}
	
	public Event getEventBeforeUpdated() {
		return _eventBeforeUpdated;
	}
	
	public Event getEventAfterUpdated() {
		return _eventAfterUpdated;
	}
	
	public String rollBack() {
		ListOfEvent.update(_eventAfterUpdated, _eventBeforeUpdated);
	
		String messageUndo = String.format(MESSAGE_UNDO_UPDATE, _eventBeforeUpdated.getEventName(), _eventAfterUpdated.getEventName());
		return messageUndo;
	}
}


