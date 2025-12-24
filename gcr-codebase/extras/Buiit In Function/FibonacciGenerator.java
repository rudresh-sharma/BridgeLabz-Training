/*


Fibonacci Sequence Generator:
○ Write a program that generates the Fibonacci sequence up to a specified number
of terms entered by the user.
○ Organize the code by creating a function that calculates and prints the Fibonacci
sequence.

*/
import java.util.Scanner;
public class FibonacciGenerator {
    public static void main(String[] args) {


	// Taking inputs
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of terms: ");
        long n = in.nextLong();


	// Printing n terms 
        generateFibonacci(n);

        in.close();
    }

    // Method to generate and print Fibonacci sequence
    public static void generateFibonacci(long n) {

        long a = 0, b = 1;

        System.out.println("\nFibonacci Sequence:");

        for (int i = 1; i <= n; i++) {
            System.out.print(a + " ");

            long next = a + b;
            a = b;
            b = next;
        }
    }
}
