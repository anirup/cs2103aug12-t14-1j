package logic;
 
import java.util.Vector;

public class LogicSplitterUndone extends LogicSplitter{
	protected static Vector<String> splitInputUndone(String userInput,
			Vector<String> parameterList) {
		extractKeywordsAlongWithHashTags(userInput, parameterList);
		return parameterList;
	}

}
 