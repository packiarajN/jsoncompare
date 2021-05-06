

package com.paypal.json.comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.paypal.json.JSONCompareResult;

public interface JSONComparator {

    /**
     * Compares two {@link JSONObject}s and returns the result of the comparison in a {@link JSONCompareResult} object.
     *
     * @param expected the expected JSON object
     * @param actual   the actual JSON object
     * @return the result of the comparison
     * @throws JSONException JSON parsing error
     */
    JSONCompareResult compareJSON(JSONObject expected, JSONObject actual) throws JSONException;

    /**
     * Compares two {@link JSONArray}s and returns the result of the comparison in a {@link JSONCompareResult} object.
     *
     * @param expected the expected JSON array
     * @param actual   the actual JSON array
     * @return the result of the comparison
     * @throws JSONException JSON parsing error
     */
    JSONCompareResult compareJSON(JSONArray expected, JSONArray actual) throws JSONException;

    /**
     * Compares two {@link JSONObject}s on the provided path represented by {@code prefix} and
     * updates the result of the comparison in the {@code result} {@link JSONCompareResult} object.
     *
     * @param prefix   the path in the json where the comparison happens
     * @param expected the expected JSON object
     * @param actual   the actual JSON object
     * @param result   stores the actual state of the comparison result
     * @throws JSONException JSON parsing error
     */
    void compareJSON(String prefix, JSONObject expected, JSONObject actual, JSONCompareResult result) throws JSONException;

    /**
     * Compares two {@link Object}s on the provided path represented by {@code prefix} and
     * updates the result of the comparison in the {@code result} {@link JSONCompareResult} object.
     *
     * @param prefix        the path in the json where the comparison happens
     * @param expectedValue the expected value
     * @param actualValue   the actual value
     * @param result        stores the actual state of the comparison result
     * @throws JSONException JSON parsing error
     */
    void compareValues(String prefix, Object expectedValue, Object actualValue, JSONCompareResult result) throws JSONException;

    /**
     * Compares two {@link JSONArray}s on the provided path represented by {@code prefix} and
     * updates the result of the comparison in the {@code result} {@link JSONCompareResult} object.
     *
     * @param prefix   the path in the json where the comparison happens
     * @param expected the expected JSON array
     * @param actual   the actual JSON array
     * @param result   stores the actual state of the comparison result
     * @throws JSONException JSON parsing error
     */
    void compareJSONArray(String prefix, JSONArray expected, JSONArray actual, JSONCompareResult result) throws JSONException;
}
