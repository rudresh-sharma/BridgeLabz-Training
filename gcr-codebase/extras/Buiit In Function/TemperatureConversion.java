/*


Temperature Converter:
○ Write a program that converts temperatures between Fahrenheit and Celsius. ○
The program should have separate functions for converting from Fahrenheit to
Celsius and from Celsius to Fahrenheit.


*/


import java.util.Scanner;

public class TemperatureConversion {
	public static void main(String[] args) {

	// Taking inputs
        Scanner in = new Scanner(System.in);
        System.out.println("Temperature Converter");
        System.out.println("1. Fahrenheit to Celsius");
        System.out.println("2. Celsius to Fahrenheit");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = in.nextInt();


	// Printing temps according to choice
        if (choice == 1) {
            System.out.print("Enter temperature in Fahrenheit: ");
            double f = in.nextDouble();
            double c = fahrenheitToCelsius(f);
            System.out.println("Celsius = " + c);
        }
        else if (choice == 2) {
            System.out.print("Enter temperature in Celsius: ");
            double c = in.nextDouble();
            double f = celsiusToFahrenheit(c);
            System.out.println("Fahrenheit = " + f);
        }
        else {
            System.out.println("Invalid choice!");
        }

        in.close();
    }

    // Method to convert Fahrenheit to Celsius
    public static double fahrenheitToCelsius(double f) {
        return (f - 32) * 5 / 9;
    }

    // Method to convert Celsius to Fahrenheit
    public static double celsiusToFahrenheit(double c) {
        return (c * 9 / 5) + 32;
    }
}
