package Project;

import org.joda.time.DateTime;


	public class EventForSort implements Comparable<Object> {

		int _index;
		Event _event;
		int _priority;
		DateTime _dateTime;

		public EventForSort(int index, Event event) {
			_index = index;
			_event = event;
			_dateTime = event.getEventEndTime();
			// @Need to change
			String priority = event.getEventHashTag().split("#")[0].trim();
			if (priority.equalsIgnoreCase("High"))
				_priority = 0;
			else if (priority.equalsIgnoreCase("Normal"))
				_priority = 1;
			else if (priority.equalsIgnoreCase("Low"))
				_priority = 2;
		}

		public int compareTo(Object o) {
			if (this.priority() != ((EventForSort) o).priority())
				return this.priority() - ((EventForSort) o).priority();
			else if (this._dateTime.isBefore(((EventForSort) o)._dateTime))
				return -1;
			else if (this._dateTime.isAfter(((EventForSort) o)._dateTime))
				return 1;
			else
				return 0;

		}

		Integer index() {
			return _index;
		}

		Integer priority() {
			return _priority;
		}

		Event event() {
			return _event;
		}

	}

