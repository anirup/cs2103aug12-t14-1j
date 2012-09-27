import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Clock {
	private String _time;
	private String _dateFormat;
	
	public Clock() {
		_time = null;
		_dateFormat = null;
	}
	
	public Clock(String time, String dateFormat) {
		_time = time;
		_dateFormat = dateFormat;
	}
	
	public String getTime() {
		return _time;
	}
	
	public String getDateFormat() {
		return _dateFormat;
	}
	
	public void updateTime(String time) {
		_time = time;
		return;
	}
	
	public void updateDateFormat(String dateFormat) {
		_dateFormat = dateFormat;
		return;
	}
	
	public String toString() {
		StringBuilder date = new StringBuilder();
		
		date.append(_time + "\n");
		date.append(_dateFormat + "\n");
		
		return date.toString();
	}
	
	public Date toDate() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(_dateFormat);
		
		Date date = null;
		
		date = dateFormat.parse(_time);
		
		return date;
	}
}
