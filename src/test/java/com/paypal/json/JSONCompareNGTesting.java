package com.paypal.json;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import au.com.bytecode.opencsv.CSVReader;

public class JSONCompareNGTesting {

	@DataProvider(name = "data-provider")
	public Object[][] dataProviderMethod() {
		return new Object[][] { { "localFile.json", "productionFile.json" } };
	}

	//@Test(dataProvider = "data-provider", singleThreaded = true)
	public void testMethod(String localFile, String prodFile) throws Exception {
		String expected = JSONAssert.readFileAsString(localFile);
		System.out.println(expected);

		String actual = JSONAssert.readFileAsString(prodFile);
		System.out.println(actual);
		Assert.assertNotNull(actual);

		JSONAssert.assertEquals(expected, actual, true);
	}

	@DataProvider(name = "excel-data")
	public Object[][] excelDP() throws Exception {
		// We are creating an object from the excel sheet data by calling a method that
		// reads data from the excel stored locally in our system
		Object[][] arrObj = getExcelData("/data/test.csv");
		return arrObj;
	}

	

	private Object[][] getExcelData(String fileName) throws IOException {
		
		 CSVReader reader = new CSVReader(new FileReader(fileName), ',', '"', 1);
	       
	      //Read all rows at once
	    //  List<String[]> allRows = reader.readAll();
	       
	      
	     
	     
	    
	     List<String[]> csvLines=reader.readAll();
	     for (int index=0; index < csvLines.size(); index++) {
	       String[] line=csvLines.get(index);
	       
	       return new Object[][] { { line[0], line[1] }}; 
	    
	       
	     }
	     
	   
		return null;
	}

	
	/*
	 * @Test(dataProvider = "excel-data") public void testCompareJSON(String
	 * localFile, String prodFile) throws Exception { String expected =
	 * JSONAssert.readFileAsString(localFile); System.out.println(expected);
	 * 
	 * String actual = JSONAssert.readFileAsString(prodFile);
	 * System.out.println(actual); Assert.assertNotNull(actual);
	 * 
	 * JSONAssert.assertEquals(expected, actual, true); }
	 */

	@Test(dataProvider = "excel-data")
	public void testCompareJSONURL(String url1, String url2) throws Exception {
		// String expected = JSONAssert.getJSON(url1);
		// System.out.println(expected);
		System.out.println(url1);
		System.out.println(url2);
		Assert.assertNotNull(url1);
		Assert.assertNotNull(url2);
		// String actual = JSONAssert.getJSON(url2);
		// System.out.println(actual);
		// Assert.assertNotNull(actual);

		// JSONAssert.assertEquals(expected, actual, true);
	}

}
