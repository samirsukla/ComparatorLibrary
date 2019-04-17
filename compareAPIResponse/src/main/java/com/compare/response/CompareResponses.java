package com.compare.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class CompareResponses implements IComparator {
	public FileInputStream inputStreamFile1,inputStreamFile2;
	public HSSFWorkbook workbookFile1,workbookFile2;
	public HSSFSheet sheetFile1,sheetFile2;


	int rowCount =0;

	public boolean compare(URL url1, URL url2) {
		// TODO Auto-generated method stub
		return false;
	}

	public HashMap<String, String> getData(File file1, File file2) {
		try {
			inputStreamFile1 = new FileInputStream(file1);
			inputStreamFile2 = new FileInputStream(file2);
			workbookFile1 = new HSSFWorkbook(inputStreamFile1);
			workbookFile2 = new HSSFWorkbook(inputStreamFile2);
			sheetFile1 = workbookFile1.getSheetAt(0);
			sheetFile2 = workbookFile2.getSheetAt(0);
			
			int rowCountFile1 = sheetFile1.getLastRowNum() - sheetFile1.getLastRowNum();
			int rowCountFile2 = sheetFile2.getLastRowNum() - sheetFile2.getFirstRowNum();
			
			int maxRowCount = Math.max(rowCountFile1, rowCountFile2);
			for (int i=0; i<=maxRowCount; i++) {
				
				String url1 = sheetFile1.getRow(i);
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	public void compareResponse() {

	}

}
