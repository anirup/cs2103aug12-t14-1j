package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;


public class ListOfAlarm {

	private static CopyOnWriteArrayList<AlarmType> alarmList = new CopyOnWriteArrayList<AlarmType>();
	private static AlarmSound sound = new AlarmSound();
	
	public static void setListOfAlarm(ArrayList<AlarmType> newList) {
		Collections.copy(alarmList, newList);		
	}
	
	public static void add(AlarmType alarm){
		alarmList.add(alarm);
	}
	
	public static void runAlarm() {
		if(!alarmList.isEmpty())
			for(int i = 0 ; i < alarmList.size(); i++) {
				if(alarmList.get(i).isAlarmTime())
					sound.play();
			}
	}
	
}
