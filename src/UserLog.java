
public class UserLog {
	enum LOG_TYPE {
		ADD, UPDATE, DELETE;
	}
	
	protected LOG_TYPE _logType;

	public UserLog() {
		_logType = null;
	}
	
	public LOG_TYPE getLogType() {
		return _logType;
	}
	
	public void rollBack() {
		return;
	}
}
