/*

3. Prime Number Checker:
○ Create a program that checks whether a given number is a prime number. ○
The program should use a separate function to perform the prime check and
return the result.

*/



import java.util.Scanner;
public class NumberChecker {
    public static void main(String[] args) {
        
	// Takinginputs
	Scanner in = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = in.nextInt();


	// Printing num is prome or nt
        boolean result = isPrime(number);
        if (result) {
            System.out.println(number + " is a Prime number");
        } else {
            System.out.println(number + " is NOT a Prime number");
        }

        in.close();
    }

    // Method to check if a number is prime
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;   // Not prime
            }
        }

        return true;  // Prime
    }
}
