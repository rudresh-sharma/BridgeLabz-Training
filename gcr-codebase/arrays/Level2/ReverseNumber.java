/* Create a program to take a number as input and reverse the number. To do this, store the digits of the number in an array and display the array in reverse order
Hint => 
Take user input for a number. 
Find the count of digits in the number. 
Find the digits in the number and save them in an array
Create an array to store the elements of the digits array in reverse order
Finally, display the elements of the array in reverse order  


*/


import java.util.Scanner;
public class ReverseNumber{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number: ");
	long originalNum = in.nextLong();
	boolean isNegative = originalNum < 0;
        long num = Math.abs(originalNum);
	
	
	// Counting digits and storing in array
	long[] digits = new long[19];
	int index = 0;
		while(num>0){
			long digit = num%10;
			num /= 10;
			digits[index] = digit;
			index++;
			}
	
	// printing reverse number
		
		if (isNegative) {
			System.out.print("-");
        	}
		for(int i=0; i<index; i++){
			System.out.print(digits[i]);
		}

	in.close();

	}

}










