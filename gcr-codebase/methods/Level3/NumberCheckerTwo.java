/*
Extend or Create a NumberChecker utility class and perform following task. Call from main() method the different methods and display results. Make sure all are static methods
Hint => 
Method to find the count of digits in the number and a Method to Store the digits of the number in a digits array
Method to find the sum of the digits of a number using the digits array
Method to find the sum of the squares of the digits of a number using the digits array. Use Math.pow() method
Method to Check if a number is a harshad number using a digits array. A number is called a Harshad number if it is divisible by the sum of its digits. For e.g. 21
Method to find the frequency of each digit in the number. Create a 2D array to store the frequency with digit in the first column and frequency in the second column.


*/


import java.util.Scanner;
public class NumberCheckerTwo {
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number:");
	long num = in.nextLong();

	// Printing digits count, digits, sumOfDigits,sumOfSquareOfDigits, is harshad number, frequnecy of each digits
	int digitsCount = digitsCount(num);
	long[] digits = getDigits(num);
	long sumOfDigits = sumOfDigits(digits, digitsCount);
	long sumOfSquareOfDigits = sumOfSquareOfDigits(digits, digitsCount);
	boolean harshadNumber = harshadNumber(num, sumOfDigits);
	int[] frequencyOfDigits = frequencyOfDigits(num);

	System.out.println("Number of digits in number " + num + " is " + digitsCount);
	System.out.print("Digits in " + num + " are ");

	 
	System.out.println();


	System.out.println("Is given number " + num + " is harshad number " + harshadNumber );
	System.out.println("Sum of digits in number = " + sumOfDigits);
	System.out.println("Sum of Square of digits in number = " + sumOfSquareOfDigits);

	for(int i=0; i<10; i++){
		System.out.println(" Frequency of" + i + " = " + frequencyOfDigits[i]);
	}

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



	// Method for getting digits
	public static long[] getDigits(long num){
		int size = digitsCount(num);
		long[] dig = new long[size];

    		for(int i = 0; i < size; i++){
			dig[i] = num % 10;
        		num /= 10;
    		}
    		
		return dig;
	}


	// Method for finding sum of digits
	public static long sumOfDigits(long[] num, int digitsCount){
		long sum = 0;
		int size = digitsCount;
		for(int i=0; i<size; i++){
			sum += num[i];
	        }

		return sum;	
	}


	// Method for finding sum of squares of digits
	public static long sumOfSquareOfDigits(long[] num, int digitsCount){
		long sum = 0;
		int size = digitsCount;
		for(int i=0; i<size; i++){
			sum += (long) Math.pow(num[i],2);
	        }

		return sum;	
	}



	// Method to check harshadn number or not
	public static boolean harshadNumber(long num, long sumOfDigits)
	{
		if(num%sumOfDigits == 0){
			return true;
		}
		else {
			return false;
		}
	}


	// Frequency of digits
	public static int[] frequencyOfDigits(long num){
		int[] digits = new int[10];
		
		while(num>0){
			int digit = (int) num%10;
			digits[digit]++;
			num /= 10;
		}
	
		return digits;

	}

}










