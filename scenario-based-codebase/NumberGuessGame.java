/*

7. The Number Guessing Game 🎲
A game asks the player to guess a number between 1 and 100.
Core Java Scenario Based Problem Statements
● Use do-while loop.
● Give hints like "Too high" or "Too low".
● Count attempts and exit after 5 wrong tries.




*/
//created class named NumberGuessGame
import java.util.*;
public class NumberGuessGame {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        Random random=new Random();
        int secretNumber=random.nextInt(100) + 1;  
        int guess;
        int attempts = 0;
        int MAX_ATTEMPTS = 5;
		
		//used do while loop for guessing the number
        do {
            System.out.print("Enter your guess between 1 and 100: ");
            guess = input.nextInt();
            attempts++;

            if (guess == secretNumber) {
                System.out.println("Correct! You guessed the number in " + attempts + " attempts.");
                break;
            }
			else if (guess > secretNumber) {
                System.out.println("Too high! Try again.");
            } 
			else {
                System.out.println("Too low! Try again.");
            }

            if (attempts == MAX_ATTEMPTS) {
                System.out.println("You've used all attempts.");
                System.out.println("The correct number was: " + secretNumber);
            }

        }
		while(attempts < MAX_ATTEMPTS);
    }
}
