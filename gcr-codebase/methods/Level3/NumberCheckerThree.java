/*


Extend or Create a NumberChecker utility class and perform following task. Call from main() method the different methods and display results. Make sure all are static methods
Hint => 
Method to find the count of digits in the number and a Method to Store the digits of the number in a digits array
Method to reverse the digits array 
Method to compare two arrays and check if they are equal
Method to check if a number is a palindrome using the Digits. A palindrome number is a number that remains the same when its digits are reversed. 
Method to Check if a number is a duck number using the digits array. A duck number is a number that has a non-zero digit present in it


*/

import java.util.Scanner;
public class NumberCheckerThree {
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number:");
	long num = in.nextLong();

	
	// Printing digits count, storing digits, reverse digits array, compare two arrays is equal or not,is number palindrome and duck
	int digitsCount = digitsCount(num);
	boolean duckNumber = duckNumber(num);
	long[] reverseDigits = getReverseDigits(num, digitsCount);
	long[] orderedDigits = getOrderedDigits(reverseDigits, digitsCount);
	boolean isPalindrome = isPalindrome(orderedDigits, digitsCount);
	boolean isArraysEqual = isArraysEqual(orderedDigits, reverseDigits, digitsCount);
	System.out.println("Number of digits in number " + num + " is " + digitsCount);
	System.out.println("Is given number " + num + " duck number " + duckNumber);
	System.out.println("Is given number " + num + " palindrome " + isPalindrome);
	System.out.println("Is Both arrays Equal " + isArraysEqual);

	in.close();

	}

	
	// Method for getting Number of digits
	public static int digitsCount(long num){
		long n = num;
		int count=0;
			while(n>0){
				long digit= n%10;
				count++;
				n /= 10;
			}
		
		return count;
	}

	// Checking number is duck or not
	public static boolean duckNumber(long num){
		long n = num;
			while(n>0){
				long digit = n%10;
				if(digit == 0){
					return true;
				}
				n /= 10;
			}

		return false;
	}

	
	// Method for getting digits in reverse order
	public static long[] getReverseDigits(long num, int digitsCount){
		int size = digitsCount;
		long[] dig = new long[size];

    		for(int i = 0; i < size; i++){
			dig[i] = num % 10;
        		num /= 10;
    		}
    		
		return dig;
	}



	// Method for getting digits in  order
	public static long[] getOrderedDigits(long[] reverseDigits,int digitsCount){
		int size = digitsCount;
		long[] dig = reverseDigits;

    		for(int i = 0; i < digitsCount/2; i++){
			long temp = dig[i];
			dig[i] = dig[digitsCount-i-1];
			dig[digitsCount-i-1] = temp;
    		}
    		
		return dig;
	}



	// Method for checking palindrome
	public static boolean isPalindrome(long[] orderedDigits, int digitsCount){
		int size = digitsCount;
		long[] dig = orderedDigits;

    		for(int i = 0; i < digitsCount/2; i++){
			 if(dig[i] != dig[digitsCount-i-1]){
				return false;
			 }
			
    		}
    		
		return true;
	}

	// Is Arrays are equal
	public static boolean isArraysEqual(long[] orderedDigits, long[] reverseDigits, int digitsCount){
		for(int i=0; i<digitsCount; i++){
			if(orderedDigits[i] != reverseDigits[i]){
				return false;
			}
		}
		
		return true;

	}





}








