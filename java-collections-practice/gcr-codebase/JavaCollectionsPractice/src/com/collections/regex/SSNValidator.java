package com.collections.regex;

import java.util.Scanner;

public class SSNValidator {

    public static boolean isValidSSN(String ssn) {
        return ssn.matches("\\d{3}-\\d{2}-\\d{4}");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter SSN: ");
        String ssn = sc.nextLine();

        System.out.println(isValidSSN(ssn) ? "Valid" : "Invalid");
        sc.close();
    }
}