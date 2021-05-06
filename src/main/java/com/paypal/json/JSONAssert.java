package com.paypal.json;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.paypal.json.comparator.CustomComparator;
import com.paypal.json.comparator.JSONComparator;


public class JSONAssert {
    private JSONAssert() {}

    /**
     * Asserts that the JSONObject provided matches the expected string.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param expectedStr Expected JSON string
     * @param actual JSONObject to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String expectedStr, JSONObject actual, boolean strict)
            throws JSONException {
        assertEquals(expectedStr, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }
    
    /**
     * Asserts that the JSONObject provided matches the expected string.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param message Error message to be displayed in case of assertion failure
     * @param expectedStr Expected JSON string
     * @param actual JSONObject to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String message, String expectedStr, JSONObject actual, boolean strict)
        throws JSONException {
        assertEquals(message, expectedStr, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }

    /**
     * Asserts that the JSONObject provided does not match the expected string.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @see #assertEquals(String, JSONObject, boolean)
     * 
     * @param expectedStr Expected JSON string
     * @param actual JSONObject to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String expectedStr, JSONObject actual, boolean strict)
            throws JSONException {
        assertNotEquals(expectedStr, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }
    
    /**
     * Asserts that the JSONObject provided does not match the expected string.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @see #assertEquals(String, JSONObject, boolean)
     * 
     * @param message Error message to be displayed in case of assertion failure
     * @param expectedStr Expected JSON string
     * @param actual JSONObject to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String message, String expectedStr, JSONObject actual, boolean strict)
        throws JSONException {
        assertNotEquals(message, expectedStr, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }

    /**
     * Asserts that the JSONObject provided matches the expected string.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param expectedStr Expected JSON string
     * @param actual JSONObject to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String expectedStr, JSONObject actual, JSONCompareMode compareMode)
            throws JSONException {
        assertEquals("", expectedStr, actual, compareMode);
    }
    
    /**
     * Asserts that the JSONObject provided matches the expected string.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param message Error message to be displayed in case of assertion failure
     * @param expectedStr Expected JSON string
     * @param actual JSONObject to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String message, String expectedStr, JSONObject actual, JSONCompareMode compareMode)
        throws JSONException {
        Object expected = JSONParser.parseJSON(expectedStr);
        if (expected instanceof JSONObject) {
            assertEquals(message, (JSONObject)expected, actual, compareMode);
        }
        else {
            throw new AssertionError("Expecting a JSON array, but passing in a JSON object");
        }
    }

    /**
     * Asserts that the JSONObject provided does not match the expected string.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @see #assertEquals(String, JSONObject, JSONCompareMode)
     * 
     * @param expectedStr Expected JSON string
     * @param actual JSONObject to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String expectedStr, JSONObject actual, JSONCompareMode compareMode)
            throws JSONException {
        assertNotEquals("", expectedStr, actual, compareMode);
    }
    
    /**
     * Asserts that the JSONObject provided does not match the expected string.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @see #assertEquals(String, JSONObject, JSONCompareMode)
     * 
     * @param message Error message to be displayed in case of assertion failure
     * @param expectedStr Expected JSON string
     * @param actual JSONObject to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String message, String expectedStr, JSONObject actual, JSONCompareMode compareMode)
        throws JSONException {
        Object expected = JSONParser.parseJSON(expectedStr);
        if (expected instanceof JSONObject) {
            assertNotEquals(message, (JSONObject) expected, actual, compareMode);
        }
        else {
            throw new AssertionError("Expecting a JSON array, but passing in a JSON object");
        }
    }

    /**
     * Asserts that the JSONArray provided matches the expected string.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param expectedStr Expected JSON string
     * @param actual JSONArray to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String expectedStr, JSONArray actual, boolean strict)
            throws JSONException {
        assertEquals(expectedStr, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }
    
    /**
     * Asserts that the JSONArray provided matches the expected string.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param message Error message to be displayed in case of assertion failure
     * @param expectedStr Expected JSON string
     * @param actual JSONArray to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String message, String expectedStr, JSONArray actual, boolean strict)
        throws JSONException {
        assertEquals(message, expectedStr, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }

    /**
     * Asserts that the JSONArray provided does not match the expected string.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param expectedStr Expected JSON string
     * @param actual JSONArray to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String expectedStr, JSONArray actual, boolean strict)
            throws JSONException {
        assertNotEquals(expectedStr, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }
    
    /**
     * Asserts that the JSONArray provided does not match the expected string.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param message Error message to be displayed in case of assertion failure
     * @param expectedStr Expected JSON string
     * @param actual JSONArray to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String message, String expectedStr, JSONArray actual, boolean strict)
        throws JSONException {
        assertNotEquals(message, expectedStr, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }

    /**
     * Asserts that the JSONArray provided matches the expected string.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param expectedStr Expected JSON string
     * @param actual JSONArray to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String expectedStr, JSONArray actual, JSONCompareMode compareMode)
            throws JSONException {
        assertEquals("", expectedStr, actual, compareMode);
    }
    
    /**
     * Asserts that the JSONArray provided matches the expected string.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param message Error message to be displayed in case of assertion failure
     * @param expectedStr Expected JSON string
     * @param actual JSONArray to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String message, String expectedStr, JSONArray actual, JSONCompareMode compareMode)
        throws JSONException {
        Object expected = JSONParser.parseJSON(expectedStr);
        if (expected instanceof JSONArray) {
            assertEquals(message, (JSONArray) expected, actual, compareMode);
        }
        else {
            throw new AssertionError("Expecting a JSON object, but passing in a JSON array");
        }
    }

    /**
     * Asserts that the JSONArray provided does not match the expected string.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param expectedStr Expected JSON string
     * @param actual JSONArray to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String expectedStr, JSONArray actual, JSONCompareMode compareMode)
            throws JSONException {
        Object expected = JSONParser.parseJSON(expectedStr);
        if (expected instanceof JSONArray) {
            assertNotEquals((JSONArray) expected, actual, compareMode);
        }
        else {
            throw new AssertionError("Expecting a JSON object, but passing in a JSON array");
        }
    }
    
    /**
     * Asserts that the JSONArray provided does not match the expected string.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param message Error message to be displayed in case of assertion failure
     * @param expectedStr Expected JSON string
     * @param actual JSONArray to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String message, String expectedStr, JSONArray actual, JSONCompareMode compareMode)
        throws JSONException {
        Object expected = JSONParser.parseJSON(expectedStr);
        if (expected instanceof JSONArray) {
            assertNotEquals(message, (JSONArray) expected, actual, compareMode);
        }
        else {
            throw new AssertionError("Expecting a JSON object, but passing in a JSON array");
        }
    }

    /**
     * Asserts that the JSONArray provided matches the expected string.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param expectedStr Expected JSON string
     * @param actualStr String to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String expectedStr, String actualStr, boolean strict)
            throws JSONException {
        assertEquals(expectedStr, actualStr, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }
    
    /**
     * Asserts that the JSONArray provided matches the expected string.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param message Error message to be displayed in case of assertion failure
     * @param expectedStr Expected JSON string
     * @param actualStr String to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String message, String expectedStr, String actualStr, boolean strict)
        throws JSONException {
        assertEquals(message, expectedStr, actualStr, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }

    /**
     * Asserts that the JSONArray provided does not match the expected string.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param expectedStr Expected JSON string
     * @param actualStr String to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String expectedStr, String actualStr, boolean strict)
            throws JSONException {
        assertNotEquals(expectedStr, actualStr, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }
    
    /**
     * Asserts that the JSONArray provided does not match the expected string.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param message Error message to be displayed in case of assertion failure
     * @param expectedStr Expected JSON string
     * @param actualStr String to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String message, String expectedStr, String actualStr, boolean strict)
        throws JSONException {
        assertNotEquals(message, expectedStr, actualStr, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }

    /**
     * Asserts that the JSONArray provided matches the expected string.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param expectedStr Expected JSON string
     * @param actualStr String to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String expectedStr, String actualStr, JSONCompareMode compareMode)
            throws JSONException {
        assertEquals("", expectedStr, actualStr, compareMode);
    }
    
    /**
     * Asserts that the JSONArray provided matches the expected string.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param message Error message to be displayed in case of assertion failure
     * @param expectedStr Expected JSON string
     * @param actualStr String to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String message, String expectedStr, String actualStr, JSONCompareMode compareMode)
        throws JSONException {
        if (expectedStr==actualStr) return;
        if (expectedStr==null){
            throw new AssertionError("Expected string is null.");
        }else if (actualStr==null){
            throw new AssertionError("Actual string is null.");
        }
        JSONCompareResult result = JSONCompare.compareJSON(expectedStr, actualStr, compareMode);
        if (result.failed()) {
            throw new AssertionError(getCombinedMessage(message, result.getMessage()));
        }
    }

    /**
     * Asserts that the JSONArray provided does not match the expected string.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param expectedStr Expected JSON string
     * @param actualStr String to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String expectedStr, String actualStr, JSONCompareMode compareMode)
            throws JSONException {
        assertNotEquals("", expectedStr, actualStr, compareMode);
    }
    
    /**
     * Asserts that the JSONArray provided does not match the expected string.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param message Error message to be displayed in case of assertion failure
     * @param expectedStr Expected JSON string
     * @param actualStr String to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String message, String expectedStr, String actualStr, JSONCompareMode compareMode)
        throws JSONException {
        JSONCompareResult result = JSONCompare.compareJSON(expectedStr, actualStr, compareMode);
        if (result.passed()) {
            throw new AssertionError(getCombinedMessage(message, result.getMessage()));
        }
    }

    /**
     * Asserts that the json string provided matches the expected string.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param expectedStr Expected JSON string
     * @param actualStr String to compare
     * @param comparator Comparator
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String expectedStr, String actualStr, JSONComparator comparator)
            throws JSONException {
        assertEquals("", expectedStr, actualStr, comparator);
        
    }
    
    /**
     * Asserts that the json string provided matches the expected string.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param message Error message to be displayed in case of assertion failure
     * @param expectedStr Expected JSON string
     * @param actualStr String to compare
     * @param comparator Comparator
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String message, String expectedStr, String actualStr, JSONComparator comparator)
        throws JSONException {
        JSONCompareResult result = JSONCompare.compareJSON(expectedStr, actualStr, comparator);
        if (result.failed()) {
            throw new AssertionError(getCombinedMessage(message, result.getMessage()));
        }
    }

    /**
     * Asserts that the json string provided does not match the expected string.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param expectedStr Expected JSON string
     * @param actualStr String to compare
     * @param comparator Comparator
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String expectedStr, String actualStr, JSONComparator comparator)
            throws JSONException {
        assertNotEquals("", expectedStr, actualStr, comparator);
    }
    
    /**
     * Asserts that the json string provided does not match the expected string.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param message Error message to be displayed in case of assertion failure
     * @param expectedStr Expected JSON string
     * @param actualStr String to compare
     * @param comparator Comparator
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String message, String expectedStr, String actualStr, JSONComparator comparator)
        throws JSONException {
        JSONCompareResult result = JSONCompare.compareJSON(expectedStr, actualStr, comparator);
        if (result.passed()) {
            throw new AssertionError(getCombinedMessage(message, result.getMessage()));
        }
    }

    /**
     * Asserts that the JSONObject provided matches the expected JSONObject.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param expected Expected JSONObject
     * @param actual JSONObject to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(JSONObject expected, JSONObject actual, boolean strict)
            throws JSONException {
        assertEquals(expected, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }
    
    /**
     * Asserts that the JSONObject provided matches the expected JSONObject.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param message Error message to be displayed in case of assertion failure
     * @param expected Expected JSONObject
     * @param actual JSONObject to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String message, JSONObject expected, JSONObject actual, boolean strict)
        throws JSONException {
        assertEquals(message, expected, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }

    /**
     * Asserts that the JSONObject provided does not match the expected JSONObject.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param expected Expected JSONObject
     * @param actual JSONObject to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(JSONObject expected, JSONObject actual, boolean strict)
            throws JSONException {
        assertNotEquals(expected, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }
    
    /**
     * Asserts that the JSONObject provided does not match the expected JSONObject.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param message Error message to be displayed in case of assertion failure
     * @param expected Expected JSONObject
     * @param actual JSONObject to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String message, JSONObject expected, JSONObject actual, boolean strict)
        throws JSONException {
        assertNotEquals(message, expected, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }

    /**
     * Asserts that the JSONObject provided matches the expected JSONObject.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param expected Expected JSONObject
     * @param actual JSONObject to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(JSONObject expected, JSONObject actual, JSONCompareMode compareMode)
            throws JSONException {
        assertEquals("", expected, actual, compareMode);
    }
    
    /**
     * Asserts that the JSONObject provided matches the expected JSONObject.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param message Error message to be displayed in case of assertion failure
     * @param expected Expected JSONObject
     * @param actual JSONObject to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String message, JSONObject expected, JSONObject actual, JSONCompareMode compareMode)
        throws JSONException {
        JSONCompareResult result = JSONCompare.compareJSON(expected, actual, compareMode);
        if (result.failed()) {
            throw new AssertionError(getCombinedMessage(message, result.getMessage()));
        }
    }

    /**
     * Asserts that the JSONObject provided does not match the expected JSONObject.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param expected Expected JSONObject
     * @param actual JSONObject to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(JSONObject expected, JSONObject actual, JSONCompareMode compareMode)
            throws JSONException {
        assertNotEquals("", expected, actual, compareMode);
    }
    
    /**
     * Asserts that the JSONObject provided does not match the expected JSONObject.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param message Error message to be displayed in case of assertion failure
     * @param expected Expected JSONObject
     * @param actual JSONObject to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String message, JSONObject expected, JSONObject actual, JSONCompareMode compareMode)
        throws JSONException {
        JSONCompareResult result = JSONCompare.compareJSON(expected, actual, compareMode);
        if (result.passed()) {
            throw new AssertionError(getCombinedMessage(message, result.getMessage()));
        }
    }

    /**
     * Asserts that the JSONArray provided matches the expected JSONArray.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param expected Expected JSONArray
     * @param actual JSONArray to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(JSONArray expected, JSONArray actual, boolean strict)
            throws JSONException {
        assertEquals("", expected, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }
    
    /**
     * Asserts that the JSONArray provided matches the expected JSONArray.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param message Error message to be displayed in case of assertion failure
     * @param expected Expected JSONArray
     * @param actual JSONArray to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String message, JSONArray expected, JSONArray actual, boolean strict)
        throws JSONException {
        assertEquals(message, expected, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }

    /**
     * Asserts that the JSONArray provided does not match the expected JSONArray.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param expected Expected JSONArray
     * @param actual JSONArray to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(JSONArray expected, JSONArray actual, boolean strict)
            throws JSONException {
        assertNotEquals(expected, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }
    
    /**
     * Asserts that the JSONArray provided does not match the expected JSONArray.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param message Error message to be displayed in case of assertion failure
     * @param expected Expected JSONArray
     * @param actual JSONArray to compare
     * @param strict Enables strict checking
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String message, JSONArray expected, JSONArray actual, boolean strict)
        throws JSONException {
        assertNotEquals(message, expected, actual, strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT);
    }

    /**
     * Asserts that the JSONArray provided matches the expected JSONArray.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param expected Expected JSONArray
     * @param actual JSONArray to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(JSONArray expected, JSONArray actual, JSONCompareMode compareMode)
            throws JSONException {
        assertEquals("", expected, actual, compareMode);
    }
    
    /**
     * Asserts that the JSONArray provided matches the expected JSONArray.  If it isn't it throws an
     * {@link AssertionError}.
     *
     * @param message Error message to be displayed in case of assertion failure
     * @param expected Expected JSONArray
     * @param actual JSONArray to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertEquals(String message, JSONArray expected, JSONArray actual, JSONCompareMode compareMode)
        throws JSONException {
        JSONCompareResult result = JSONCompare.compareJSON(expected, actual, compareMode);
        if (result.failed()) {
            throw new AssertionError(getCombinedMessage(message, result.getMessage()));
        }
    }

    /**
     * Asserts that the JSONArray provided does not match the expected JSONArray.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param expected Expected JSONArray
     * @param actual JSONArray to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(JSONArray expected, JSONArray actual, JSONCompareMode compareMode)
            throws JSONException {
        assertNotEquals("", expected, actual, compareMode);
    }
    
    /**
     * Asserts that the JSONArray provided does not match the expected JSONArray.  If it is it throws an
     * {@link AssertionError}.
     * 
     * @param message Error message to be displayed in case of assertion failure
     * @param expected Expected JSONArray
     * @param actual JSONArray to compare
     * @param compareMode Specifies which comparison mode to use
     * @throws JSONException JSON parsing error
     */
    public static void assertNotEquals(String message, JSONArray expected, JSONArray actual, JSONCompareMode compareMode)
        throws JSONException {
        JSONCompareResult result = JSONCompare.compareJSON(expected, actual, compareMode);
        if (result.passed()) {
            throw new AssertionError(getCombinedMessage(message, result.getMessage()));
        }
    }
    
    private static String getCombinedMessage(String message1, String message2) {
        String combinedMessage = "";
        
        if(message1 == null || "".equals(message1)) {
            combinedMessage = message2;
        } else {
            combinedMessage = message1 + " " + message2;
        }
        return combinedMessage;
    }
    
    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
    
    public static void main(String[] args) throws Exception {
    	String localfile = "localFile.json";
        String expected = readFileAsString(localfile);
        System.out.println(expected);
        
        String productionFile = "productionFile.json";
        String actual = readFileAsString(productionFile);
        System.out.println(actual);
    	
    	JSONAssert.assertEquals(expected, actual, new CustomComparator(JSONCompareMode.LENIENT,
    			   new Customization("age", (ct1, ct2) -> true),
    			   new Customization("hobbies", (c1, c2) -> true)));
    	
    	//JSONAssert.assertEquals(expected, actual, true);
	}
}
