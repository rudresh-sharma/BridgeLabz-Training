/*

9. Find the Most Frequent Character
Problem:
Write a Java program to find the most frequent character in a string.
Example Input:
String: "success"

Expected Output:
Most Frequent Character: 's'

*/

import java.util.Scanner;

public class MostFrequentCharacter {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String str = in.nextLine();

        int[] freq = new int[256];

	// Counting frequenct of char and printing maimu freq char
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            freq[ch]++;
        }

        char mostFreqChar = '\0';
        int maxCount = 0;

        for (int i = 0; i < 256; i++) {
            if (freq[i] > maxCount) {
                maxCount = freq[i];
                mostFreqChar = (char) i;
            }
        }

        System.out.println("Most frequent character: " + mostFreqChar);
        System.out.println("Frequency: " + maxCount);

        in.close();
    }
}
