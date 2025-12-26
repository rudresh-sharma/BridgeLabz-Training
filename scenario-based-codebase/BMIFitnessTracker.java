/*


Rewrite the above program using multi-dimensional array to store height, weight, and BMI in 2D array for all the persons
Hint => 
Take input for a number of persons
Create a multi-dimensional array to store weight, height and BMI. Also create an to store the weight status of the persons
       double[][] personData = new double[number][3];
       String[] weightStatus = new String[number];
Take input for weight and height of the persons and for negative values, ask the user to enter positive values
Calculate BMI of all the persons and store them in the personData array and also find the weight status and put them in the weightStatus array
Display the height, weight, BMI and status of each person


*/


import java.util.Scanner;
public class PersonsBMIS{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number of persons: ");
	int	 number = in.nextInt();
	double[][] personData    = new double[number][3];
	String[] weightStatus  = new String[number];
		for(int i=0; i<number; i++){
			System.out.print("Enter the weight(kg) and heights(meter) of person " + (i+1) +" :");
			for(int j=0; j<2; j++){
			personData[i][j] = in.nextDouble();
			}
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