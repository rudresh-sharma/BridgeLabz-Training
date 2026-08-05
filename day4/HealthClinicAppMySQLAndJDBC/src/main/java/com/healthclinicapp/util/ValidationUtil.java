package com.healthclinicapp.util;

/**
 * Input validation helpers.
 * All methods are static; no instantiation needed.
 */
public class ValidationUtil {

    private ValidationUtil() {}

    /** Escape special LIKE characters in user-supplied search terms. */
    public static String sanitizeLike(String input) {
        if (input == null) return "";
        return input
            .replace("\\", "\\\\")
            .replace("%",  "\\%")
            .replace("_",  "\\_");
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean isValidBloodGroup(String bg) {
        return bg != null && bg.matches("^(A|B|AB|O)[+-]$");
    }

    public static boolean isNotBlank(String s) {
        return s != null && !s.isBlank();
    }

    public static boolean isPositive(double d) { return d > 0; }
    public static boolean isNonNegative(double d) { return d >= 0; }
}
