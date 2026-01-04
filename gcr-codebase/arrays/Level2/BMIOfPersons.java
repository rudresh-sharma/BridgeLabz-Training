/*

An organization took up an exercise to find the Body Mass Index (BMI) of all the persons in the team. For this create a program to find the BMI and display the height, weight, BMI and status of each individual
Hint => 
Take input for a number of persons
Create arrays to store the weight, height, BMI, and weight status of the persons
Take input for the weight and height of the persons
Calculate the BMI of all the persons and store them in an array and also find the weight status of the persons
Display the height, weight, BMI, and weight status of each person
Use the table to determine the weight status of the person


*/


import java.util.Scanner;
public class BMIOfPersons{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number of persons: ");
	int number = in.nextInt();
	double[] weights = new double[number];
	double[] heights = new double[number];
	double[] bmi     = new double[number];
	String[] status  = new String[number];
		for(int i=0; i<number; i++){
			System.out.print("Enter the weight(kg) and heights(meter) of person " + (i+1) +" :");
			weights[i] = in.nextDouble();
			heights[i] = in.nextDouble();
			System.out.println();
		}
	

	// Printing weights, heights, bmi, status of each person
	for(int i=0; i<number; i++){
		bmi[i] = bodyMassIndex(weights[i], heights[i]);
		status[i] = weightStatus(bmi[i]);
	}
	
	for (int i = 0; i < number; i++) {
 	   	System.out.println("Details of person " + (i + 1) + " are:");
    		System.out.println("Weight = " + weights[i]);
    		System.out.println("Height = " + heights[i]);
    		System.out.println("BMI = " + bmi[i]);
    		System.out.println("Weight Status = " + status[i]);
    		System.out.println(); // <-- this line creates a blank line
}	




	in.close();

	}



	// Method for finding the bmi 
	public static double bodyMassIndex(double weight, double height){

		double BMIIndex = weight/(height * height);
		return BMIIndex;
	}
	

	// Method for finding weight status
	public static String weightStatus(double BMIIndex){
		
		
		if(BMIIndex <= 18.4){
			return "underweight";
		}
		else if(BMIIndex >=18.5 && BMIIndex <= 24.9){
			return "normal";
		}
		else if(BMIIndex >=25.0 && BMIIndex <= 39.9){
			return "Overweight";
		}
		else {
			return "Obese";
		}

	}

}






