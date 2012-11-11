package logic;
 
import global.StringOperation;

import java.util.Vector;

public class LogicSplitterUpdate extends LogicSplitter{

	protected static Vector<String> splitInputUpdate(String userInput, Vector<String> parameterList) throws Exception {
		int[] reminderFound = { 0 };
		int[] timeCount = { 0 };
		int index=-1;
		Vector<Integer> timeIndexes = new Vector<Integer>();
		Exception exception = new Exception();
		index=StringOperation.getFirstNumber(userInput.trim());
		if(index==-1)
		{
			message=18;
			throw exception;
		}
		else
		{
			String formattedIndex=String.format("%d",index);
			userInput=userInput.substring(userInput.indexOf(formattedIndex)+formattedIndex.length());
		}
		try {
			userInput = extractTimeFieldsAndUpdateInputString(
					reminderFound, userInput, parameterList, timeIndexes,
					timeCount);
		} catch (Exception e) {
			message = 13;
			throw exception;
		}
		try { 
			extractKeywordsAlongWithHashTags(userInput, parameterList);
		} catch (Exception e) {
			message = 14;
			throw exception;
		}
		try {
			shiftKeywordsToSecondIndex(parameterList);
		} catch (Exception e) {
			message = 16;
			throw exception;
		}
		try {
			processEndStartTime(userInput, parameterList, timeIndexes);
		} catch (Exception e) {
			message = 13;
			throw exception;
		}
		for(int i=parameterList.size();i<5;i++)
		{
			parameterList.add("-1");
		}
		parameterList.add(String.format("%d",index));
		return parameterList;
	}
}
