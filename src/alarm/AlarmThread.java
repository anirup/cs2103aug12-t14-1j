package alarm;
  
public class AlarmThread implements Runnable {
	Thread t;
	private static AlarmThread _instance = new AlarmThread();

	private AlarmThread() {
	}
	
	public static AlarmThread getInstance() {
		return _instance;
	}

	// This is the entry point for the second thread.
	public void run() {
		while (true) {
			try
			{
			ListOfAlarm.runAlarm();
			}
			catch(Exception e)
			{
				
			}
		}

	}
}
