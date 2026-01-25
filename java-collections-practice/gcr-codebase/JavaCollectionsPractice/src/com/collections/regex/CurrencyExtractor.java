package com.collections.regex;

import java.util.regex.*;

public class CurrencyExtractor {

    public static void main(String[] args) {
        String text = "The price is $45.99, and the discount is 10.50.";

        Pattern pattern = Pattern.compile("\\$?\\d+\\.\\d{2}");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}