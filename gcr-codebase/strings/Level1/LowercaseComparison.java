/*



*/


/*

Write a program to convert the complete text to uppercase and compare the results
Hint => 
Take user input using the  Scanner nextLine() method to take the complete text into a String variable
Write a method using the String built-in charAt() method to convert each character if it is lowercase to the uppercase. Use the logic ASCII value of 'a' is 97 and 'A' is 65 so the difference is 32, similarly ASCII value of 'b' is 98 and 'B' is 66 so the difference is 32, and so on
Write a method to compare two strings using the charAt() method and return a boolean result
In the main() use the String built-in method toUpperCase() to get the uppercase text and compare the two strings using the user-defined method. And finally display the result

*/


import java.util.Scanner;
public class LowercaseComparison{
	public static void main(String[] args){

	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the string : ");
	String str = in.next();
	

	// Printing lowercase string using both manualLowerCase & methodLowerCase
	String manualLowerCase =  manualLowerCase(str);
	String methodLowerCase = str.toLowerCase();
		if(manualLowerCase.equals(methodLowerCase)){
			System.out.println("Yes both Lowercase is correct"); 
			System.out.println("Lowercase using manually is " + manualLowerCase); 
			System.out.println("Lowercase using method is " + methodLowerCase);
		}
		else{
			System.out.println("No both Lowercase is not same"); 
		}


	in.close();

	}



	// Method to uppercase Manually
	public static String manualLowerCase (String str) {
        	char[] temp = str.toCharArray();
		String tempString = "";
        	for (int i = 0; i < str.length(); i++) {
			temp[i] = (char)(temp[i]+32);
	    		tempString += temp[i];
       	        }
        	return tempString;
	}


}




