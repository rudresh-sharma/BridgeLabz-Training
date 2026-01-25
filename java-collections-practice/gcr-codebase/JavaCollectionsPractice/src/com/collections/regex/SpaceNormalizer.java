package com.collections.regex;

import java.util.Scanner;

public class SpaceNormalizer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter sentence: ");
        String text = sc.nextLine();

        System.out.println(text.replaceAll("\\s+", " "));
        sc.close();
    }
}