package Test;

import static org.junit.Assert.*;

import java.util.Vector;

import logic.LogicSplitter;
import logic.PatternLib;

import org.junit.Test;

public class TestSplitter {

	@Test
	public void testSplitInput() throws Exception {
		LogicSplitter.setUp();
		PatternLib.setUpPattern();
		Vector<String> temp=LogicSplitter.splitInput("add dfojnvojern1 7:00 9 sep #wnwf r-1min");
		System.out.print(temp.toString()); 
	}

}
