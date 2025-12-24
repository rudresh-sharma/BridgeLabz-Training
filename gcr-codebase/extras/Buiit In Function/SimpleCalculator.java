/*


*/


import java.util.Scanner;
public class SimpleCalculator {
    public static void main(String[] args) {


	// Taking inputs
        Scanner in = new Scanner(System.in);
        System.out.println("Basic Calculator");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.print("Enter your choice (1-4): ");
        int choice = in.nextInt();
        System.out.print("Enter first number: ");
        double a = in.nextDouble();
        System.out.print("Enter second number: ");
        double b = in.nextDouble();
        double result = 0;


	// Calculating and printing result
        switch (choice) {
            case 1:
                result = add(a, b);
                break;
            case 2:
                result = subtract(a, b);
                break;
            case 3:
                result = multiply(a, b);
                break;
            case 4:
                result = divide(a, b);
                break;
            default:
                System.out.println("Invalid choice!");
                in.close();
                return;
        }

        System.out.println("Result = " + result);
        in.close();
    }

    // Method for Addition
    public static double add(double a, double b) {
        return a + b;
    }

    // Method for Subtraction
    public static double subtract(double a, double b) {
        return a - b;
    }

    // Method for Multiplication
    public static double multiply(double a, double b) {
        return a * b;
    }

    //Method for  Division
    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Cannot divide by zero!");
            return 0;
        }
        return a / b;
    }
}
