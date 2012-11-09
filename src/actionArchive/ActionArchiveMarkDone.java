package actionArchive;

import event.Event;
import event.ListOfEvent;
 
 
public class ActionArchiveMarkDone {
	private Event _eventMarkedDone;
	
	public ActionArchiveMarkDone(Event eventMarkedDone) {
		_eventMarkedDone = eventMarkedDone;
	}
	
	public void rollBack() {
		ListOfEvent.markUndoneList(_eventMarkedDone);
	}
}
