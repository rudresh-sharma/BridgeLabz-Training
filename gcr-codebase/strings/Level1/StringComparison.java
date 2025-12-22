/*
[Strings]

Write a program to compare two strings using the charAt() method and check the result with the built-in String equals() method
Hint => 
Take user input using the  Scanner next() method for 2 String variables
Write a method to compare two strings using the charAt() method and return a boolean result
Use the String Built-In method to check if the results are the same and display the result 


*/



import java.util.Scanner;
public class StringComparison{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter String 1: ");
	String str1 = in.next();
	System.out.print("Enter String 2: ");
	String str2 = in.next();



	// Comparing string by both charAt() and equal and comparing results
		if(str1.length() != str2.length()){
			System.out.println("Both string are not equal ");
			System.exit(-1);
		}

	 
	 
	        if(byCharAt(str1, str2) && byEqual(str1, str2) ){
			System.out.println("Yes both  strings are equal using charAt and equal");
		}
		else{ 		
			System.out.println("No both  strings are equal using charAt and equal");
		}
	
		in.close();

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


	// Method equals Comparison

	public static boolean byEqual(String str1, String str2){
 
		if(str1.equals(str2)){
			return true;
		}
		
		return false;
		
	}

}









