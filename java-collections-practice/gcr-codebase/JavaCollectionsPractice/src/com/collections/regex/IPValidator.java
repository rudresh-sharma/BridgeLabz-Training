package com.collections.regex;

import java.util.Scanner;

public class IPValidator {

    public static boolean isValidIP(String ip) {
        return ip.matches("((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter IP address: ");
        String ip = sc.nextLine();

        System.out.println(isValidIP(ip) ? "Valid" : "Invalid");
        sc.close();
    }
}