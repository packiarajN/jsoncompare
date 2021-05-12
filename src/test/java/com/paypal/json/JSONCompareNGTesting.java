package com.paypal.json;

import org.json.JSONException;
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
	
}
