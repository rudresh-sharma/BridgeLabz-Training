/*

Write a program to trim the leading and trailing spaces from a string using the charAt() method 
Hint => 
Create a method to trim the leading and trailing spaces from a string using the charAt() method. Inside the method run a couple of loops to trim leading and trailing spaces and determine the starting and ending points with no spaces. Return the start point and end point in an array
Write a method to create a substring from a string using the charAt() method with the string, start, and end index as the parameters
Write a method to compare two strings using the charAt() method and return a boolean result
The main function calls the user-defined trim and substring methods to get the text after trimming the leading and trailing spaces. Post that use the String built-in method trim() to trim spaces and compare the two strings. And finally display the result


*/
import java.util.Scanner;

public class TrimSpace {

    public static void main(String[] args) {

        // Taking input
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String str = in.nextLine();

        // Printing is trim by manual and method is same
        int[] spaceEndPoints = getSpaceEndPoints(str);
        String trimmedString = createSubstring(str, spaceEndPoints[0], spaceEndPoints[1]);
        String builtInTrim = str.trim();
        boolean result = compareStrings(trimmedString, builtInTrim);
        System.out.println("Trimmed string using logic : \"" + trimmedString + "\"");
        System.out.println("Trimmed string using trim(): \"" + builtInTrim + "\"");
        System.out.println("Are both strings equal? " + result);

        in.close();
    }

    // Method to find starting and ending index without spaces
    public static int[] getSpaceEndPoints(String str) {
        int start = 0;
        int end = str.length() - 1;
        while (start < str.length() && str.charAt(start) == ' ') {
            start++;
        }
        while (end >= 0 && str.charAt(end) == ' ') {
            end--;
        }

        return new int[]{start, end};
    }

    // Method to create substring using charAt()
    public static String createSubstring(String str, int start, int end) {
        String result = "";

        for (int i = start; i <= end; i++) {
            result += str.charAt(i);
        }

        return result;
    }

    // Method to compare two strings using charAt()
    public static boolean compareStrings(String s1, String s2) {

        if (s1.length() != s2.length()) {
            return false;
        }

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
