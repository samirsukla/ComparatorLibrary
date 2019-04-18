package com.comparatortest.test;

import java.io.File;
import java.net.URL;

import org.testng.annotations.Test;

import com.compare.response.CompareResponses;

public class TestCompareResponses{
	CompareResponses comp ;
	
	@Test
	public void compare() {
		comp = new CompareResponses();
		comp.compareResponse(new File("src/test/resources/File1.xls"), new File("src/test/resources/File2.xls"));
		
	}

}
