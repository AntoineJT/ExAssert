package com.github.antoinejt.exassert;

import com.github.antoinejt.exassert.utils.ExReflection;
import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ExAssertTest {
  private static Class<?> exAssertInternals;
  private static Method throwExceptionMsg, throwException;

  @BeforeAll
  static void accessInternals() throws NoSuchMethodException {
    exAssertInternals = ExReflection.getPrivateStaticClass(ExAssert.class, "Internals");
    assert exAssertInternals != null;

    throwExceptionMsg =
        exAssertInternals.getDeclaredMethod("throwException", String.class, Class.class);
    throwExceptionMsg.setAccessible(true);

    throwException = exAssertInternals.getDeclaredMethod("throwException", Class.class);
    throwException.setAccessible(true);
  }

  @Test
  void test() {
    /*
    assertThrows(AssertionFailedException.class, () -> throwException.invoke(null, AssertionFailedException.class));
    assertThrows(AssertionFailedException.class, () -> throwExceptionMsg.invoke(null, "Test", AssertionFailedException.class));
    */
  }
}
