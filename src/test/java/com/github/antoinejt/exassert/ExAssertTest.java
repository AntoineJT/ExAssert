package com.github.antoinejt.exassert;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.antoinejt.exassert.utils.ExReflection;

class ExAssertTest {
    private static Class<?> exAssertInternals;
    
    @BeforeAll
    static void accessInternals() {
        exAssertInternals = ExReflection.getPrivateStaticClass(ExAssert.class, "Internals");
    }
    
    @Test
    void test() {
        
        // assertThrows(AssertionFailedException.class, () -> ExAssert.exAssert(1 > 2));
    }
}
