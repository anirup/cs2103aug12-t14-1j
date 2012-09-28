import java.util.*;
import java.io.*;
import org.joda.time.DateTime;

public class Logic {

	private static boolean searchState;
	private static ListOfEvent list;
	private static ListOfEvent searchResults;
	private static UserLog lastTasks;
	public Logic() {
		searchState = false;
		list = new ListOfEvent();
		searchResults = new ListOfEvent();
	}

	public static void analyze(String userInput) {
		String[] parameterList=userInput.split("..");
		String command=getCommand(parameterList);
		if(command.equalsIgnoreCase("add")){
			analyzeAddInput(parameterList);
			searchToFalse();
		}
		else if(command.equalsIgnoreCase("delete"))
		{
			if(searchState==true)
			{
				int index = getInteger(parameterList);
				list.remove(index);
				searchToFalse();
			}
			else if (searchState==false)
			{
				analyzeAndSearch(parameterList);
				searchToTrue();
			}
		}
		else if(command.equalsIgnoreCase("update"))
		{
			if(searchState==true)
			{
				int index= getInteger(parameterList);
				updateEvent(index);
				searchToFalse();
			}
			else if (searchState==false)
			{
				analyzeAndSearch(parameterList);
				searchToTrue();
			}
		}
		else if(command.equalsIgnoreCase("search"))
		{
			analyzeAndSearch(parameterList);
		}
		else if(command.equalsIgnoreCase("done"))
		{
			if(searchState==true)
			{
				int index= getInteger(parameterList);
				markDone(index);
				searchToFalse();
			}
			else if (searchState==false)
			{
				analyzeAndSearch(parameterList);
				searchToTrue();
			}
		}
		else if(command.equalsIgnoreCase("undone"))
		{
			if(searchState==true)
			{
				int index= getInteger(parameterList);
				markNotDone(index);
				searchToFalse();
			}
			else if (searchState==false)
			{
				analyzeAndSearch(parameterList);
				searchToTrue();
			}
		}
		else if(command.equalsIgnoreCase("undo"))
		{
			undoLast();
		}
		else if(command.equalsIgnoreCase("deletep"))
		{
			deleteHashDeleted();
		}

	}
	private static void searchToTrue()
	{
		searchState=true;
	}
	private static void searchToFalse()
	{
		searchState=false;
	}
	private static String getCommand(String[] parameterList)
	{
		return parameterList[0];
	}
	private static int getInteger(String[] parameterList)
	{
		try
		{
			return Integer.parseInt(parameterList[1]);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Invalid argument");
			return -1;
		}
	}
	private static void undoLast()
	{
		
	}
	private static void deleteHashDeleted(){
		
	}
	private static void analyzeAndSearch(String[] parameterList)
	{
		
	}
	private static void markNotDone(int index)
	{
		
	}
	private static void markDone(int index){
		
	}
	private static void analyzeAddInput(String[] parameterList)
	{
		String priority,keywords;
		int reminderTime;
		keywords=getKeyWords(parameterList);
		priority=getPriority(parameterList);
		reminderTime=getReminderTime(parameterList);
		
	}
	private static void updateEvent(int index)
	{
		
	}
	private static String getKeyWords(String[] parameterList)
	{
		return "";
	}
	private static String getPriority(String[] parameterList)
	{
		return "";
	}
	private static int getReminderTime(String[] parameterList)
	{
		return -1;
	}
	private static Vector<String> getHashTags(String[] parameterList)
	{
		Vector<String> listOfHashTags=new Vector<String>();
		for(int i=0;i<parameterList.length;i++)
		{
			if(parameterList[i].startsWith("#"))
			{
				String[] tempArray=parameterList[i].split("#");
				for(int j=0;j<tempArray.length;j++)
				{
					listOfHashTags.add(tempArray[i]);
				}
			}
		}
		return listOfHashTags;
	}
	private static DateTime getStartTime(String[] parameterList)
	{
		DateTime startTime=new DateTime();
		return startTime;
	}
}
