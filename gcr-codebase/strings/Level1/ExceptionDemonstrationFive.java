/*

 Write a program to demonstrate ArrayIndexOutOfBoundsException
Hint => 
Define a variable of array of names and take input from the user
Write a Method to generate the Exception. Here access index larger then the length of the array. This will generate a runtime exception and abruptly stop the program. 
Write the Method to demonstrate ArrayIndexOutOfBoundsException. Here access index larger then the length of the array. This will generate a runtime exception. Use the try-catch block to handle the ArrayIndexOutOfBoundsException and the generic runtime exception
From the main Firstly call the method to generate the Exception then call the method to handle the RuntimeException


*/

import java.util.Scanner;
public class ExceptionDemonstrationFive {
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
 
	int[] array = {1,2,3,4,5};
 
        demonstrateNullPointerException(array);

	in.close();


	}


	// Method to generate exception
	public static void generateException(int[] array){
 		for(int i=array.length; i<array.length+1; i++){
			System.out.println(array[i]);
 		}
	}

	// Method demonstrating NullPointerException
	public static void demonstrateNullPointerException(int[] array){
                
                try{
		        generateException(array);
                }
                catch(ArrayIndexOutOfBoundsException error)
                {
			System.out.println(error);
                }
		catch(Exception e){
			System.out.println(e);
		}
		
	}

}

	
	
