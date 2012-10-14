import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

public class TimeAnalyzer {
	private static String input;
	
	public static void setInput(String command) {
		input = command;
	}
	
	public static String getInput() {
		return input;
	}
	public static DateTime analyzeTime(String command) {
		input = command;
		prepareInputToAnalyze();
		return PatternLib.getDateTime(input);
	}
	
	public static void addDateTimeSeparator() {
		Pattern pat = Pattern.compile("[0-9] [0-9t]");
		Matcher mat = pat.matcher(input);
		if(mat.find()) {
			int pos = mat.start();
			insertCharAt(",", pos + 1);
		}
		pat = Pattern.compile("[0-9] (ne)");
		mat = pat.matcher(input);
		if(mat.find()) {
			int pos = mat.start();
			insertCharAt(",", pos + 1);
		}
		pat = Pattern.compile("m [0-9]");
		mat = pat.matcher(input);
		if(mat.find()) {
			int pos = mat.start();
			insertCharAt(",", pos + 1);
		}
		pat = Pattern.compile("[a-z] [a-z]");
		mat = pat.matcher(input);
		while(mat.find()) {
			int pos = mat.start();
			insertCharAt(",", pos + 1);
			mat = pat.matcher(input);
		}
		return;
	}
	
	public static void removeDateTimeSeparator() {
		input = input.replaceAll(",", " ");
	}
	
	public static void correctTime() {
		input = input.replaceAll("\\.", ":");
		if (input.matches("^\\d\\d?((am)|(pm)).{0,}")) {
			input = input.replaceFirst("(am)", ":00am");
			input = input.replaceFirst("(pm)", ":00pm");
		}
		input = input.replaceFirst("\\.", ":");
	}
	
	public static void toLowerCase() {
		input = input.toLowerCase().trim();
	}
	
	public static void removeExtraSpace() {
		input = input.replaceAll(" {1,}", " ");
	}
	
	public static void removeSpace() {
		input = input.replaceAll(" ", "");
	}
	
	public static void insertCharAt(String ch, int i) {
		StringBuffer buffer = new StringBuffer(input);
		input = buffer.insert(i, ch).toString();	
	}
	
	public static void correctDate() {
		input = input.replaceAll("-", "/");
		Pattern pat = Pattern.compile("\\d[a-z&&[^ap]]");
		Matcher mat = pat.matcher(input);
		while(mat.find()) {
			int pos = mat.start();
			insertCharAt("/", pos + 1);
		}
		pat = Pattern.compile("[a-z]\\d");
		mat = pat.matcher(input);
		while(mat.find()) {
			int pos = mat.start();
			insertCharAt("/", pos + 1);
		}
		return;
	}
	
	public static void prepareInputToAnalyze() {
		toLowerCase();
		removeExtraSpace();
		addDateTimeSeparator();
		removeSpace();
		correctTime();
		correctDate();
		removeDateTimeSeparator();
	}
}
