package com.github.antoinejt.exassert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.github.antoinejt.exassert.utils.ExReflection;

class ExReflectionTest {
    @Test
    void getPrivateStaticClassTest() {
        assertNotNull(ExReflection.getPrivateStaticClass(ExAssert.class, "Internals"));
        assertNull(ExReflection.getPrivateStaticClass(ExAssert.class, "NonExistingStaticInnerClass"));
    }
}
