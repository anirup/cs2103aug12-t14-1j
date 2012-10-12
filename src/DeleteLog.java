
public class DeleteLog extends UserLog{
	private Event _deletedEvent;
	private static final String MESSAGE_UNDO_DELETE = "Undo: Delete %1$s";
	
	public DeleteLog(Event deletedEvent) {
		_logType = UserLog.LOG_TYPE.ADD;
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
