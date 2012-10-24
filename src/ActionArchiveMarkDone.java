
public class ActionArchiveMarkDone {
	private Event _eventMarkedDone;
	
	public ActionArchiveMarkDone(Event eventMarkedDone) {
		_eventMarkedDone = eventMarkedDone;
	}
	
	public void rollBack() {
		int position = ListOfEvent.indexOf(_eventMarkedDone);
		ListOfEvent.markUndone(position);
	}
}
