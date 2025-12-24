/*


1. Number Guessing Game:
○ Write a Java program where the user thinks of a number between 1 and 100, and
the computer tries to guess the number by generating random guesses.
○ The user provides feedback by indicating whether the guess is high, low, or
correct. The program should be modular, with different functions for generating
guesses, receiving user feedback, and determining the next guess.

*/

import java.util.Scanner;
import java.util.Random;

public class NumberGuessing{
    static Scanner in = new Scanner(System.in);
    static Random rand = new Random();
    public static void main(String[] args) {


	// Taking inputs
        System.out.println("Think of a number between 1 and 100.");
        System.out.println("I will try to guess it!");
        System.out.println("Enter:");
        System.out.println("H  -> if my guess is too High");
        System.out.println("L  -> if my guess is too Low");
        System.out.println("C  -> if my guess is Correct\n");

        int low = 1;
        int high = 100;
        char feedback;

	// generataing num according to guess feedback
        do {
            int guess = generateGuess(low, high);
            System.out.println("My guess is: " + guess);

            feedback = getUserFeedback();

            if (feedback == 'L') {
                low = guess + 1;
            }
            else if (feedback == 'H') {
                high = guess - 1;
            }

        } while (feedback != 'C');

        System.out.println("\n🎉 I guessed your number correctly!");
        in.close();
    }


	
    // Method to generate a random guess between low and high
    public static int generateGuess(int low, int high) {
        return rand.nextInt(high - low + 1) + low;
    }

    // Method to get feedback from user
    public static char getUserFeedback() {
        System.out.print("Enter your feedback (H/L/C): ");
        return in.next().toUpperCase().charAt(0);
    }
}
