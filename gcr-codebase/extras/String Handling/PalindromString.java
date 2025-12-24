/*

Palindrome String Check
Problem:
Write a Java program to check if a given string is a palindrome (a string that reads the
same forward and backward).

*/



import java.util.Scanner;
public class  PalindromString{
	public static void main(String[] args){
	
	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the string: ");
	String originalStr = in.nextLine();
	
	
	boolean isPalindrome = isPalindrome(originalStr, 0, originalStr.length()-1);


		if(isPalindrome){
			System.out.println("Palindrome");
		}	
		else{
			System.out.println("Not Palindrome");
		}

	
	in.close();


	}



	// Method palindrom using recursive method
	public static boolean isPalindrome(String str, int start, int end){
	
		if(start>= end){
			return true;
		}
		else if(str.charAt(start) != str.charAt(end)){
			return false;
		}
		else{	
			return isPalindrome(str, start+1,  end-1);
		}

	}


}