package com.healthclinicapp.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Date helper — bridges java.time and java.sql.Date.
 * All methods are static; no instantiation needed.
 */
public class DateUtil {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private DateUtil() {}

    /** Parse "yyyy-MM-dd" string → java.sql.Date. Returns null for blank input. */
    public static Date toSqlDate(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) return null;
        try {
            return Date.valueOf(LocalDate.parse(dateStr.trim(), FMT));
        } catch (DateTimeParseException e) {
            System.out.println(ColorUtil.RED + "  Invalid date format. Expected yyyy-MM-dd." + ColorUtil.RESET);
            return null;
        }
    }

    /** java.sql.Date → "yyyy-MM-dd" string. */
    public static String toDisplayString(Date date) {
        return date == null ? "N/A" : date.toString();
    }

    /** Today as java.sql.Date. */
    public static Date todaySqlDate() {
        return Date.valueOf(LocalDate.now());
    }

    /** java.time.LocalDate → java.sql.Date. */
    public static Date fromLocalDate(LocalDate ld) {
        return ld == null ? null : Date.valueOf(ld);
    }

    /** java.sql.Date → java.time.LocalDate. */
    public static LocalDate toLocalDate(Date sqlDate) {
        return sqlDate == null ? null : sqlDate.toLocalDate();
    }

    /** Calculate age in years from a java.sql.Date. */
    public static int calculateAge(Date dob) {
        if (dob == null) return 0;
        return (int) java.time.temporal.ChronoUnit.YEARS.between(dob.toLocalDate(), LocalDate.now());
    }

    /** Check whether a string is a valid yyyy-MM-dd date. */
    public static boolean isValidDate(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) return false;
        try {
            LocalDate.parse(dateStr.trim(), FMT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
