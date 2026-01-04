

/*

[Strings]

Write a program to demonstrate NullPointerException. 
Hint => 
Write a Method to generate the Exception. Here define the variable text and initialize it to null. Then call one of the String Method to generate the exception
Write the Method to demonstrate NullPointerException. Here define the variable text and initialize it to null. Then write try catch block for handling the Exception while accessing one of the String method
From the main Firstly call the method to generate the Exception then refactor the code to call the method to handle the RuntimeException


*/


import java.util.Scanner;
public class  ExceptionDemonstrationOne{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
        demonstrateNullPointerException();

	in.close();


	}


	// Method to generate exception
	public static void generateException(){
		String name = null;
		
		System.out.println(name.toLowerCase());
	
		
	}

	// Method demonstrating NullPointerException
	public static void demonstrateNullPointerException(){
                
                try{
		            generateException();
                }
                catch(NullPointerException error)
                {
                  System.out.println(error);
                }
		
	}

}

	
	
