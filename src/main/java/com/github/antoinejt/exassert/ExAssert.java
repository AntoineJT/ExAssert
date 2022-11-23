package com.github.antoinejt.exassert;

import com.github.antoinejt.exassert.exceptions.AssertionFailedException;
import com.github.antoinejt.exassert.exceptions.ExAssertInternalException;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExAssert {

  public static void exAssert(final boolean condition) {
    exAssert(condition, AssertionFailedException::new);
  }

  public static void exAssert(final boolean condition, final String message) {
    exAssert(condition, message, AssertionFailedException.class);
  }

  public static void exAssert(
      final boolean condition, final Class<? extends RuntimeException> exClass) {
    if (!condition) {
      Internals.throwException(exClass);
    }
  }

  public static void exAssert(
      final boolean condition,
      final String message,
      final Class<? extends RuntimeException> exClass) {
    if (!condition) {
      Internals.throwException(message, exClass);
    }
  }

  public static void exAssert(
      final boolean condition, final Supplier<? extends RuntimeException> supplier) {
    if (!condition) {
      throw supplier.get();
    }
  }

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
        final String message, final Class<? extends RuntimeException> exClass) {
      try {
        throw exClass.getConstructor(String.class).newInstance(message);
      } catch (final NoSuchMethodException
          | SecurityException
          | InstantiationException
          | IllegalAccessException
          | IllegalArgumentException
          | InvocationTargetException ex) {
        throwInternalException("String", ex);
      }
    }

    private static void throwException(final Class<? extends RuntimeException> exClass) {
      try {
        throw exClass.getConstructor().newInstance();
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
}
