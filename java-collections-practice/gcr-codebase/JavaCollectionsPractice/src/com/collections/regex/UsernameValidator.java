package com.collections.regex;
import java.util.Scanner;

public class UsernameValidator {

    public static boolean isValidUsername(String username) {
        return username.matches("^[A-Za-z][A-Za-z0-9_]{4,14}$");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter UserName: ");
        String username = sc.nextLine();

        if (isValidUsername(username)) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
        sc.close();
    }
}