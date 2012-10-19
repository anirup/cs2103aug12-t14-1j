import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Clock {
	private String _time;
	private String _dateFormat;
	
	public Clock() {
		_time = "1970-01-01T00:00+08:00";
		_dateFormat = "yyyy-MM-dd'T'hh:mmZ";
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
		if(_time.equalsIgnoreCase("1970-01-01T00:00+08:00")) {
			return "";
		}
		
		StringBuilder date = new StringBuilder();
		
		date.append(_time + "..");
		date.append(_dateFormat + "..");
		
		return date.toString();
	}
	
	public boolean isInDay(DateTime day) {
		DateTime thisDate = this.toDate();
		if((thisDate.getDayOfYear() == day.getDayOfYear()) && (thisDate.getYearOfCentury() == thisDate.getYearOfCentury())) {
			return true;
		}
		return false;
	}
	
	public boolean isBefore(DateTime time) {
		long timeLong = time.getMillis();
		return this.toDate().isBefore(timeLong);
	}
	
	public DateTime toDate() {
		DateTimeFormatter pattern = DateTimeFormat.forPattern(_dateFormat);
		
		DateTime date = pattern.parseDateTime(_time);
		
		return date;
	}

	public boolean isBefore(Clock anotherTime) {
		DateTime thisTimeDate = this.toDate();
		DateTime anotherTimeDate = anotherTime.toDate();
		long anotherLong  = anotherTimeDate.getMillis();
		return thisTimeDate.isBefore(anotherLong);
	}
}