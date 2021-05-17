package com.paypal.json;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class JSONCompareNGTesting {

	private static final String FILE_PATH = "/data/test.txt";

	@BeforeSuite
	public void BeforeTest() throws IOException {
		FileChannel.open(Paths.get(FILE_PATH), StandardOpenOption.WRITE).truncate(0).close();
	}
	
	@DataProvider(name = "data-provider")
	public Object[][] dataProviderMethod() throws IOException {
		return getCSVData("/data/test.csv");
	}

	@Test(dataProvider = "data-provider", singleThreaded = false)
	public void testMethod(String localFile, String prodFile) throws Exception {
		String expected = JSONAssert.readFileAsString(localFile);
		System.out.println(expected);
		String actual = JSONAssert.readFileAsString(prodFile);
		System.out.println(actual);
		//Assert.assertNotNull(actual);
		final String NEWLINE = System.getProperty("line.separator");
		
		
		//Assert.assertEquals(Boolean.TRUE, JSONAssert.assertEquals(expected, actual, true));
		//Assert.assertEquals(Boolean.FALSE, JSONAssert.assertEquals(expected, actual, true));
		
		String data []= new String[3];
		
		data[0]= localFile;
		data[1]= prodFile;
		data[2]= JSONAssert.assertEquals(expected, actual, true).toString();
		
		Path p = Paths.get(FILE_PATH);
		String s = data[0] +","+ data[1] +","+ data[2]+System.lineSeparator();
		
		try {
		    Files.write(p, s.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
		    System.err.println(e);
		}
		
		Assert.assertEquals(Boolean.TRUE, JSONAssert.assertEquals(expected, actual, true));	
	}

	@AfterTest
	public void exportReport() throws IOException {
		
		String csv = "/data/test2.csv";
		CSVWriter csvWriter= new CSVWriter(new FileWriter(csv), ',' );
		
		List<String[]> datas= new ArrayList<String[]>();
		
		String data []= new String[3];
		try {
			// the file to be opened for reading
			FileInputStream fis = new FileInputStream(FILE_PATH);
			Scanner sc = new Scanner(fis); // file to be scanned
			// returns true if there is another line to read
			while (sc.hasNextLine()) {
				//System.out.println(sc.nextLine()); // returns the line that was skipped
				
				String inputData = sc.nextLine();
				data = inputData.split(",");
				datas.add(data);
			}
			sc.close(); // closes the scanner
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		
		csvWriter.writeAll(datas);
		csvWriter.close();
		
	}
	
	@DataProvider(name = "csv-data")
	public Object[][] excelDP() throws Exception {
		// We are creating an object from the excel sheet data by calling a method that
		// reads data from the excel stored locally in our system
		Object[][] arrObj = getCSVData("/data/test.csv");
		return arrObj;
	}

	

	private Object[][] getCSVData(String fileName) throws IOException {
		
		 CSVReader reader = new CSVReader(new FileReader(fileName), ',', '"', 1);
	     List<String[]> csvLines=reader.readAll();
	     Object[][]  obj =new Object[csvLines.size()][2];
	     for (int index=0; index < csvLines.size(); index++) {
	       String[] line=csvLines.get(index);
	       obj[index][0] = line[0];
	       obj[index][1] = line[1];
	     }
		return obj;
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

	//@Test(dataProvider = "excel-data")
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
