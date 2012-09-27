
public class UpdateLog extends UserLog{
	private Event _eventAfterUpdated;
	private Event _eventBeforeUpdated;
	
	public UpdateLog(Event eventBeforeUpdated, Event eventAfterUpdated) {
		_logType = UserLog.LOG_TYPE.UPDATE;
		_eventAfterUpdated = eventAfterUpdated;
		_eventBeforeUpdated = eventBeforeUpdated;
	}
	
	public Event getEventBeforeUpdated() {
		return _eventBeforeUpdated;
	}
	
	public Event getEventAfterUpdated() {
		return _eventAfterUpdated;
	}
	
	public void rollBack() {
		ListOfEvent.update(_eventAfterUpdated, _eventBeforeUpdated);
		return;
	}
}


