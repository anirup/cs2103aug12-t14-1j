package Project;

public class ActionArchiveAdd extends ActionArchive {
	private Event _addedEvent;
	private static final String MESSAGE_UNDO_ADD = "Undo: Add %1$s";
	
	public ActionArchiveAdd(Event addedEvent) {
		_addedEvent = addedEvent;
	}
	
	public Event getAddedEvent() {
		return _addedEvent;
	}

	public String rollBack() {
		ListOfEvent.remove(_addedEvent);
		String messageUndo = String.format(MESSAGE_UNDO_ADD, _addedEvent.getEventName());
		return messageUndo;
	}
}
