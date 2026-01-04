/*

Remove a Specific Character from a String
Problem:
Write a Java program to remove all occurrences of a specific character from a string.
Example Input:
String: "Hello World"
Character to Remove: 'l'

Expected Output:
Modified String: "Heo Word"

*/



import java.util.Scanner;

public class RemoveSpecificCharacter {
    public static void main(String[] args) {


	// Taking inputs
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String str = in.nextLine();
        System.out.print("Enter the character to remove: ");
        char removeChar = in.next().charAt(0);
	
	// Modifying string
        String result = "";

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch != removeChar) {
                result = result + ch;
            }
        }

        System.out.println("Modified String: \"" + result + "\"");

        in.close();
    }
}
