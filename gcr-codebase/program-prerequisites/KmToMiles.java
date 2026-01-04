/*
     Convert Kilometers to Miles

Write a program that takes the distance in kilometers as input from the user
and converts it into miles using the formula:
Miles = Kilometers * 0.621371.

*/


import java.util.Scanner;

public class KmToMiles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Kilometers: ");
        float km = sc.nextFloat();

        float miles = km * 0.621371f;

        System.out.println("Miles = " + miles);

        sc.close();
    }
}
