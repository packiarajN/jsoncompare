package com.paypal.json;

import org.json.JSONException;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class JSONCompareNGTesting {

	@DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { "localFile.json" , "productionFile.json" } };
    }
	
	@Test(dataProvider = "data-provider", singleThreaded = true)
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
		Object[][] arrObj = getExcelData("/data/test.xlsx");
		return arrObj;
	}
	
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
