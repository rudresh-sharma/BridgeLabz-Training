/*
[String]

Write a program to create a substring from a String using the charAt() method. Also, use the String built-in method substring() to find the substring of the text. Finally Compare the the two strings and display the results
Hint => 
Take user input using the  Scanner next() method to take the String variable and also the start and the end index to get the substring from the given text
Write a method to create a substring from a string using the charAt() method with the string, start, and end index as the parameters
Write a method to compare two strings using the charAt() method and return a boolean result
Use the String built-in method substring() to get the substring and compare the two strings. And finally display the result


*/


import java.util.Scanner;
public class CreateSubstring{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter String: ");
	String str = in.nextLine();
	System.out.print("Enter Starting index: ");
	int indexStart = in.nextInt();
	System.out.print("Enter Ending index: ");
	int indexEnd = in.nextInt();
 

	
	// generating substring using charAt and substring , and checking isequal
		if(indexEnd >= str.length()){
			System.out.print("Out of index ");
			System.exit(-1);
		}
	String str1 = substringByCharAt(str, indexStart, indexEnd);
	String str2 = substringBySubstring(str, indexStart, indexEnd);
	boolean isEqual =byCharAt(str1,str2); 

		if(isEqual){
			System.out.println("Yes both substrings generated using charAt and substring are equal ");
			System.out.println("Substrings generated using charAt:" +str1);
			System.out.println("Substrings generated using substring " + str2);
		}
		else{
			System.out.println("No both substrings generated using charAt and substring are equal ");
		}

	in.close();


	}


	// Method for generating substring using charAt();
	public static String substringByCharAt(String str, int indexStart, int indexEnd){
		String sub = "";
		for(int i=indexStart; i<indexEnd; i++){
			sub += str.charAt(i);
		}

		return sub;
	}


	// Method for generating substring using substring();
	public static String substringBySubstring(String str1, int indexStart, int indexEnd){
		
		String sub = str1.substring(indexStart,indexEnd);
		return sub;
	}


	// Method charAt comparison
	public static boolean byCharAt(String str1, String str2){
 
		for(int i=0; i<str1.length(); i++){
			if(str1.charAt(i) != str2.charAt(i)){
				return false;
			}
		}
		
		return true;
	}

}












