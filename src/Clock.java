import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
	
	public String toString() {
		StringBuilder date = new StringBuilder();
		
		date.append(_time + "..");
		date.append(_dateFormat + "..");
		
		return date.toString();
	}
	
	public int compareTo(Clock anotherTime) {
		DateTime thisTimeDate = this.toDate();
		DateTime anotherTimeDate = anotherTime.toDate();
		
		return thisTimeDate.compareTo(anotherTimeDate);
	}
	
	public DateTime toDate() {
		DateTimeFormatter pattern = DateTimeFormat.forPattern(_dateFormat);
		
		DateTime date = pattern.parseDateTime(_time);
		
		return date;
	}
}
