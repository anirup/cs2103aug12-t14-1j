package actionArchive;

import event.Event;
import event.ListOfEvent;

 
public class ActionArchiveMarkDone extends ActionArchive {
	private Event _eventMarkedDone;
	
	public ActionArchiveMarkDone(Event eventMarkedDone) {
		_eventMarkedDone = eventMarkedDone;
	}
	
	public String rollBack() {
		ListOfEvent.markUndoneList(_eventMarkedDone);
		return null;
	}
}
