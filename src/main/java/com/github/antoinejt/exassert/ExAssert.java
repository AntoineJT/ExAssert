package com.github.antoinejt.exassert;

import com.github.antoinejt.exassert.exceptions.AssertionFailedException;
import com.github.antoinejt.exassert.exceptions.ExAssertInternalException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

// S1148: Sonarlint - Use a logger instead of printStackTrace
@SuppressWarnings("java:S1148")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExAssert {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class Internals {

        protected static void throwInternalException(String ctorParameters, Exception ex) throws ExAssertInternalException {
            throw new ExAssertInternalException(
                    "Please check your assertions are throwing exceptions implementing ctor(" + ctorParameters + ").\n"
                    + "Here is the internal exception: \n" 
                    + ex.getMessage());
        }
        
        // S1130: Sonarlint - Should not throw subclass exceptions of already specified exceptions
        // I want to be able to catch this particular exception, here the internal one
        // S112: Sonarlint - Generic exceptions should never be thrown
        // I throw a subclass of exception via Reflection, I must throw Exception then
        @SuppressWarnings({"java:S1130", "java:S112"})
        protected static void throwException(String message, Class<? extends Exception> exClass) throws ExAssertInternalException, Exception {
            try {
                Constructor<? extends Exception> constructor = exClass.getConstructor(String.class);
                throw constructor.newInstance(message);
            } catch (NoSuchMethodException | SecurityException
                    | InstantiationException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException ex) {
                throwInternalException("String", ex);
            }
        }
        
        // S1130: Sonarlint - Should not throw subclass exceptions of already specified exceptions
        // I want to be able to catch this particular exception, here the internal one
        // S112: Sonarlint - Generic exceptions should never be thrown
        // I throw a subclass of exception via Reflection, I must throw Exception then
        @SuppressWarnings({"java:S1130", "java:S112"})
        protected static void throwException(Class<? extends Exception> exClass) throws ExAssertInternalException, Exception {
            try {
                Constructor<? extends Exception> constructor = exClass.getConstructor();
                throw constructor.newInstance();
            } catch (NoSuchMethodException | SecurityException
                    | InstantiationException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException ex) {
                throwInternalException("", ex);
            }
        }
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
            Internals.throwException(exClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exAssert(boolean condition, String message, Class<? extends Exception> exClass) {
        if (condition)
            return;
        
        try {
            Internals.throwException(message, exClass);
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
