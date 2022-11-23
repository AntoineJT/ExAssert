package com.github.antoinejt.exassert;

import static com.github.antoinejt.exassert.ExAssert.exAssert;

import com.github.antoinejt.exassert.exceptions.AssertionFailedException;
import com.github.antoinejt.exassert.exceptions.NumberSignException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Preconditions {

  private static void assertUnsigned(boolean condition, Object value) {
    exAssert(
        condition,
        "Number must be unsigned/positive. Found `" + value + "`.",
        NumberSignException.class);
  }

  public static void requiresUnsigned(int i) {
    assertUnsigned(i >= 0, i);
  }

  public static void requiresUnsigned(float f) {
    assertUnsigned(f >= 0, f);
  }

  public static void requiresUnsigned(double d) {
    assertUnsigned(d >= 0, d);
  }

  private static void assertStrictlyPositive(boolean condition, Object value) {
    exAssert(
        condition,
        "Number must be strictly positive. Found `" + value + "`.",
        AssertionFailedException.class);
  }

  public static void requiresStrictlyPositive(int i) {
    assertStrictlyPositive(i > 0, i);
  }

  public static void requiresStrictlyPositive(float f) {
    assertStrictlyPositive(f > 0, f);
  }

  public static void requiresStrictlyPositive(double d) {
    assertStrictlyPositive(d > 0, d);
  }
}
