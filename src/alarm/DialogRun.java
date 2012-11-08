package alarm;
class DialogRun  implements Runnable {

	private String event;
	private Dialogs reminder;
	
	DialogRun(String ev) {
		event = ev;
		reminder = new Dialogs(null, event);
	}

	private static void run(Dialogs applet) {
		
		applet.setAlwaysOnTop(true);
		applet.setLocationRelativeTo(null);
		applet.startAlarm();
		applet.setVisible(true);
		
	}	
	
	@Override
	public void run() {
		run(reminder);		
	}
}