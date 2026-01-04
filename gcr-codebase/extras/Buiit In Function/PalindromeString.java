/*

5. Palindrome Checker:
○ Write a program that checks if a given string is a palindrome (a word, phrase, or
sequence that reads the same backward as forward).
○ Break the program into functions for input, checking the palindrome condition,
and displaying the result.


*/


import java.util.Scanner;
public class PalindromeChecker {
    public static void main(String[] args) {


	// Printing is Palidrome
        String text = getInput();         
        boolean result = isPalindrome(text);
        displayResult(text, result);           



   }

    // Method to take input from user
    public static String getInput() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a string: ");
        return in.nextLine();
    }

    // Method to check if string is palindrome
    public static boolean isPalindrome(String str) {

        str = str.toLowerCase().replaceAll("\\s+", ""); // remove spaces & make lowercase
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    // Method to display result
    public static void displayResult(String text, boolean result) {
        if (result) {
            System.out.println("\"" + text + "\" is a Palindrome");
        } else {
            System.out.println("\"" + text + "\" is NOT a Palindrome");
        }
    }
}
