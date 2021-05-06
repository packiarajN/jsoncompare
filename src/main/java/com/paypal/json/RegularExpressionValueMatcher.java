package com.paypal.json;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.paypal.json.ValueMatcher;


public class RegularExpressionValueMatcher<T> implements ValueMatcher<T> {

	private final Pattern expectedPattern;

	/**
	 * Create RegularExpressionValueMatcher in which the pattern the actual
	 * value must match with be specified dynamically from the expected string
	 * passed to this matcher in the equals method.
	 */
	public RegularExpressionValueMatcher() {
		this(null);
	}

	public RegularExpressionValueMatcher(String pattern) throws IllegalArgumentException {
		try {
			expectedPattern = pattern == null ? null : Pattern.compile(pattern);
		}
		catch (PatternSyntaxException e) {
			throw new IllegalArgumentException("Constant expected pattern invalid: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean equal(T actual, T expected) {
		String actualString = actual.toString();
		String expectedString = expected.toString();
		try {
			Pattern pattern = isStaticPattern() ? expectedPattern : Pattern
					.compile(expectedString);
			if (!pattern.matcher(actualString).matches()) {
				throw new ValueMatcherException(getPatternType() + " expected pattern did not match value", pattern.toString(), actualString);
			}
		}
		catch (PatternSyntaxException e) {
			throw new ValueMatcherException(getPatternType() + " expected pattern invalid: " + e.getMessage(), e, expectedString, actualString);
		}
		return true;
	}

	private boolean isStaticPattern() {
		return expectedPattern != null;
	}

	private String getPatternType() {
		return isStaticPattern()? "Constant": "Dynamic";
	}
}
