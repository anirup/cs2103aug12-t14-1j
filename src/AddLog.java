
public class AddLog extends UserLog {
	private Event _addedEvent;
	
	public AddLog(Event addedEvent) {
		_logType = UserLog.LOG_TYPE.ADD;
		_addedEvent = addedEvent;
	}
	
	public Event getAddedEvent() {
		return _addedEvent;
	}

	public void rollBack() {
		ListOfEvent.remove(_addedEvent);
	}
}
