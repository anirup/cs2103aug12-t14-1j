import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.Duration;
import org.joda.time.LocalTime;
import java.util.Vector;

public class Logic {

	private static final String SPLIT_HASH = "#";
	private static final String Priority_Normal = "NORMAL";
	private static final String Priority_Low = "LOW";
	private static final String Priority_High = "HIGH";
	private static boolean fieldFound[]={false,false,false,false,false,false};
	
	public static void setUp()
	{
		for(int i=0;i<6;i++)
		{
			fieldFound[i]=false;
		}
	}
	public static String getKeyWords(String[] parameterList) {
		fieldFound[1] = true;
		if(parameterList[1].trim().contains("#"))
		{
		return parameterList[1].trim().substring(0,
				parameterList[1].indexOf(SPLIT_HASH)).trim();
		}
		else 
		{
			return parameterList[1].trim();
		}
	}

	public static String getPriority(String[] parameterList) {
		for (int i = 0; i < parameterList.length; i++) {
			if (parameterList[i].trim().equalsIgnoreCase(Priority_High)
					|| parameterList[i].trim().equalsIgnoreCase("h")) {
				fieldFound[i] = true;
				return Priority_High;
			} else if (parameterList[i].trim().equalsIgnoreCase(Priority_Low)
					|| parameterList[i].trim().equalsIgnoreCase("l")) {
				fieldFound[i] = true;
				return Priority_Low;
			}
		}
		fieldFound[5] = true;
		return Priority_Normal;
	}

	public static Duration getReminderTime(String[] parameterList) {
		long miliseconds = 0;
		int indexOfReminder = -1;
		Vector<Long> timeQuantity = new Vector<Long>();
		Vector<String> timeParameter = new Vector<String>();
		for (int i = 0; i < parameterList.length; i++) {
			if (parameterList[i].trim().startsWith("r-")
					|| parameterList[i].trim().startsWith("R-")) {
				fieldFound[i] = true;
				indexOfReminder = i;
				Pattern p = Pattern.compile("\\d+");
				Matcher matches = p.matcher(parameterList[i].trim());
				while (matches.find()) {
					timeQuantity.add(Long.parseLong(matches.group()));
				}
				break;
			} 
		}
		if (indexOfReminder != -1) {
			for (int j = 0; j < timeQuantity.size() -1 ; j++) {
				String firstLimitString = "" + timeQuantity.get(j);
				String secondLimitString = "" + timeQuantity.get(j + 1);
				int lastIndex = parameterList[indexOfReminder]
						.indexOf(secondLimitString);
				int firstIndex = firstLimitString.length()
						+ parameterList[indexOfReminder]
								.indexOf(firstLimitString);
				String parameter = parameterList[indexOfReminder].substring(
						firstIndex, lastIndex);
				timeParameter.addElement(parameter.toLowerCase());
			}
			String firstLimitString = "" + timeQuantity.get(timeQuantity.size()-1);
			int lastIndex = parameterList[indexOfReminder].length()-1;
			int firstIndex = firstLimitString.length()
					+ parameterList[indexOfReminder]
							.indexOf(firstLimitString);
			String parameter = parameterList[indexOfReminder].substring(
					firstIndex, lastIndex+1);
			timeParameter.addElement(parameter.toLowerCase());
			for (int k = 0; k < timeParameter.size(); k++) {
				if (timeParameter.get(k).trim().startsWith("d")
						|| timeParameter.get(k).trim().startsWith("day")
						|| timeParameter.get(k).trim().startsWith("days")) {
					miliseconds += timeQuantity.get(k) * 86400000;
				} else if (timeParameter.get(k).trim().startsWith("h")
						|| timeParameter.get(k).trim().startsWith("hr")
						|| timeParameter.get(k).trim().startsWith("hour")) {
					miliseconds += timeQuantity.get(k) * 3600000;
				} else if (timeParameter.get(k).trim().startsWith("m")
						|| timeParameter.get(k).trim().startsWith("min")
						|| timeParameter.get(k).trim().startsWith("minute")) {
					miliseconds += timeQuantity.get(k) * 60000;
				} else if (timeParameter.get(k).trim().startsWith("s")
						|| timeParameter.get(k).trim().startsWith("sec")
						|| timeParameter.get(k).trim().startsWith("second")) {
					miliseconds += timeQuantity.get(k) * 1000;
				}
			}
		}else {
			fieldFound[4] = true;
		}

		return (new Duration(miliseconds));
	}

	public static Vector<String> getHashTags(String[] parameterList) {
		Vector<String> listOfHashTags = new Vector<String>();
		for (int i = 0; i < parameterList.length; i++) {
			int startHashCode = parameterList[i].indexOf(SPLIT_HASH);
			if (startHashCode > -1) {
				String[] hashCodes = parameterList[i].trim().substring(startHashCode+1).trim()
						.split(SPLIT_HASH);
				for (int j = 0; j < hashCodes.length; j++) {
					listOfHashTags.add(hashCodes[j].trim());
				}
				break;
			}

		}
		return listOfHashTags;
	}

	public static String getCommand(String[] parameterList) {
		fieldFound[0] = true;
		return parameterList[0];
	}

	public static int getInteger(String[] parameterList) {
		try {
			return Integer.parseInt(parameterList[1]);
		} catch (NumberFormatException e) {
			System.out.println("Invalid argument");
			return -1;
		}
	}

	public static String getStartTime(String[] parameterList) {
		String currentTime = "";
		for (int i = 0; i < 6; i++) {
			if (fieldFound[i] == false && !parameterList[i].equals("-1")) {
				currentTime="";
				String[] dateAndTime = parameterList[i].trim().split(" ");
				if (dateAndTime[0].contains(":")) {
					currentTime += dateAndTime[1] + "T" + dateAndTime[0].trim()
							+ "+08:00";
					fieldFound[i]=true;
					break;
				} else if (dateAndTime[1].contains(":")) {
					currentTime += dateAndTime[0] + "T" + dateAndTime[1].trim()
							+ "+08:00";
					fieldFound[i]=true;
					break;
				}
				
			}
		}

		return currentTime;
	}

	public static String getEndTime(String[] parameterList) {
		String endTime = "";
		for (int i = 0; i < 6; i++) {
			if (fieldFound[i] == false && !parameterList[i].equals("-1")) {
				endTime="";
				String[] dateAndTime = parameterList[i].trim().split(" ");
				if (dateAndTime[0].contains(":")) {
					endTime += dateAndTime[1] + "T" + dateAndTime[0].trim()
							+ "+08:00";
					fieldFound[i]=true;
					break;
				} else if (dateAndTime[1].contains(":")) {
					endTime += dateAndTime[0] + "T" + dateAndTime[1].trim()
							+ "+08:00";
					fieldFound[i]=true;
					break;
				}
				
			}
		}

		return endTime;
	}

	public static String getEventID() {
		Vector<Integer> idDigits= new Vector<Integer>();
		String eventId=LocalTime.now().toString();
		Pattern p = Pattern.compile("\\d+");
		Matcher matches = p.matcher(eventId.trim());
		while (matches.find()) {
			idDigits.add((int) Long.parseLong(matches.group()));
		}
		eventId="";
		for(int i=0;i<idDigits.size();i++){
			eventId+=idDigits.get(i);
		}
		return eventId;
		
		
	}
}
