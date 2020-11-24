package com.github.antoinejt.exassert;

import com.github.antoinejt.exassert.exceptions.AssertionFailedException;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

// S1148: Sonarlint - Use a logger instead of printStackTrace
@SuppressWarnings({"unused", "java:S1148"})
public class ExAssert {
    private ExAssert() {
        // hide default public ctor
    }

    public static void exAssert(boolean condition) {
        exAssert(condition, AssertionFailedException::new);
    }

    public static void exAssert(boolean condition, String message) {
        exAssert(condition, message, AssertionFailedException.class);
    }

    public static void exAssert(boolean condition, Class<? extends Exception> exClass) {
        if (condition)
            return;

        try {
            Constructor<? extends Exception> constructor = exClass.getConstructor();
            throw constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exAssert(boolean condition, String message, Class<? extends Exception> exClass) {
        if (condition)
            return;

        try {
            Constructor<? extends Exception> constructor = exClass.getConstructor(String.class);
            throw constructor.newInstance(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exAssert(boolean condition, Supplier<? extends Exception> supplier) {
        if (condition)
            return;

        try {
            throw supplier.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
