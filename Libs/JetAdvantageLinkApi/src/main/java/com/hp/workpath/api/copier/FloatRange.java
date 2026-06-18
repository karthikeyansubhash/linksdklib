// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.copier;

/**
 * Range for retrieving capability of float parameters.
 *
 * @since API 3
 */
public class FloatRange {
    float mLowerBound;
    float mUpperBound;

    FloatRange(float lowerBound, float upperBound) {
        this.mLowerBound = lowerBound;
        this.mUpperBound = upperBound;
    }

    /**
     * Retrieves minimum supported value.
     *
     * @return minimum
     * @since API 3
     */
    public float getLowerBound() {
        return mLowerBound;
    }

    /**
     * Retrieves maximum supported value.
     *
     * @return maximum
     * @since API 3
     */
    public float getUpperBound() {
        return mUpperBound;
    }

    /**
     * Validates the provided value against this range.
     *
     * @return true if value is within the range.
     * @since API 3
     */
    public boolean validate(float value) {
        return Float.compare(value, mLowerBound) >= 0 && Float.compare(value, mUpperBound) <= 0 && Float.compare(mLowerBound, mUpperBound) <= 0;
    }

    /**
     * @hide This is hidden because it should be understood without documenting in the javadoc.
     */
    @Override
    public String toString() {
        return "[" + mLowerBound + ", " + mUpperBound + "]";
    }
}
