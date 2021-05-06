package com.paypal.json.comparator;

import java.text.MessageFormat;

import org.json.JSONArray;
import org.json.JSONException;

import com.paypal.json.JSONCompareMode;
import com.paypal.json.JSONCompareResult;

public class ArraySizeComparator extends DefaultComparator {

	/**
	 * Create new ArraySizeComparator.
	 * 
	 * @param mode
	 *            comparison mode, has no impact on ArraySizeComparator but is
	 *            used by instance of superclass DefaultComparator to control
	 *            comparison of JSON items other than arrays.
	 */
	public ArraySizeComparator(JSONCompareMode mode) {
		super(mode);
	}

	/**
	 * Expected array should consist of either 1 or 2 integer values that define
	 * maximum and minimum valid lengths of the actual array. If expected array
	 * contains a single integer value, then the actual array must contain
	 * exactly that number of elements.
	 */
	@Override
	public void compareJSONArray(String prefix, JSONArray expected,
			JSONArray actual, JSONCompareResult result) throws JSONException {
		String arrayPrefix = prefix + "[]";
		if (expected.length() < 1 || expected.length() > 2) {
			result.fail(MessageFormat
					.format("{0}: invalid expectation: expected array should contain either 1 or 2 elements but contains {1} elements",
							arrayPrefix, expected.length()));
			return;
		}
		if (!(expected.get(0) instanceof Number)) {
			result.fail(MessageFormat
					.format("{0}: invalid expectation: {1}expected array size ''{2}'' not a number",
							arrayPrefix, (expected.length() == 1? "": "minimum "), expected.get(0)));
			return;
		}
		if ((expected.length() == 2 && !(expected.get(1) instanceof Number))) {
			result.fail(MessageFormat
					.format("{0}: invalid expectation: maximum expected array size ''{1}'' not a number",
							arrayPrefix, expected.get(1)));
			return;
		}
		int minExpectedLength = expected.getInt(0);
		if (minExpectedLength < 0) {
			result.fail(MessageFormat
					.format("{0}: invalid expectation: minimum expected array size ''{1}'' negative",
							arrayPrefix, minExpectedLength));
			return;
		}
		int maxExpectedLength = expected.length() == 2 ? expected.getInt(1)
				: minExpectedLength;
		if (maxExpectedLength < minExpectedLength) {
			result.fail(MessageFormat
					.format("{0}: invalid expectation: maximum expected array size ''{1}'' less than minimum expected array size ''{2}''",
							arrayPrefix, maxExpectedLength, minExpectedLength));
			return;
		}
		if (actual.length() < minExpectedLength
				|| actual.length() > maxExpectedLength) {
			result.fail(
					arrayPrefix,
					MessageFormat.format(
							"array size of {0}{1} elements",
							minExpectedLength,
							(expected.length() == 2 ? (" to " + maxExpectedLength)
									: "")),
					MessageFormat.format("{0} elements",
							actual.length()));
		}
	}

}
