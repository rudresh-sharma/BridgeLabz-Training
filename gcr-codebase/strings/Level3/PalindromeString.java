/*

Write a program to to check if a text is palindrome and display the result
Hint => 
A palindrome is a word, phrase, number, or other sequence of characters that reads the same forward and backward
Logic 1: Write a method to compare the characters from the start and end of the string to determine whether the text is palindrome. The logic used here is as follows:
Set the start and end indexes of the text
Loop through the text and compare the characters from the start and the end of the string. If the characters are not equal, return false
Logic 2: Write a recursive method to compare the characters from the start and end of the text passed as parameters using recursion. The logic used here is as follows:
First, check if the start index is greater than or equal to the end index, then return true.
If the characters at the start and end indexes are not equal, return false.
Otherwise, call the method recursively with the start index incremented by 1 and the end index
Logic 3: Write a Method to compare the characters from the start and end of the text using character arrays. The logic used here is as follows:
Firstly Write a Method to reverse a string using the charAt() method and return the reversal array.
Create a character array using the String method toCharArray() and also create a reverse array. Compare the characters in the original and reverse arrays to do a Palindrome check 
Finally, in the main method do palindrome check using the three logic and display result

*/


import java.util.Scanner;
public class PalindromString{
	public static void main(String[] args){
		
	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the string: ");
	String str = in.nextLine();

	boolean usingLogicOne = usingLogicOne(str);
	int start = 0, end = str.length()-1;
	boolean usingLogicTwo = usingLogicTwo(str, start, end);
	boolean usingLogicThree = usingLogicThree(str);
	


		if(usingLogicOne){
			System.out.println("Yes using two pointer given string is palindrome " + str);
		}
		else {
			System.out.println("No using two pointer given string is palindrome " + str);
		}

		if(usingLogicTwo){
			System.out.println("Yes using recursive method given string is palindrome " + str);
		}
		else {
			System.out.println("No using recursive method given string is palindrome " + str);
		}	

		if(usingLogicThree){
			System.out.println("Yes using charArray given string is palindrome " + str);
		}
		else {
			System.out.println("No using charArray given string is palindrome " + str);
		}


	in.close();

	}


	// Method palindrome usig two pointer
	public static boolean usingLogicOne(String str){
		int k= 0;
		int j = str.length()-1;
		for(int i=0; i<str.length()/2; i++){
			if(str.charAt(k) != str.charAt(j)){
				return false;
			}
		}

		return true;
	}


	// Method palindrom using recursive method
	public static boolean usingLogicTwo(String str, int start, int end){
	
		if(start>= end){
			return true;
		}
		else if(str.charAt(start) != str.charAt(end)){
			return false;
		}
		else{	
			return usingLogicTwo(str, start+1,  end-1);
		}

	}
			
	// Method palindrom using char Array
	public static boolean usingLogicThree(String str){
	
		char[] usingTo = str.toCharArray();
		char[] usingManual = new char[str.length()];
		int size = str.length()-1;
		for(int i= size; i>=0; i--){
			usingManual[i] = str.charAt(i);
		}

        for(int i=0; i<usingManual.length; i++){
            usingManual[i] = str.charAt(size);
            size--;
        }
        
        
        
		for(int i=0; i<str.length(); i++){
			if(usingManual[i] != usingTo[i]){
				return false;
			}
		}			
	

		return true;

	}


}

	




