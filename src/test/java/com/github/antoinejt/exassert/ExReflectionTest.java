package com.github.antoinejt.exassert;

import static org.junit.jupiter.api.Assertions.*;

import com.github.antoinejt.exassert.utils.ExReflection;
import org.junit.jupiter.api.Test;

class ExReflectionTest {
  @Test
  void getPrivateStaticClassTest() {
    assertNotNull(ExReflection.getPrivateStaticClass(ExAssert.class, "Internals"));
    assertNull(ExReflection.getPrivateStaticClass(ExAssert.class, "NonExistingStaticInnerClass"));
  }
}
