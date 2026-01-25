package com.collections.regex;

import java.util.Scanner;

public class CreditCardValidator {

    public static boolean isValidCard(String card) {
        return card.matches("4\\d{15}|5\\d{15}");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter card number: ");
        String card = sc.nextLine();

        System.out.println(isValidCard(card) ? "Valid" : "Invalid");
        sc.close();
    }
}