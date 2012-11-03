package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;


public class ListOfAlarm implements ListOfEventObserver {

	private static CopyOnWriteArrayList<AlarmType> alarmList = new CopyOnWriteArrayList<AlarmType>();
	private static AlarmSound sound = new AlarmSound();
	private static ListOfAlarm _instance = new ListOfAlarm();
	
	private ListOfAlarm() {
		
	}
	
	public static ListOfAlarm getInstance() {
		return _instance;
	}
	public static void setListOfAlarm(ArrayList<AlarmType> newList) {
		//Collections.copy(alarmList, newList);
		alarmList.clear();
		for(int i=0;i<newList.size();i++)
		{
			alarmList.add(newList.get(i));
		}
	}
	
	//public static void add(AlarmType alarm){
		//alarmList.add(alarm);
	//}
	
	public void updateListOfEvent() {
		setListOfAlarm(ListOfEvent.setUpListOfReminder());
	}
	
	public static void runAlarm() {
		if(!alarmList.isEmpty())
			for(int i = 0 ; i < alarmList.size(); i++) {
				if(alarmList.get(i).isAlarmTime())
					sound.play();
			}
	}
	
}
