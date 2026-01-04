/*

Factorial Using Recursion:
○ Write a program that calculates the factorial of a number using a recursive
function.
○ Include modular code to separate input, calculation, and output processes.

*/



import java.util.Scanner;
public class FactorialUsingRecursion {
    public static void main(String[] args) {


	// Taking input and printing its factorial
        int number = getInput();              
        long result = findFactorial(number);  
        displayResult(number, result);       
    }

    // Method to take input
    public static int getInput() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a number: ");
        return in.nextInt();
    }

    // Recursive method to find factorial
    public static long findFactorial(int n) {

        if (n == 0 || n == 1) {
            return 1;  // Base case
        }

        return n * findFactorial(n - 1); // Recursive call
    }

    // Method to display result
    public static void displayResult(int number, long result) {
        System.out.println("Factorial of " + number + " is: " + result);
    }
}
