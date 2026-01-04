/*

Extend or Create a NumberChecker utility class and perform following task. Call from main() method the different methods and display results. Make sure all are static methods
Hint => 
Method to Find the count of digits in the number
Method to Store the digits of the number in a digits array
Method to Check if a number is a duck number using the digits array. A duck number is a number that has a non-zero digit present in it
Method to check if the number is a armstrong number using the digits array. ​​Armstrong number is a number that is equal to the sum of its own digits raised to the power of the number of digits. Eg: 153 = 1^3 + 5^3 + 3^3
Method to find the largest and second largest elements in the digits array. Use Integer.MIN_VALUE to initialize the variable.
Method to find the the smallest and second smallest elements in the digits array. Use Integer.MAX_VALUE to initialize the variable.

*/



import java.util.Scanner;
public class NumberCheckerOne {
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number:");
	long num = in.nextLong();


	// Printing digits count, duck number, armstrong, second smallest and second largest, largest and smallest
	int digitsCount = digitsCount(num);
	boolean duckNumber = duckNumber(num);
	long[] digits = getDigits(num);
	boolean armstrongNumber = armstrongNumber(digits, digitsCount,num);
	long[] largest = largestAndSecondLargest(digits,digitsCount );
	long[] smallest = smallestAndSecondSmallest(digits,digitsCount);
	System.out.println("Number of digits in number " + num + " is " + digitsCount);
	System.out.println("Is given number " + num + " is duck number " + duckNumber);
	System.out.println("Is given number " + num + " is armstrong number " + armstrongNumber);
	System.out.println("Largest digit in number " + num + " is " + largest[0]);
	System.out.println("Second Largest digit in number " + num + " is " + largest[1]);
	System.out.println("Smallest digit in number " + num + " is " + smallest[0]);
	System.out.println("Second Smallest digit in number " + num + " is " + smallest[1]);


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


	// Getting second smallest and smallest
	public static long[] smallestAndSecondSmallest(long[] digits, int size){
		long smallest = Integer.MAX_VALUE;
		long secondSmallest = Integer.MAX_VALUE;
		long[] arr = new long[2];
			for(int i=0; i<size; i++){
				if(digits[i]<smallest){
					secondSmallest = smallest;	
					smallest = digits[i];
				}
				else if((digits[i] != smallest) && (digits[i]<secondSmallest)){
					secondSmallest = digits[i];
				}
			}
		arr[0] = smallest;
		arr[1] = secondSmallest;

		return arr;

	}



	// Getting second smallest and smallest
	public static long[] largestAndSecondLargest(long[] digits, int size){
		long largest = Integer.MIN_VALUE;
		long secondLargest = Integer.MIN_VALUE;
		long[] arr = new long[2];
			for(int i=0; i<size; i++){
				if(digits[i]>largest){
					secondLargest = largest;	
					largest = digits[i];
				}
				else if((digits[i] != largest) && (digits[i]>secondLargest)){
					secondLargest = digits[i];
				}
			}
		arr[0] = largest;
		arr[1] = secondLargest;

		return arr;

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


	// Checking number is armstrong or not
	public static boolean armstrongNumber(long[] num, int noOfDigits , long n){
	long sum = 0;
	    for(int i=0; i<noOfDigits; i++){
			sum += (long)Math.pow(num[i],noOfDigits);
	    }
		if(sum == n){
			return true;

		}
		else{
			return false;
		}

	}


}
 













