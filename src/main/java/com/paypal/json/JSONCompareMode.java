package com.paypal.json;

public enum JSONCompareMode {
    /**
     * Strict checking.  Not extensible, and strict array ordering.
     */
    STRICT(false, true),
    /**
     * Lenient checking.  Extensible, and non-strict array ordering.
     */
    LENIENT(true, false),
    /**
     * Non-extensible checking.  Not extensible, and non-strict array ordering.
     */
    NON_EXTENSIBLE(false, false),
    /**
     * Strict order checking.  Extensible, and strict array ordering.
     */
    STRICT_ORDER(true, true);

    private final boolean _extensible;
    private final boolean _strictOrder;

    JSONCompareMode(boolean extensible, boolean strictOrder) {
        _extensible = extensible;
        _strictOrder = strictOrder;
    }

    /**
     * Is extensible
     * @return True if results can be extended from what's expected, otherwise false.
     */
    public boolean isExtensible() {
        return _extensible;
    }

    /**
     * Strict order required
     * @return True if results require strict array ordering, otherwise false.
     */
    public boolean hasStrictOrder() {
        return _strictOrder;
    }
    
    /**
     * Get the equivalent {@code JSONCompareMode} with or without strict ordering.
     * 
     * @param strictOrdering if true, requires strict ordering of array elements
     * @return the equivalent {@code JSONCompareMode}
     */
    public JSONCompareMode withStrictOrdering(boolean strictOrdering) {
        if (strictOrdering) {
            return isExtensible() ? STRICT_ORDER : STRICT;
        } else {
            return isExtensible() ? LENIENT : NON_EXTENSIBLE;
        }
    }

    /**
     * Get the equivalent {@code JSONCompareMode} with or without extensibility.
     * 
     * @param extensible if true, allows keys in actual that don't appear in expected
     * @return the equivalent {@code JSONCompareMode}
     */
    public JSONCompareMode withExtensible(boolean extensible) {
        if (extensible) {
            return hasStrictOrder() ? STRICT_ORDER : LENIENT;
        } else {
            return hasStrictOrder() ? STRICT : NON_EXTENSIBLE;
        }
    }
}
