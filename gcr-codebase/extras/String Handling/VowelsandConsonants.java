/*

1. Count Vowels and Consonants
Problem:
Write a Java program to count the number of vowels and consonants in a given string.

*/


import java.util.Scanner;
public class VowelsandConsonants{
	public static void main(String[] args){

	Scanner in = new Scanner(System.in);
	System.out.print("Enter the string: ");
	String str = in.nextLine();

	int vowels = getVowelsCount(str);
	int consonants = getConsonantCount(str);

	System.out.println("No of vowels = " + vowels);
	System.out.println("No of consonants = " + consonants );



	in.close();

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


	// Method to get vowels count
	public static int getVowelsCount(String str){
	
		int count = 0;
		for(int i=0; i<str.length(); i++){
			if(isVowel(str.charAt(i))){
				count++;
			}
		}

		return count;
	}



	// Method to get vowels count
	public static int  getConsonantCount(String str){
	
		int count = 0;
		for(int i=0; i<str.length(); i++){
			if(isConsonant(str.charAt(i))){
				count++;
			}
		}

		return count;
	}

}










