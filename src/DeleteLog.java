
public class DeleteLog extends UserLog{
	private Event _deletedEvent;

	public DeleteLog(Event deletedEvent) {
		_logType = UserLog.LOG_TYPE.ADD;
		_deletedEvent = deletedEvent;
	}

	public Event getDeletedEvent() {
		return _deletedEvent;
	}
	
	public void rollBack() {
		ListOfEvent.add(_deletedEvent);
	}
}
