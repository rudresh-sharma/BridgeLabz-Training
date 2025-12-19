/*

Create a program to find the BMI of a person
Hint => 
Take user input in double for the weight (in kg) of the person and height (in cm) for the person and store it in the corresponding variable.
Use the formula BMI = weight / (height * height). Note unit is kg/m^2. For this convert cm to meter
Use the table to determine the weight status of the person
 


*/



import java.util.Scanner;
public class BMI{
	public static void main(String[] args){
	
	// Taking inputs
	Scanner in =  new Scanner(System.in);
	System.out.print("Enter the weight in kg: ");
	double weight = in.nextDouble();
	System.out.print("Enter the height in cm: ");
	double heightInCM = in.nextDouble();


	// Calculating BMI and Categorise According to it
	double heightInMeter = heightInCM/100;
	double BMIIndex = weight/(heightInMeter * heightInMeter);

		if(BMIIndex <= 18.4){
			System.out.print("You are underweight " );
		}
		if(BMIIndex >=18.5 && BMIIndex <= 24.9){
			System.out.print("You are normal " );
		}
		if(BMIIndex >=25.0 && BMIIndex <= 39.9){
			System.out.print("You are Overweight " );
		}
		if(BMIIndex >= 40.0){
			System.out.print("You are Obese " );
		}

	
	in.close();	
	}
}

	
