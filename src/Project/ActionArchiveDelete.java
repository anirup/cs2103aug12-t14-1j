package Project;


public class ActionArchiveDelete extends ActionArchive{
	private Event _deletedEvent;
	private static final String MESSAGE_UNDO_DELETE = "Undo: Delete %1$s";
	
	public ActionArchiveDelete(Event deletedEvent) {
		_deletedEvent = deletedEvent;
	}

	public Event getDeletedEvent() {
		return _deletedEvent;
	}
	
	public String rollBack() {
		ListOfEvent.add(_deletedEvent);
		String messageUndo = String.format(MESSAGE_UNDO_DELETE, _deletedEvent.getEventName());
		
		return messageUndo;
	}
}
