/*

Create a program to store the digits of the number in an array and find the largest and second largest element of the array.
Hint => 
Create a number variable and Take user input. 
Define an array to store the digits. Set the size of the array to maxDigit variable initially set to 10
Create an integer variable index with the value 0 to reflect the array index.
Use a loop to iterate until the number is not equal to 0.
Remove the last digit from the number in each iteration and add it to the array.
Increment the index by 1 in each iteration and if the index count equals maxDigit then break out of the loop and the remaining digits are not added to the array
Define variable to store largest and second largest digit and initialize it to zero
Loop through the array and use conditional statements to find the largest and second largest number in the array
Finally display the largest  and second-largest number


*/



import java.util.Scanner;
public class MaximumDigits2{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number: ");
	long originalNum = in.nextLong();


	// Counting digits and storing in array
	int maxDigits = 10;
	long[] digits = new long[maxDigits];
	int index = 0;
	long num = originalNum;
		while(num>0){
			long digit = num%10;
			num /= 10;
			digits[index] = digit;
			index++;
			if(index+1 == 10){
				maxDigits *= 2;
				long[] temp = new long[maxDigits];	
               			for (int j = 0; j < index; j++) {
                    			temp[j] = digits[j];
               			 }

               			 digits = temp;
			}
		}





	// finding largest and smallest digit
	long largest = digits[0];
	long secondLargest = -1;
		for(int i=0; i<index; i++){
			if(digits[i] > largest){
				secondLargest = largest;
				largest = digits[i];
			}
			else if(digits[i] != largest && digits[i] > secondLargest){
				secondLargest = digits[i];
			}
		}
	System.out.println("Largest digit in numbers is: " + largest);
	System.out.println("Second largest digit in numbers is: " + secondLargest);
	

	in.close();

	}

}






















