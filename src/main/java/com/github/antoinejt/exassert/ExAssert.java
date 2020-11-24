package com.github.antoinejt.exassert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("unused")
public class ExAssert {
    private ExAssert() {
        // hide default public ctor
    }

    @SuppressWarnings("unused")
    public static void exAssert(boolean condition) {
        exAssert(condition, Exception.class);
    }

    public static void exAssert(boolean condition, String message) {
        exAssert(condition, message, Exception.class);
    }

    public static void exAssert(boolean condition, Class<? extends Exception> exClass) {
        if (condition)
            return;

        try {
            Constructor<? extends Exception> constructor = exClass.getConstructor();
            throw constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Assertion failed!");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void exAssert(boolean condition, String message, Class<? extends Exception> exClass) {
        if (!condition) {
            try {
                Constructor<? extends Exception> constructor = exClass.getConstructor(String.class);
                throw constructor.newInstance(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
