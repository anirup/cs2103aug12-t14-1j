package alarm;
 
public class AlarmThread implements Runnable {
	Thread t;

	public AlarmThread() {
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
