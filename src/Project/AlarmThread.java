package Project;

class AlarmThread implements Runnable {
	Thread t;

	AlarmThread() {
		// Create a new, second thread
		t = new Thread(this, "Alarm Thread");
		t.start(); // Start the thread
	}

	// This is the entry point for the second thread.
	public void run() {
		while (true) {
			ListOfAlarm.runAlarm();
		}

	}
}
