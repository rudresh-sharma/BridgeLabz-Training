/*
     Calculate Average of Three Numbers

Write a program that takes three numbers as input from the user and prints
their average.

*/


import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        float a = sc.nextFloat();

        System.out.print("Enter second number: ");
        float b = sc.nextFloat();

        System.out.print("Enter third number: ");
        float c = sc.nextFloat();

        float avg = (a+b+c)/3;

        System.out.println("Average = " + avg);

        sc.close();
    }
}
