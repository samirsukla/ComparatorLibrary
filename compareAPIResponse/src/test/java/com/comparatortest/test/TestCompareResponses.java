package com.comparatortest.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.compare.response.CompareResponses;



public class TestCompareResponses{
	CompareResponses comp ;
	
	@Test
	public void testcompare() throws IOException {
		comp = new CompareResponses();
		comp.compareResponse(new File("src/test/resources/File1.xls"), new File("src/test/resources/File2.xls"));
		
	}
	
	
	public void testGetData() {
		comp = new CompareResponses();
		String[] getDataResults = comp.getData(new File("src/test/resources/File1.xls"), new File("src/test/resources/File2.xls")
				,2);
		Assert.assertEquals("https://reqres.in/api/users/2", getDataResults[0].trim());
		Assert.assertEquals("https://reqres.in/api/users/2", getDataResults[1].trim());
	}

}
