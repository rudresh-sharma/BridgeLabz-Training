package com.collections.junit.userregistration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UserRegistrationTest {

    UserRegistration reg = new UserRegistration();

    @Test
    void testValidRegistration() {
        assertDoesNotThrow(() ->
            reg.registerUser("username", "user@mail.com", "Password1")
        );
    }

    @Test
    void testInvalidRegistration() {
        assertThrows(IllegalArgumentException.class, () ->
            reg.registerUser("usr", "invalidmail", "pass")
        );
    }
}