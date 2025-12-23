/*

Write a program to find the frequency of characters in a string using nested loops and display the result
Hint => 
Create a method to find the frequency of characters in a string and return the characters and their frequencies in a 1D array. The logic used here is as follows:
Create an array to store the frequency of each character in the text and an array to store the characters in the text using the toCharArray() method
Loops to Find the frequency of each character in the text and store the result in a frequency array. For this use a Nested Loop with an Outer loop to iterate through each character in the text and initialize the frequency of each character to 1. And an Inner loop to check for duplicate characters. In case of duplicate increment the frequency value and set the duplicate characters to '0' to avoid counting them again.
Create a 1D String array to store the characters and their frequencies. For this Iterate through the characters in the text and store the characters and their frequencies 
Finally, the main function takes user inputs, calls the user-defined methods, and displays the result. 


*/


import java.util.Scanner;
public class CharactersFrequencyFour{
	public static void main(String[] args){
		
	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the string: ");
	String str = in.nextLine();


	// Printing frequency of characters
	int length = getLength(str);
	char[] uniqueChars = getUniqueChars(str,length);
	String[][] charFreq = getCharFreq(str,uniqueChars);
 

	for(int i=0; i<charFreq.length; i++){
		System.out.println("Character = " + charFreq[i][0] + " Frequency = " + charFreq[i][1]);
	}


	in.close();



	}



	// Method to get Frequency of characters
	public static String[][] getCharFreq(String str,char[] uniqueChars){
		int size = uniqueChars.length;
		String[][] charFreq = new String[size][2];
			
		for(int i=0; i<size; i++){
			char ch = uniqueChars[i];
			int count = 0;
			for(int j=0; j<str.length(); j++){
				if(str.charAt(j) == ch){
					count++;
				}
			}
		charFreq[i][0] = String.valueOf(ch);
		charFreq[i][1] = String.valueOf(count);
		}

		return charFreq;

	}




	// Method to get Unique Characters
	public static char[] getUniqueChars(String str, int length){
		char[] temp = new char[length];

		boolean isUnique = true;
		int index=0;
		for(int i=0; i<str.length();i++){
			char ch = str.charAt(i);
			isUnique = true;

			for(int j=0; j<i; j++){
				if(str.charAt(j) == ch){
					isUnique = false;
					break;
				}
			}
			if(isUnique){
				temp[index] = ch;
 				index++;
 
			}
		}


		char[] result = new char[index];
		for(int i=0; i<index; i++){
			result[i] = temp[i];
		}

		return result;

	}


	// Method to get length using manual way
	public static int getLength(String str){
		int count = 0;
		int asciiSum =0;
		
		try{
			int i=0;
			while(true){
				asciiSum += (int) str.charAt(i);
				i++;
				count++;
			}
		}
		catch(Exception e){
			return count;
		}
	}



}

