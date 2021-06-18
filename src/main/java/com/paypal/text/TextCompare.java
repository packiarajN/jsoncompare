package com.paypal.text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextCompare {
	
	public String getContent(String input, int option) throws IOException {
		
		StringBuffer inputTxt = new StringBuffer();
		
		if(option==1) {
		  //Connect to URL
		  String command = "curl -X POST  " + input; Process
		  p = Runtime.getRuntime().exec(command); BufferedReader reader = new
		  BufferedReader(new InputStreamReader(p.getInputStream())); String line = "";
		  while ((line = reader.readLine())!= null) {
			  System.out.println("JSONAssert.main()"+line);
			  inputTxt.append(line + "\n"); 
		  }
		} else if(option==2) {
			//Connect to file 
			inputTxt.append(new String(Files.readAllBytes(Paths.get(input))));
		}
		
		System.out.println("TextCompare.getContent()"+ inputTxt.toString());
		return inputTxt.toString();
	}
	
	public Map<String, List<String>> convertToMap(String data) {
		
		Map<String, List<String>> datamap=new  HashMap<String, List<String>>();
		int flag =0;
		List<String> childList = new ArrayList<String>();
		String []stringarr = data.split("\n");
		
		 String []copyArray = new String[stringarr.length-1];
		System.arraycopy(stringarr, 1, copyArray, 1, copyArray.length-1); 
		String parant = "";
		
		for (String e : copyArray) {
			
			
			if(e !=null && !e.isBlank())
			if(! e.startsWith("\t")) {
				
				flag = flag + 1;
				if(flag == 2) {
					datamap.put(parant, childList);
					flag=1;
					childList = new ArrayList<String>();
				}
				System.err.println(e);
				parant = e.substring(1, e.length()-1);
				
			} else if (e.startsWith("\t")) {
				
				childList.add(e.substring(1, e.length()-1));
				System.err.println("Child"+ e);
				
			}
			
		}
		datamap.put(parant, childList);
		return datamap;
	}
	
	public static void compare(Map<String, List<String>> first, Map<String, List<String>> second) {
		
		if (first.size() != second.size()) {
			System.err.println("Both files are different");

		}

		for (String firstKey : first.keySet()) {
			if (second.containsKey(firstKey)) {
				System.out.println("Key found" + firstKey);
				List<String> firstList = first.get(firstKey);
				List<String> secondList = second.get(firstKey);
				List<String> compareResult = compareList(firstList, secondList);
				System.out.println("TextCompare.compare()" + compareResult.size());
				
			} else {
				System.out.println("Key Not Found" + firstKey);
			}

		}
	}
	private static List<String> compareList(List<String> firstList, List<String> secondList) {
		
		
		
		firstList.removeAll(secondList);
		System.err.println(firstList.size());
		return firstList;
	}

	static String[] containsNewLine(String str) {
	    Pattern regex = Pattern.compile("^(.*)$", Pattern.MULTILINE);
	    String []stringarr = str.split("\r\n|\n|\r");
	    System.out.println("TextCompare.containsNewLine()"+stringarr.length);
	    return stringarr;
	}
	
	public static void main(String[] args) throws IOException {
		TextCompare tx = new TextCompare();
		Map<String, List<String>> first= tx.convertToMap(tx.getContent("testing.txt", 2));
		Map<String, List<String>> second = tx.convertToMap(tx.getContent("testing2.txt", 2));
		
		compare(first, second);
		
		System.out.println("TextCompare.main()" );
	}

}
