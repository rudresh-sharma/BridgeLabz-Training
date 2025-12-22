/*

Write a program to demonstrate StringIndexOutOfBoundsException
Hint => 
Define a variable of type String and take user input to assign a value
Write a Method to generate the Exception. Access the index using charAt() beyond the length of the String. This will generate a runtime exception and abruptly stop the program.
Write the Method to demonstrate StringIndexOutOfBoundsException. Access the index using charAt() beyond the length of the String. Then write try catch block for Exception while accessing the String method
From the main Firstly call the method to generate the Exception then call the method to handle the RuntimeException

*/

import java.util.Scanner;
public class ExceptionDemonstrationTwo  {
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the string: ");
	String str = in.next();
	
        demonstrateSIOBException(str);

	in.close();


	}


	// Method to generate exception
	public static void generateException(String str){
		System.out.println(str.charAt(str.length()+1));	
		
	}

	// Method demonstrating  StringIndexOutOfBoundsException
	public static void demonstrateSIOBException(String str){
                
                try{
		            generateException(str);
                }
                catch(StringIndexOutOfBoundsException error)
                {
                  System.out.println(error);
                }
		
	}

}

	
	
