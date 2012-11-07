package logAndException;
import global.Clock;

import java.util.logging.*;

import org.joda.time.DateTime;

public class Log {
	private static final Logger messageLogger=Logger.getLogger("Log");
	private static FileHandler fh=null;
	public static void setup() throws Exception
	{
		String fileName="MessageLog -" + Clock.toString(DateTime.now()).replace(":", "").replace("/", "");
		fh = new FileHandler(fileName, true);	
	}
	public static void toLog(int Level,String message) throws Exception
	{
		if(fh!=null)
		{
		fh.setFormatter(new SimpleFormatter());
		messageLogger.addHandler(fh);
		messageLogger.setLevel(java.util.logging.Level.ALL);
		if(Level==0)
		messageLogger.log(java.util.logging.Level.INFO, message);
		else if(Level==1)
			messageLogger.log(java.util.logging.Level.SEVERE, message);
		else
			messageLogger.log(java.util.logging.Level.WARNING, message);
		}
	}
	
}
