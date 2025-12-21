/*

An organization took up the exercise to find the Body Mass Index (BMI) of all the persons in the team of 10 members. For this create a program to find the BMI and display the height, weight, BMI and status of each individual
Hint => 
Take user input in double for the weight (in kg) of the person and height (in cm) for the person and and store it in the corresponding 2D array of 10 rows and 3 columns. The First Column storing the weight, the second column storing the height in cm and the third column is the BMI
Create a Method to find the BMI of every person and populate the array. Use the formula BMI = weight / (height * height). Note unit is kg/m^2. For this convert cm to meter
Create a Method to determine the BMI status using the logic shown in the figure below. and return the array of all the persons BMI Status. 
 


*/



import java.util.Scanner;
public class BodyMassIndex{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number of persons: ");
	int number = in.nextInt();
	double[][] personData    = new double[number][3];
	String[] weightStatus  = new String[number];
	double heightInCM = 0;
		for(int i=0; i<number; i++){
			System.out.print("Enter the weight(kg) and heights(cm) of person " + (i+1) +" :");
			personData[i][0] = in.nextDouble();
			personData[i][1] = in.nextDouble();
			heightInCM = personData[i][1] /100;
			personData[i][1]  = heightInCM;

			
			System.out.println();
		}


	// Printing weights, heights, bmi, status of each person
	for(int i=0; i<number; i++){
		personData[i][2] = bodyMassIndex(personData[i][0], personData[i][1]);
	}
	for(int i=0; i<number; i++){
		weightStatus[i] = weightStatus(personData[i][2]);
	}
	for (int i = 0; i < number; i++) {
 	   	System.out.println("Details of person " + (i + 1) + " are:");
    		System.out.println("Weight = " + personData[i][0]);
    		System.out.println("Height = " + personData[i][1]);
    		System.out.println("BMI = " + personData[i][2]);
    		System.out.println("Weight Status = " + weightStatus[i]);
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
