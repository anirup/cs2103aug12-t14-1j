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
		input = input.replaceFirst("\\.", ":");
		Pattern pat = Pattern.compile("^(00?):?(00?) ?((am))?");
		Matcher mat = pat.matcher(input);
		if (mat.find()) {
			int pos = mat.end();
			input = input.substring(pos, input.length());
			insertCharAt("23:59", 0);
		}
		if (input.matches("^\\d\\d?")) {
			StringBuilder sb = new StringBuilder(input);
			sb.append(":00");
			input = sb.toString();
		}
		if (input.matches("^\\d\\d?\\,[0-9tn].{0,}")) {
			input = input.replaceFirst(",", ":00,");
		}
		if (input.matches("^\\d\\d? ?((am)|(pm)).{0,}")) {
			input = input.replaceFirst("(am)", ":00am");
			input = input.replaceFirst("(pm)", ":00pm");
		}
	}
	
	public static void toLowerCase() {
		input = input.toLowerCase().trim();
	}
	
	public static void removeExtraSpace() {
		input = input.replaceAll(" {1,}", " ").trim();
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
