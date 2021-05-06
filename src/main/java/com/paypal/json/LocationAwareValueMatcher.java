/**
 * 
 */
package com.paypal.json;

public interface LocationAwareValueMatcher<T> extends ValueMatcher<T> {

	boolean equal(String prefix, T actual, T expected, JSONCompareResult result) throws ValueMatcherException;
}
