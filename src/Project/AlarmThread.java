package Project;

class AlarmThread implements Runnable {
	Thread t;

	AlarmThread() {
	}

	// This is the entry point for the second thread.
	public void run() {
		while (true) {
			ListOfAlarm.runAlarm();
		}

	}
}
