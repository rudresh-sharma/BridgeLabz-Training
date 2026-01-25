package com.collections.junit.dateformatter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DateFormatterTest {

    DateFormatter formatter = new DateFormatter();

    @Test
    void testValidDate() {
        assertEquals("25-01-2026", formatter.formatDate("2026-01-25"));
    }

    @Test
    void testInvalidDate() {
        assertThrows(Exception.class, () -> formatter.formatDate("25-01-2026"));
    }
}