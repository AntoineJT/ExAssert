package com.github.antoinejt.exassert;

import com.github.antoinejt.exassert.exceptions.AssertionFailedException;
import com.github.antoinejt.exassert.exceptions.ExAssertInternalException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExAssert {

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  private static final class Internals {

    protected static void throwInternalException(String ctorParameters, Exception ex)
        throws ExAssertInternalException {
      throw new ExAssertInternalException(
          "Please check your assertions are throwing exceptions implementing ctor("
              + ctorParameters
              + ").\n"
              + "Here is the internal exception: \n"
              + ex.getMessage());
    }

    protected static void throwException(String message, Class<? extends Exception> exClass)
        throws ExAssertInternalException, Exception {
      try {
        Constructor<? extends Exception> constructor = exClass.getConstructor(String.class);
        throw constructor.newInstance(message);
      } catch (NoSuchMethodException
          | SecurityException
          | InstantiationException
          | IllegalAccessException
          | IllegalArgumentException
          | InvocationTargetException ex) {
        throwInternalException("String", ex);
      }
    }

    protected static void throwException(Class<? extends Exception> exClass)
        throws ExAssertInternalException, Exception {
      try {
        Constructor<? extends Exception> constructor = exClass.getConstructor();
        throw constructor.newInstance();
      } catch (NoSuchMethodException
          | SecurityException
          | InstantiationException
          | IllegalAccessException
          | IllegalArgumentException
          | InvocationTargetException ex) {
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
    if (condition) return;

    try {
      Internals.throwException(exClass);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void exAssert(
      boolean condition, String message, Class<? extends Exception> exClass) {
    if (condition) return;

    try {
      Internals.throwException(message, exClass);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void exAssert(boolean condition, Supplier<? extends Exception> supplier) {
    if (condition) return;

    try {
      throw supplier.get();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
