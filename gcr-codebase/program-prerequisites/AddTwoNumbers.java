// package javaFundamentals.week1.day1.Assisted.Question2;

import java.util.Scanner;

public class AddTwoNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter first number");

        int num1 = sc.nextInt();

        System.out.println("Enter second number");

        int num2 = sc.nextInt();

        int sum = num1 + num2;

        System.out.println("Sum = " + sum);

        sc.close();
    }
}
