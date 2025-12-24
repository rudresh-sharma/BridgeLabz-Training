/*

6. Find Substring Occurrences
Problem:
Write a Java program to count how many times a given substring occurs in a string.

*/


import java.util.Scanner;
public class SubstringOccurrences{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the string: ");
	String str = in.nextLine();
	System.out.print("Enter the substring: ");
	String substr = in.nextLine();
	int countSubstring = countSubstring(str, substr);
	
	
	// Printing no of times substring occure in string
	System.out.println(countSubstring);


	in.close();

	}


	// Method to count substring
	public static int countSubstring(String str, String substr){
		int substrLength = substr.length();
		int count = 0;
		int lastIdx = str.length()-substrLength;
		int extractIdx = 0;
		// Generate substring of length substrLength
		for(int i=0; i<=lastIdx; i++){
			extractIdx = i+substrLength;
			String temp = str.substring(i,extractIdx );
			if(temp.equals(substr)){
				count++;
			}
 
		}


		return count;

	}

}
	






