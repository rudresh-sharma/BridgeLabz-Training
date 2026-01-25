package com.collections.junit.exceptiontest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ExceptionTest {

    @Test
    void testArithmeticException() {
        assertThrows(ArithmeticException.class, () -> {
            int a = 10 / 0;
        });
    }
}