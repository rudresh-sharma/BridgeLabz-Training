/*


Write a program to find vowels and consonants in a string and display the count of  Vowels and Consonants in the string
Hint => 
Create a method to check if the character is a vowel or consonant and return the result. The logic used here is as follows:
Convert the character to lowercase if it is an uppercase letter using the ASCII values of the characters
Check if the character is a vowel or consonant and return Vowel, Consonant, or Not a Letter
Create a Method to Method to find vowels and consonants in a string using charAt() method and finally return the count of vowels and consonants in an array
Finally, the main function takes user inputs, calls the user-defined methods, and displays the result. 




*/import java.util.Scanner;

public class VowelsAndConsonants {

    public static void main(String[] args) {

        // Taking input
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String strOriginal = in.nextLine();

        // DO NOT trim – spaces & symbols must be checked
        String str = strOriginal;

        int length = getLength(str);
        int[] vowelsConsont = getNumberOfVowelsAndConsonats(str, length);

        // Displaying no of vowels and consonants
        System.out.println("Number of Vowels     : " + vowelsConsont[0]);
        System.out.println("Number of Consonants : " + vowelsConsont[1]);

        in.close();
    }

    // Method to get length using manual way
    public static int getLength(String str) {
        int count = 0;

        try {
            int i = 0;
            while (true) {
                str.charAt(i);
                i++;
                count++;
            }
        } catch (Exception e) {
            return count;
        }
    }

    // Method to check is vowel or not
    public static boolean isVowel(char ch) {

        // Convert uppercase to lowercase using ASCII
        if (ch >= 'A' && ch <= 'Z') {
            ch = (char) (ch + 32);
        }

        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return true;
        }

        return false;
    }

    // Method to check isConsonat
    public static boolean isConsonant(char ch) {

        // Convert uppercase to lowercase using ASCII
        if (ch >= 'A' && ch <= 'Z') {
            ch = (char) (ch + 32);
        }

        // Check only letters
        if (ch >= 'a' && ch <= 'z') {
            return !isVowel(ch);
        }

        return false; // not a letter
    }

    // Method to get getNumberOfVowelsAndConsonats
    public static int[] getNumberOfVowelsAndConsonats(String str, int length) {

        int[] vowelsConstant = new int[2]; // [0] vowels, [1] consonants

        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);

            if (isVowel(ch)) {
                vowelsConstant[0]++;
            } else if (isConsonant(ch)) {
                vowelsConstant[1]++;
            }
        }

        return vowelsConstant;
    }
}
