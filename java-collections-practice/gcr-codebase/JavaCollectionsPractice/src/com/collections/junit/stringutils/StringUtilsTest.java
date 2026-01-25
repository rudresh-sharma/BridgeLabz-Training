package com.collections.junit.stringutils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StringUtilsTest {

    StringUtils utils = new StringUtils();

    @Test
    void testReverse() {
        assertEquals("avaJ", utils.reverse("Java"));
    }

    @Test
    void testPalindrome() {
        assertTrue(utils.isPalindrome("madam"));
        assertFalse(utils.isPalindrome("java"));
    }

    @Test
    void testUpperCase() {
        assertEquals("JAVA", utils.toUpperCase("java"));
    }
}