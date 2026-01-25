package com.collections.junit.dateformatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    public String formatDate(String inputDate) {
        LocalDate date = LocalDate.parse(inputDate);
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}