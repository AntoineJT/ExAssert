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

    public static void throwInternalException(final String ctorParameters, final Exception ex)
        throws ExAssertInternalException {
      final String reason =
          String.format(
              "Please check your assertions are throwing exceptions implementing ctor(%s).%nHere is the internal exception: %n%s",
              ctorParameters, ex.getMessage());
      throw new ExAssertInternalException(reason);
    }

    public static void throwInternalException(final Exception ex) throws ExAssertInternalException {
      throwInternalException("", ex);
    }

    public static void throwException(
        final String message, final Class<? extends Exception> exClass) throws Exception {
      try {
        final Constructor<? extends Exception> constructor = exClass.getConstructor(String.class);
        throw constructor.newInstance(message);
      } catch (final NoSuchMethodException
          | SecurityException
          | InstantiationException
          | IllegalAccessException
          | IllegalArgumentException
          | InvocationTargetException ex) {
        throwInternalException("String", ex);
      }
    }

    private static void throwException(final Class<? extends Exception> exClass) throws Exception {
      try {
        final Constructor<? extends Exception> constructor = exClass.getConstructor();
        throw constructor.newInstance();
      } catch (final NoSuchMethodException
          | SecurityException
          | InstantiationException
          | IllegalAccessException
          | IllegalArgumentException
          | InvocationTargetException ex) {
        throwInternalException(ex);
      }
    }
  }

  public static void exAssert(final boolean condition) {
    exAssert(condition, AssertionFailedException::new);
  }

  public static void exAssert(final boolean condition, final String message) {
    exAssert(condition, message, AssertionFailedException.class);
  }

  public static void exAssert(final boolean condition, final Class<? extends Exception> exClass) {
    if (condition) {
      return;
    }

    try {
      Internals.throwException(exClass);
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  public static void exAssert(
      final boolean condition, final String message, final Class<? extends Exception> exClass) {
    if (condition) {
      return;
    }

    try {
      Internals.throwException(message, exClass);
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  public static void exAssert(
      final boolean condition, final Supplier<? extends Exception> supplier) {
    if (condition) {
      return;
    }

    try {
      throw supplier.get();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }
}
