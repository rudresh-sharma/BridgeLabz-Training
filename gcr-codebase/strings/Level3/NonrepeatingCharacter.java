/*

Write a program to find the first non-repeating character in a string and show the result
Hint => 
Non-repeating character is a character that occurs only once in the string
Create a Method to find the first non-repeating character in a string using the charAt() method and return the character. The logic used here is as follows:
Create an array to store the frequency of characters in the text. ASCII values of characters are used as indexes in the array to store the frequency of each character. There are 256 ASCII characters
Loop through the text to find the frequency of characters in the text
Loop through the text to find the first non-repeating character in the text by checking the frequency of each character
In the main function take user inputs, call user-defined methods, and displays result. 


*/


import java.util.Scanner;
public class NonrepeatingCharacter {
    public static void main(String[] args) {

	// Taking inputs
        Scanner in = new Scanner(System.in);
	System.out.print("Enter the string ");
	String str = in.nextLine();
 


	// Taking frequency of character and printing first non-repeating character
	int[] charsFrequency = getcharsFrequency(str);
	char uniqueCharacter = getUniqueCharacter(charsFrequency, str);
	System.out.println(uniqueCharacter);


	in.close();

	}




	// Method to get Frequency of characters
	public static int[]  getcharsFrequency(String str){
		int[] charactersFrequency = new int[256];
		

		for(int i=0; i<str.length(); i++){
			int asciiValue = (int) str.charAt(i);
			charactersFrequency[asciiValue] += 1;
		}

		return charactersFrequency;

	}


	// Method to find first Unique Characters
	public static char getUniqueCharacter(int[] charsFrequency, String str){
		int ch = 0;

		for(int i=0; i<str.length(); i++){
			int ascii = str.charAt(i);
			if(charsFrequency[ascii] == 1){
				return str.charAt(i);
 
			}
		}

		return '\0';

	}


}





