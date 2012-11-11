package executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import event.ListOfEvent;

import logAndException.Log;
import logAndException.MessageHandler;
import logic.LogicAnalyzer;
import logic.LogicSplitter;
import logic.PatternLib;


public class Command {

	protected String userInputString;
	protected int userInputInteger;
	protected boolean searchState;
	protected int searchSize;
	protected int shownEventSize;
	protected String[] parameterList;
	protected ArrayList<String>feedback;
	protected int returnVal;
	
	public Command(String userInputString, boolean searchState, int searchSize, int shownEventSize) {
		
		this.userInputString = userInputString;
		this.searchSize = searchSize;
		this.searchState = searchState;
		this.shownEventSize = shownEventSize;
		this.userInputInteger = -1;
		feedback = new ArrayList<String>();
		init();
		ListOfEvent.sortList();
		this.parameterList = new String[6];
		Arrays.fill(this.parameterList,0,6,"-1");
				
	}

	protected int splitInput() throws Exception {
		Vector<String> parameters = new Vector<String>();		
		try {
			parameters = LogicSplitter.splitInput(this.userInputString);
			for (int i = 0; i < parameters.size(); i++)
				this.parameterList[i] = parameters.get(i);
			this.userInputInteger = LogicAnalyzer.getInteger(parameterList) - 1;
			return -1;

		} catch (Exception e) {
			if (LogicSplitter.getMessage() == 0) {
				Log.toLog(2, MessageHandler.getMessage(9));
				return 9;
			} else
				return LogicSplitter.getMessage();
		}
	}
	
	protected boolean isValidIndex() {
		return (userInputInteger > -1);
	}

	private void init() {
		PatternLib.setUpPattern();
		LogicSplitter.setUp();
		LogicAnalyzer.setUp();
	}
	
	public ArrayList<String> getFeedback() {
		feedback =  ListOfEvent.getFeedback();
		return feedback;
	}
	
	public void execute() throws Exception {
		return ;
	}
	
	public int getMessageValue() {
		return returnVal;
	}
}
