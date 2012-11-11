package actionArchive;

import event.Event;
import event.ListOfEvent;

 
public class ActionArchiveMarkUndone extends ActionArchive {
	private Event _eventMarkedDone;
	
	public ActionArchiveMarkUndone(Event eventMarkedDone) {
		_eventMarkedDone = eventMarkedDone;
	}
	
	public String rollBack() {
		ListOfEvent.markDoneList(_eventMarkedDone);
		return null;
	}
}