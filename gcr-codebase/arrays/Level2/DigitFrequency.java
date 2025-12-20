/*

Create a program to take a number as input find the frequency of each digit in the number using an array and display the frequency of each digit
Hint => 
Take the input for a number
Find the count of digits in the number
Find the digits in the number and save them in an array
Find the frequency of each digit in the number. For this define a frequency array of size 10, Loop through the digits array, and increase the frequency of each digit
Display the frequency of each digit in the number

*/




import java.util.Scanner;
public class DigitFrequency{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number: ");
	long originalNum = in.nextLong();


	// Counting digits and storing in array
	int maxDigits = 19;
	long[] digits = new long[maxDigits];
	int index = 0;
	long num = originalNum;
		while(num>0){
			long digit = num%10;
			num /= 10;
			digits[index] = digit;
			index++;
			if(index+1 == 19){
				break;
			}
		}
	


	// counting frequency of digits
	int count = 0;
	long[][] frequency = new long[index][1];
	long[] previousElements = new long[19];

	int k=0;
		for(int i=0; i<index; i++){
 
			
			if(!isPreviousEqual(previousElements, digits[i])){
				for(int j=i+1; j<index; j++){
					if(digits[i] == digits[j]){
						count++;	
					}
				}
			frequency[i][0] = count+1;

			}
			previousElements[k] = digits[i];

 
			count = 0;
 			k++;
		}
	
 
 

	// Printing Frequencies of digit
	k=0;

		for(int i=0; i<index; i++){
			if(!isPreviousEqual(previousElements, digits[i]) && frequency[i][0] > 0){	
				System.out.println("Frequency of " + digits[i] + " = " + frequency[i][0]);
			}
			previousElements[k] = digits[i];

		}

	in.close();

	}



	// Method for cheking is current element is equal or not to previous elements
	public static boolean isPreviousEqual(long[] previousElements, long currentElements){
		for(int i=0; i<previousElements.length; i++){
			if(previousElements[i] == currentElements){
				return true;
			}
		}
		return false;

	}

}








