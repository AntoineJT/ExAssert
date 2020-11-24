package com.github.antoinejt.exassert;

import com.github.antoinejt.exassert.exceptions.AssertionFailedException;
import com.github.antoinejt.exassert.exceptions.NumberSignException;

import static com.github.antoinejt.exassert.ExAssert.exAssert;

@SuppressWarnings("unused")
public class Preconditions {
    private Preconditions() {
        // hides the public default ctor
    }

    private static void assertUnsigned(boolean condition) {
        exAssert(condition, "Number must be unsigned/positive", NumberSignException.class);
    }

    public static void requiresUnsigned(int i) {
        assertUnsigned(i >= 0);
    }

    public static void requiresUnsigned(float f) {
        assertUnsigned(f >= 0);
    }

    public static void requiresUnsigned(double d) {
        assertUnsigned(d >= 0);
    }

    private static void assertStrictlyPositive(boolean condition) {
        exAssert(condition, "Number must be strictly positive", AssertionFailedException.class);
    }

    public static void requiresStrictlyPositive(int i) {
        assertStrictlyPositive(i > 0);
    }

    public static void requiresStrictlyPositive(float f) {
        assertStrictlyPositive(f > 0);
    }

    public static void requiresStrictlyPositive(double d) {
        assertStrictlyPositive(d > 0);
    }
}
