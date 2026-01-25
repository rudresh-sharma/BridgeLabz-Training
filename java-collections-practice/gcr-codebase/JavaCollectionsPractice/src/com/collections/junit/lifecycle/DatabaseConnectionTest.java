package com.collections.junit.lifecycle;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class DatabaseConnectionTest {

    DatabaseConnection db;

    @BeforeEach
    void setup() {
        db = new DatabaseConnection();
        db.connect();
    }

    @AfterEach
    void cleanup() {
        db.disconnect();
    }

    @Test
    void testConnectionStatus() {
        assertTrue(db.isConnected());
    }
}