package com.collections.regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CapitalizedWordsExtractor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter sentence: ");
        String text = sc.nextLine();

        Pattern pattern = Pattern.compile("\\b[A-Z][a-z]*\\b");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        sc.close();
    }
}