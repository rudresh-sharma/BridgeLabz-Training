/*

Write a program that generates five 4 digit random values and then finds their average value, and their minimum and maximum value. Use Math.random(), Math.min(), and Math.max().
Hint => 
Write a method that generates array of 4 digit random numbers given the size as a parameter as shown in the method signature 
public int[] generate4DigitRandomArray(int size)
Write a method to find average, min and max value of an array 
public double[] findAverageMinMax(int[] numbers) 


*/

		
import java.util.Scanner;
public class RandomNumber{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	int size = 5;
	int[] numbers = generate4DigitRandomArray(size);
	double[] averageMinMax = findAverageMinMax(numbers,size);

	
	// Printing average, min,max of 5 random numbers

	System.out.println("Average of numbers " + numbers[0] +", " + numbers[1] +", " + numbers[2] +", " + + numbers[3] +", " +
+ numbers[4] +" is = " + averageMinMax[0]);
	 System.out.println("Minimum of numbers " + numbers[0] +", " + numbers[1] +", " + numbers[2] +", " + + numbers[3] +", " +
+ numbers[4] +" is = " + averageMinMax[1]);
	 System.out.println("Maximum of numbers " + numbers[0] +", " + numbers[1] +", " + numbers[2] +", " + + numbers[3] +", " +
+ numbers[4] +" is = " + averageMinMax[2]);
	 


	in.close();

	}


	// Method to get random numbers
	public static int[] generate4DigitRandomArray(int size){
		int[] numbers = new int[size]; 
		for(int i=0; i<size; i++){
			numbers[i] = (int)(Math.random() * 9000) + 1000;
		}

		return numbers;
	}
			

	// Method to findAverageMinMax of these numbers
	public static double[] findAverageMinMax(int[] numbers, int size) {
		double[] avgMaxMin = new double[3];
		double average = ((numbers[0]+numbers[1]+numbers[2]+numbers[3]+numbers[4])/size);
		int max = numbers[0];
		int min = numbers[0];
	
		for(int i=1; i<size;i++){
			if(numbers[i]>max)
				max = numbers[i];
		}	
		for(int i=1; i<size;i++){
			if(numbers[i]<min)
				min = numbers[i];
		}	

		avgMaxMin[0] = average;
		avgMaxMin[1] = min;
		avgMaxMin[2] = max;
	
		return avgMaxMin;
}


}






