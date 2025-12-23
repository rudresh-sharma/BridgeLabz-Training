/*

Find unique characters in a string using the charAt() method and display the result
Hint => 
Create a Method to find the length of the text without using the String method length()
Create a method to Find unique characters in a string using the charAt() method and return them as a 1D array. The logic used here is as follows:
Create an array to store the unique characters in the text. The size is the length of the text
Loops to Find the unique characters in the text. Find the unique characters in the text using a nested loop. An outer loop iterates through each character and an inner loop checks if the character is unique by comparing it with the previous characters. If the character is unique, it is stored in the result array
Create a new array to store the unique characters 
Finally, the main function takes user inputs, calls the user-defined methods, and displays the result. 

*/



import java.util.Scanner;
public class UniqueCharacters {
    public static void main(String[] args) {

	// Taking inputs
        Scanner in = new Scanner(System.in);
	String str = in.nextLine();
	int length = getLength(str);
	

	// Printing unique characters	
	char[] uniqueChars = getUniqueChars(str, length);
	for(int i=0; i<uniqueChars.length; i++){
		System.out.print(uniqueChars[i] + " ");
	}


	in.close();


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






	// Method to get Unique Characters
	public static char[] getUniqueChars(String str, int length){
		char[] temp = new char[length];

		boolean isUnique = true;
		int index=0;
		for(int i=0; i<length; i++){
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


}














	

	














	

	