/*




Reverse a String
Problem:
Write a Java program to reverse a given string without using any built-in reverse
functions.


*/


import java.util.Scanner;
public class ReverseString{
	public static void main(String[] args){
	
	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the string: ");
	String originalStr = in.nextLine();
	String reverseStr = getReverseStr(originalStr);


	System.out.println("Reverse string is: " + reverseStr);

	in.close();

	}


	// Method to reverse the string 
	public static String  getReverseStr(String originalStr){
		char[] temp = originalStr.toCharArray();
		int start = 0;
		int end = temp.length-1;
		for(int i=0; i<temp.length/2; i++){
			char ch = temp[i];
			temp[i] = temp[end-i];
			temp[end-i] = ch;
		}

		String str = "";
		for(int i=0; i<temp.length; i++){
			str += temp[i];
		}


		return str;

	}


}


















