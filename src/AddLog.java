public class AddLog extends UserLog {
	private Event _addedEvent;
	private static final String MESSAGE_UNDO_ADD = "Undo: Add %1$s";
	
	public AddLog(Event addedEvent) {
		_logType = UserLog.LOG_TYPE.ADD;
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
