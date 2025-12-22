/*

Write a program to return all the characters in a string using the user-defined method,  compare the result with the String built-in toCharArray() method, and display the result
Hint => 
Take user input using the  Scanner next() method to take the text into a String variable
Write a method to return the characters in a string without using the toCharArray() 
Write a method to compare two string arrays and return a boolean result
In the main() call the user-defined method and the String built-in ​​toCharArray() method, compare the 2 arrays, and finally display the result


*/



import java.util.Scanner;
public class StringCharacters{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter String: ");
	String str = in.nextLine();


	// Comparing characters and displaying
	char[] manualChars = getChars(str);
	char[] toCharArray = usingCharArray(str);

	boolean isCharsEqual = isCharsEqual(manualChars, toCharArray);

		if(isCharsEqual){
			System.out.println("Yes chars generated using both methods are equal");						System.out.println("Chars generated manually are: ");
			for(int i=0; i<manualChars.length; i++){
				System.out.println(manualChars[i]);
			}

			System.out.println("Chars generated using toCharArray are: ");
			for(int i=0; i<toCharArray.length; i++){
				System.out.println(toCharArray[i]);
			}


		}
		else{
			System.out.println("Characters generated using manually and to chars of string " + str + " not equal " );
		}

	in.close();

	}




	// Method to get chars manually
	public static char[] getChars(String str){

		char[] character = new char[str.length()];
		for(int i=0; i<character.length; i++){
			character[i] = str.charAt(i);
		}
		
		return character;

	}


	// Method to get chars usingTOCHar
	public static char[] usingCharArray(String str){

		char[] character = str.toCharArray();
				
		return character;

	}


	// Method to chars are equal or not
	public static boolean isCharsEqual(char[] manualChars, char[]toCharArray){
		for(int i=0; i<manualChars.length; i++){
			if(manualChars[i] != toCharArray[i]){
				return false;
			}
		}

		return true;

	}


}






