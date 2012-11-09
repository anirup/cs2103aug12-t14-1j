package logAndException;
import global.Clock;

import java.io.File;
import java.util.logging.*;

import org.joda.time.DateTime;
 
public class Log {
	private static final Logger messageLogger=Logger.getLogger("Log");
	private static final Logger errorLogger=Logger.getLogger("Log");
	private static FileHandler fh=null;
	private static FileHandler fh2=null;
	public static void setup() throws Exception
	{	File dir = new File("Logging");  
		dir.mkdir();
		String fileName="Logging/MessageLog -" + Clock.toString(DateTime.now()).replace(":", "").replace("/", "") + ".txt";
		fh = new FileHandler(fileName, true);
		String fileName2="Logging/ErrorLog -" + Clock.toString(DateTime.now()).replace(":", "").replace("/", "") + ".txt";
		fh2 = new FileHandler(fileName2, true);	
		
 	}
	public static void toLog(int Level,String message) throws Exception
	{
		if(fh!=null)
		{
		fh.setFormatter(new SimpleFormatter());
		messageLogger.addHandler(fh);
		messageLogger.setLevel(java.util.logging.Level.ALL);
		errorLogger.addHandler(fh2);
		messageLogger.setLevel(java.util.logging.Level.SEVERE);
		if(Level==0)
		messageLogger.log(java.util.logging.Level.INFO, message);
		else if(Level==1)
			messageLogger.log(java.util.logging.Level.WARNING, message);
		else
			errorLogger.log(java.util.logging.Level.SEVERE, message);
		}
	}
	
}
