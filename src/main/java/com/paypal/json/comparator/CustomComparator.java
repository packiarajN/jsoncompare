package com.paypal.json.comparator;

import org.json.JSONException;

import com.paypal.json.Customization;
import com.paypal.json.JSONCompareMode;
import com.paypal.json.JSONCompareResult;
import com.paypal.json.ValueMatcherException;

import java.util.Arrays;
import java.util.Collection;

public class CustomComparator extends DefaultComparator {

    private final Collection<Customization> customizations;

    public CustomComparator(JSONCompareMode mode,  Customization... customizations) {
        super(mode);
        this.customizations = Arrays.asList(customizations);
    }

    @Override
    public void compareValues(String prefix, Object expectedValue, Object actualValue, JSONCompareResult result) throws JSONException {
        Customization customization = getCustomization(prefix);
        if (customization != null) {
            try {
    	        if (!customization.matches(prefix, actualValue, expectedValue, result)) {
                    result.fail(prefix, expectedValue, actualValue);
                }
            }
            catch (ValueMatcherException e) {
                result.fail(prefix, e);
            }
        } else {
            super.compareValues(prefix, expectedValue, actualValue, result);
        }
    }

    private Customization getCustomization(String path) {
        for (Customization c : customizations)
            if (c.appliesToPath(path))
                return c;
        return null;
    }
}
