package com.compare.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CompareResponses implements IComparator {
	public FileInputStream inputStreamFile1, inputStreamFile2;
	public static HSSFWorkbook workbookFile1, workbookFile2;
	public static HSSFSheet sheetFile1, sheetFile2;
	
	int rowCount = 0;

	public boolean compare(String url1, String url2) {
		String responseFromFile1 = "";
		String responseFromFile2 = "";

		RestAssured.baseURI = url1;
		RequestSpecification requestforFile1 = RestAssured.given();
		Response respForAPI1 = requestforFile1.get();
		responseFromFile1 = respForAPI1.asString();
		int responseCodeFromAPI1 = respForAPI1.getStatusCode();
		
		if(responseCodeFromAPI1!=200) {
			System.out.println(url1 + " gives " + responseCodeFromAPI1 + " response");
		}
		
		RestAssured.baseURI = url2;
		RequestSpecification requestforFile2 = RestAssured.given();
		Response respForAPI2 = requestforFile2.get();
		responseFromFile2 = respForAPI2.asString();
		int responseCodeFromAPI2 = respForAPI2.getStatusCode();
		
		if(responseCodeFromAPI2!=200) {
			System.out.println(url2 + " gives " + responseCodeFromAPI2 + " response");
		}
		
		
		if (responseFromFile1.equals(responseFromFile2)
				&& (!responseFromFile1.equals("") || !responseFromFile2.equals(""))) {

			return true;
		}

		return false;

	}

	public String[] getData(File file1, File file2, int i) {
		String[] urls = new String[2];
		try {
			inputStreamFile1 = new FileInputStream(file1);
			inputStreamFile2 = new FileInputStream(file2);
			workbookFile1 = new HSSFWorkbook(inputStreamFile1);
			workbookFile2 = new HSSFWorkbook(inputStreamFile2);
			sheetFile1 = workbookFile1.getSheetAt(0);
			sheetFile2 = workbookFile2.getSheetAt(0);
			Row rowFile1 = sheetFile1.getRow(i);
			Cell cellFile1 = rowFile1.getCell(0);
			urls[0] = cellFile1.getStringCellValue();

			Row rowFile2 = sheetFile2.getRow(i);
			Cell cellFile2 = rowFile2.getCell(0);
			urls[1] = cellFile2.getStringCellValue();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return urls;
	}

	public int getLineCount(File f1, File f2) {

		try {
			inputStreamFile1 = new FileInputStream(f1);
			inputStreamFile2 = new FileInputStream(f2);
			workbookFile1 = new HSSFWorkbook(inputStreamFile1);
			workbookFile2 = new HSSFWorkbook(inputStreamFile2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sheetFile1 = workbookFile1.getSheetAt(0);
		sheetFile2 = workbookFile2.getSheetAt(0);

		int rowCountFile1 = sheetFile1.getLastRowNum() - sheetFile1.getLastRowNum();
		int rowCountFile2 = sheetFile2.getLastRowNum() - sheetFile2.getFirstRowNum();

		int maxRowCount = Math.max(rowCountFile1, rowCountFile2);
		return maxRowCount;

	}

	public void compareResponse(File file1, File file2) throws IOException {
		String[] data = new String[2];
		boolean result;
		int rowCount = getLineCount(file1, file2);
		for (int i = 0; i <= rowCount; i++) {
			data = getData(file1, file2, i);
			if (data[0].equals("") || data[1].equals("") || data[0].equals(null) || data[1].equals(null)) {
				System.out.println("Line " + i + " of either or both the file has null url");
				continue;
			}

			result = compare(data[0], data[1]);
			if (result) {
				System.out.println(data[0] + " equals " + data[1]);
			} else {
				System.out.println(data[0] + " not equals " + data[1]);
			}
		}

	}

}
