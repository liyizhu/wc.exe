package com.liyizhu.wc;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WcUtilTest {
	
	@Test
	public void testLineNumber() {
		WcUtil.lineNumber("E:\\test.c");
		WcUtil.lineNumber("E:\\test.txt");
		WcUtil.lineNumber("E:\\123.txt");
	}

	@Test
	public void testCharNumber() {
		WcUtil.charNumber("E:\\test.c");
		WcUtil.charNumber("E:\\test.txt");
		WcUtil.charNumber("E:\\123.txt");
	}

	@Test
	public void testWordNumber() {
		WcUtil.wordNumber("E:\\test.c");
		WcUtil.wordNumber("E:\\test.txt");
		WcUtil.wordNumber("E:\\123.txt");
	}

	@Test
	public void testComplexData() {
		WcUtil.complexData("E:\\test.c");
		WcUtil.complexData("E:\\test.txt");
		WcUtil.complexData("E:\\123.txt");
	}
	@Test
	public void  testRecurseHandleAllFiles() {
		WcUtil.recurseHandleAllFiles("E:\\test","\\..c");
		WcUtil.recurseHandleAllFiles("E:\\test","\\..txt");
	}
	@Test
	public void testShowHelpDoc() {
		WcUtil.showHelpDoc();
	}

}
