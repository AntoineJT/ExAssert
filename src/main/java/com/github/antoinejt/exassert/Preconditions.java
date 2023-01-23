package com.github.antoinejt.exassert;

import static com.github.antoinejt.exassert.ExAssert.exAssert;

import com.github.antoinejt.exassert.exceptions.AssertionFailedException;
import com.github.antoinejt.exassert.exceptions.NumberSignException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Preconditions {

  public static void requiresUnsigned(final int i) {
    Internals.assertUnsigned(i >= 0, i);
  }

  public static void requiresUnsigned(final float f) {
    Internals.assertUnsigned(f >= 0, f);
  }

  public static void requiresUnsigned(final double d) {
    Internals.assertUnsigned(d >= 0, d);
  }

  public static void requiresStrictlyPositive(final int i) {
    Internals.assertStrictlyPositive(i > 0, i);
  }

  public static void requiresStrictlyPositive(final float f) {
    Internals.assertStrictlyPositive(f > 0, f);
  }

  public static void requiresStrictlyPositive(final double d) {
    Internals.assertStrictlyPositive(d > 0, d);
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  private static final class Internals {

    private static void assertUnsigned(final boolean condition, @NonNull final Object value) {
      final String reason = String.format("Number must be unsigned/positive. Found `%s`.", value);
      exAssert(condition, reason, NumberSignException.class);
    }

    private static void assertStrictlyPositive(
        final boolean condition, @NonNull final Object value) {
      final String reason = String.format("Number must be strictly positive. Found `%s`.", value);
      exAssert(condition, reason, AssertionFailedException.class);
    }
  }
}
