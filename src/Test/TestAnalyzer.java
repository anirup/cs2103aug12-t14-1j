package Test; 
import static org.junit.Assert.*;
import logic.LogicAnalyzer;

import org.junit.Before;
import org.junit.Test;
public class TestAnalyzer {
	private static String[] newparameterList={"add", "abcd", "2012-11-07T17:00:00.000+08:00", "2012-11-07T18:00:00.000+08:00", "r-1min", "-1"};
	private static String[] newparameterList1={"add"," abcdef #a#b#c", "5:00pm tmr", "r-1min", "-1", "-1"};
	private static String[] newparameterList2={"add", "abcdef #high #low", "2012-11-13T17:00:00.000+08:00", "2012-11-15T18:00:00.000+08:00", "r-1min", "-1"};
	private static String[] newparameterList3={"add","abcdasfaef #a#b#c #high", "5:00pm tmr","6:00am next Monday", "r-1min", "-1"};
	private static String[] newparameterList4={"add", "fenbojaefnsfnmgpwkr #low", "5.30pm today", "2100 tmr", "r-1min", "-1"};
	private static String[] newparameterList5={"add", "fenbojaefnsfnmgpwkr #low", "17:00 12/12/12", "2100 tmr", "r-2hr1min", "-1"};
	private static String[] newparameterList6={"add", "fenbojamgpwkr #low", "17:00 12/12/12", "2100 tmr", "-1","-1"};
	private static String[] newparameterList7={"add", "fenbojamgpwkr #low", "r-1min", "17:00 12/12/12", "2100 tmr","-1"};
	private static String[] newparameterList8={"add", "jet bijaer n gwajngomn egtaejn ","2100","-1","-1","-1"};
	private static String[] newparameterList9={"add", "fhebi", "-1", "-1", "-1", "-1"};
	private static String[] newparameterList10={"update", "2", "-1", "-1", "-1", "-1"};
	private static String[] newparameterList11={"search", "abcd #ghi #klm", "-1", "-1", "-1", "-1"};
	private static String[] newparameterList12={"delete", "2", "-1", "-1", "-1", "-1"};
	@Test
	public void testGetEventString1() {
		assertEquals("testGetEventString","",LogicAnalyzer.getAddUpdateEventString(newparameterList));
	}
	public void testGetEventString2() {
		assertEquals("testGetEventString","",LogicAnalyzer.getAddUpdateEventString(newparameterList1));
	}
	public void testGetEventString3() {
		assertEquals("testGetEventString","",LogicAnalyzer.getAddUpdateEventString(newparameterList2));
	}
	public void testGetEventString4() {
		assertEquals("testGetEventString","",LogicAnalyzer.getAddUpdateEventString(newparameterList3));
	}
	public void testGetEventString5() {
		assertEquals("testGetEventString","",LogicAnalyzer.getAddUpdateEventString(newparameterList4));
	}
	public void testGetEventString6() {
		assertEquals("testGetEventString","",LogicAnalyzer.getAddUpdateEventString(newparameterList5));
	}
	public void testGetEventString7() {
		assertEquals("testGetEventString","",LogicAnalyzer.getAddUpdateEventString(newparameterList6));
	}
	public void testGetEventString8() {
		assertEquals("testGetEventString","",LogicAnalyzer.getAddUpdateEventString(newparameterList7));
	}
	public void testGetEventString9() {
		assertEquals("testGetEventString","",LogicAnalyzer.getAddUpdateEventString(newparameterList8));
	}
	public void testGetEventString10() {
		assertEquals("testGetEventString","",LogicAnalyzer.getAddUpdateEventString(newparameterList9
				));
	}

	@Test
	public void testGetKeyWords() {
		
	}

	@Test
	public void testGetHashTags() {
		
	}

	@Test
	public void testGetCommand() {
		
	}

	@Test
	public void testGetInteger() {
		
	}
}
