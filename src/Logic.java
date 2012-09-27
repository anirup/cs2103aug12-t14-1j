import java.util.*;
import java.io.*;
import org.joda.*;

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
		
	}
	private static void updateEvent(int index)
	{
		
	}
}
