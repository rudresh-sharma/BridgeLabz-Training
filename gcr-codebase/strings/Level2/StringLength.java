/*

Write a program to find and return the length of a string without using the length() method 
Hint => 
Take user input using the Scanner next() method 
Create a method to find and return a string's length without using the built-in length() method. The logic for this is to use the infinite loop to count each character till the charAt() method throws a runtime exception, handles the exception, and then return the count
The main function calls the user-defined method as well as the built-in length() method and displays the result


*/

import java.util.Scanner;
public class StringLength{	
	public static void main(String[] args){

	// Taking input
	Scanner in = new Scanner(System.in);	
	System.out.print("Enter the string: ");
	String str = in.next();
	

	// Printing length of string manually and using length()
	int manualLength = getLength(str);
	int methodLength = str.length();
		if(manualLength == methodLength){
			System.out.println("Yes length using both method equal");
			System.out.println("Length using manualMthod is " + manualLength);
			System.out.println("Length using methodLength is " + methodLength);
		}
		else{
			System.out.println("No length using both method not equal");
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

}















