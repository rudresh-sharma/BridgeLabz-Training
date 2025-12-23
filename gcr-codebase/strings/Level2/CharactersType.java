/*

Write a program to find vowels and consonants in a string and display the character type - Vowel, Consonant, or Not a Letter
Hint => 
Create a method to check if the character is a vowel or consonant and return the result. The logic used here is as follows:
Convert the character to lowercase if it is an uppercase letter using the ASCII values of the characters
Check if the character is a vowel or consonant and return Vowel, Consonant, or Not a Letter
Create a Method to find vowels and consonants in a string using charAt() method and return the character and vowel or consonant in a 2D array
Create a Method to display the 2D Array of Strings in a Tabular Format
Finally, the main function takes user inputs, calls the user-defined methods, and displays the result. 


*/



import java.util.Scanner;
public class  CharactersType{	
	public static void main(String[] args){

	// Taking input
	Scanner in = new Scanner(System.in);	
	System.out.print("Enter the string: ");
	String strOriginal = in.nextLine();
	String str = strOriginal;
 

	displayCharactersWithType(str);

	in.close();
	
	}


	 // Method to check is vowel or not
    public static boolean isVowel(char ch) {
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
        if (ch >= 'A' && ch <= 'Z') {
            ch = (char) (ch + 32);
        }

        if (ch >= 'a' && ch <= 'z') {
            return !isVowel(ch);
        }

        return false; // not a letter
    }


	// Method to 	displayCharactersWithType
	public static void displayCharactersWithType(String str){
		

		System.out.println("Character\t Type");
		System.out.println("---------------------");
		System.out.println();

		for(int i=0;i<str.length(); i++){
			if( isVowel(str.charAt(i))){
				System.out.println( str.charAt(i) + "\t\t vowels");
			}
			else if(isConsonant(str.charAt(i))){
				System.out.println( str.charAt(i) +  "\t\tConsonants");
			}
			else{
				System.out.println( str.charAt(i) +"\t\tNot a letter");
			}
		}
	}

}









