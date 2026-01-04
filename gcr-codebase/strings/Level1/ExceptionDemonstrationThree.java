/*

Write a program to demonstrate IllegalArgumentException
Hint => 
Define a variable of type String and take user input to assign a value
Write a Method to generate the Exception. Here use the subString() and set the start index to be greater than the end index. This will generate a runtime exception and abruptly stop the program. 
Write the Method to demonstrate IllegalArgumentException. Here use the subString() and set the start index to be greater than the end index. This will generate a runtime exception. Use the try-catch block to handle the IllegalArgumentException and the generic runtime exception
From the main Firstly call the method to generate the Exception then call the method to handle the RuntimeException


*/



import java.util.Scanner;
public class ExceptionDemonstrationThree  {
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the string: ");
	String str = in.next();

        demonstrateIAException(str);

	in.close();


	}


	// Method to generate exception
	public static void generateException(String str){
		 	System.out.println(str.substring(str.length()-2, str.length()-3));
		
	}

	// Method demonstrating NullPointerException
	public static void demonstrateIAException(String str){
                
                try{
		      generateException(str);      
                }
                catch(IllegalArgumentException error)
                {
                  System.out.println(error);
                }
		catch(Exception e){
		  System.out.println("Generic exception " + e);
		}
		
	}

}

	
	
