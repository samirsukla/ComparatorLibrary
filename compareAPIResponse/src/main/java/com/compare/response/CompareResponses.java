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

public class CompareResponses implements IComparator {
	public FileInputStream inputStreamFile1,inputStreamFile2;
	public HSSFWorkbook workbookFile1,workbookFile2;
	public HSSFSheet sheetFile1,sheetFile2;
	//	File file1 = new File("src/test/resources/File1.xls");
	//	File file2 = new File("src/test/resources/File2.xls");
	URL urlFile1,urlFile2;


	int rowCount =0;

	public boolean compare(String url1, String url2) {
		String inlineResponseFile1="";
		String inlineResponseFile2="";

		try {
			URL urlFile1 = new URL(url1);
			URL urlFile2 = new URL(url2);

			HttpURLConnection connFile1 = (HttpURLConnection)urlFile1.openConnection();
			HttpURLConnection connFile2 = (HttpURLConnection)urlFile2.openConnection();
			connFile1.setRequestMethod("GET");
			connFile1.connect();
			connFile2.setRequestMethod("GET");
			connFile2.connect();
			int responseCodeAPI1 = connFile1.getResponseCode();
			int responseCodeAPI2 = connFile2.getResponseCode();

			if(responseCodeAPI1!=200) {
//				throw new RuntimeException("HttpResponseCodefor API1 : " + responseCodeAPI1);
			}
			else
			{
				Scanner sc = new Scanner(urlFile1.openStream());
				while(sc.hasNext()) {
					inlineResponseFile1+=sc.nextLine();
				}


				sc.close();
			}

			if(responseCodeAPI2!=200) {
//				throw new RuntimeException("HttpResponseCodefor API2 : " + responseCodeAPI2);
			}
			else
			{
				Scanner sc = new Scanner(urlFile2.openStream());
				while(sc.hasNext()) {
					inlineResponseFile2+=sc.nextLine();
				}


				sc.close();
			}
		}

		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(inlineResponseFile1.equals(inlineResponseFile2)) {

			return true;
		}

		return false;


	}	





	public String[] getData(File file1, File file2) {
		String[] urls = new String[2];
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

				Row rowFile1 = sheetFile1.getRow(i);
				Cell cellFile1 = rowFile1.getCell(0);
				String urlFile1 = cellFile1.getStringCellValue();

				Row rowFile2 = sheetFile2.getRow(i);
				Cell cellFile2 = rowFile2.getCell(0);
				String urlFile2 = cellFile2.getStringCellValue();



				urls[0] = urlFile1;
				urls[1] = urlFile2;
				compare(urls[0],urls[1]);


			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return urls;
	}

	public void compareResponse(File file1, File file2) {
		String[] data = new String[2];
		boolean result;
		data = getData(file1, file2);
		result = compare(data[0], data[1]);
		if(result) {
			System.out.println(data[0]+" equals "+data[1]);
		}
		else {
			System.out.println(data[0]+" not equals "+data[1]);
		}
	}

}
