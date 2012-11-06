package Project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringOperation {
	
	public static String removeFirstWord(String input) {
		int firstSpace = input.indexOf(" ");
		if (firstSpace >= 0) {
			return input.substring(firstSpace, input.length());
		}
		return "";
	}
	
	public static String getFirstWord(String input) {
		int firstSpace = input.indexOf(" ");
		if (firstSpace >= 0) {
			return input.substring(0, firstSpace);
		}
		return input;
	}
	
	public static boolean isValidIsDone(String isDone) {
		if(!isDone.equalsIgnoreCase("true") && !isDone.equalsIgnoreCase("false")) {
			return false;
		} 
		return true;
	}
	
	public static int extractFirstNumber(String input) {
		Pattern pat = Pattern.compile("[a-z\\- &&[^0-9]][0-9][0-9]?[a-z &&[^0-9]]");
		Matcher mat = pat.matcher(input);
		if(mat.find()) {
			int posStart = mat.start() + 1;
			int posEnd = mat.end() - 1;
			int firstNumber = Integer.parseInt(input.substring(posStart, posEnd));
			return firstNumber;
		}
		return -1;
	}
	
	public static int isInteger(String input) {
		int intValue;
		try {
			intValue = Integer.parseInt(input);
		} catch (NumberFormatException e){
			return -1;
		}
		return intValue;
	}
	
	public static boolean isContains(String keyWord) {
		return false;
	}
	
	public static String prepareStringToAnalyze(String input) {
		input = removeExtraSpace(input);
		input = removeFirstWord(input);
		return input.trim();
	}
	
	private static String addDateTimeSeparator(String input) {
		Pattern pat = Pattern.compile("[0-9] [0-9t]");
		Matcher mat = pat.matcher(input);
		if(mat.find()) {
			int pos = mat.start();
			input = insertCharAt(input, ",", pos + 1);
		}
		pat = Pattern.compile("[0-9] (ne)");
		mat = pat.matcher(input);
		if(mat.find()) {
			int pos = mat.start();
			input = insertCharAt(input, ",", pos + 1);
		}
		pat = Pattern.compile("m [0-9]");
		mat = pat.matcher(input);
		if(mat.find()) {
			int pos = mat.start();
			input = insertCharAt(input, ",", pos + 1);
		}
		pat = Pattern.compile("[a-z] [a-z]");
		mat = pat.matcher(input);
		while(mat.find()) {
			int pos = mat.start();
			input = insertCharAt(input, ",", pos + 1);
			mat = pat.matcher(input);
		}
		return input;
	}
	
	private static String removeDateTimeSeparator(String input) {
		input = input.replaceAll(",", " ");
		return input;
	}
	
	public static int firstMatchPosition(String input, Pattern pat) {
		Matcher mat = pat.matcher(input);
		int startPos;
		if (mat.find()) {
			startPos = mat.start();
			return startPos;
		}
		return -1;
	}
	
	public static String firstMatch(String input, Pattern pat) {
		Matcher mat = pat.matcher(input);
		if (mat.find()) {
			return mat.group();
		}
		return "";
	}
	
	public static String concat(String input, String subString) {
		Pattern pat = Pattern.compile(subString);
		Matcher mat = pat.matcher(input);
		if(mat.find()) {
			int startIndex  = mat.start();
			int endIndex = mat.end();
			return input.substring(0, startIndex) + input.substring(endIndex, input.length());
		}
		return input;
	}
	
	private static String correctTime(String input) {
		input = input.replaceFirst("\\.", ":");
		Pattern pat = Pattern.compile("^(00?):?(00?) ?((am))?");
		Matcher mat = pat.matcher(input);
		if (mat.find()) {
			int pos = mat.end();
			input = input.substring(pos, input.length());
			input = insertCharAt(input, "23:59", 0);
		}
		if (input.matches("^\\d\\d?\\,[0-9tn].{0,}")) {
			input = input.replaceFirst(",", ":00,");
		}
		if (input.matches("^\\d\\d? ?((am)|(pm)).{0,}")) {
			input = input.replaceFirst("(am)", ":00am");
			input = input.replaceFirst("(pm)", ":00pm");
		}
		return input;
	}
	
	private static String toLowerCase(String input) {
		input = input.toLowerCase().trim();
		return input;
	}
	
	public static String removeExtraSpace(String input) {
		input = input.replaceAll(" {1,}", " ").trim();
		return input;
	}
	
	public static String removeSpace(String input) {
		input = input.replaceAll(" ", "");
		return input;
	}
	
	public static String insertCharAt(String input, String ch, int i) {
		StringBuffer buffer = new StringBuffer(input);
		input = buffer.insert(i, ch).toString();	
		return input;
	}
	
	private static String correctDate(String input) {
		input = input.replaceAll("-", "/");
		Pattern pat = Pattern.compile("\\d[a-z&&[^ap]]");
		Matcher mat = pat.matcher(input);
		while(mat.find()) {
			int pos = mat.start();
			input = insertCharAt(input, "/", pos + 1);
		}
		pat = Pattern.compile("[a-z]\\d");
		mat = pat.matcher(input);
		while(mat.find()) {
			int pos = mat.start();
			input = insertCharAt(input, "/", pos + 1);
		}
		return input;
	}
	
	public static String prepareInputToAnalyzeTime(String input) {
		input = toLowerCase(input);
		input = addDateTimeSeparator(input);
		input = removeSpace(input);
		input = correctTime(input);
		input = correctDate(input);
		input = removeDateTimeSeparator(input);
		return input;
	}

	public static String booleanToString(boolean _isDone) {
		if(_isDone) {
			return "true";
		}
		return "false";
	}
	
	public static String crossOutSubString(String subString, String string) {
		int index = string.indexOf(subString);
		if(index < 0) {
			return null;
		}
		int lengthOfSubString = subString.length();
		string = concat(string,subString);
		for(int i = 0; i < lengthOfSubString; i++) {
			string = insertCharAt(string, "=", index);
		}
		return string;
	}
}
