package com.collections.regex;

import java.util.Scanner;

public class BadWordCensor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter sentence: ");
        String text = sc.nextLine();

        String badWords = "\\b(damn|stupid)\\b";
        System.out.println(text.replaceAll(badWords, "****"));
        sc.close();
    }
}