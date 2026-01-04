/*

Write a program to calculate various trigonometric functions using Math class given an angle in degrees
Hint => 
Method to calculate various trigonometric functions, Firstly convert to radians and then use Math function to find sine, cosine and tangent.
public double[] calculateTrigonometricFunctions(double angle)


*/


import java.util.Scanner;
public class TrigonometricFunctions{
	public static void main(String[] args){
	
	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the angle in degree: ");
	double angle = in.nextInt();


	//Printing the angles values of various trigonometric functions
	TrigonometricFunctions obj = new TrigonometricFunctions();
	double[] values = obj.calculateTrigonometricFunctions(angle);
	
	System.out.println(" Sin = " + values[0]);
	System.out.println(" Cos = " + values[1]);
	System.out.println(" Tan = " + values[2]);
	


	in.close();

	}


	// Method for printing values of TrigonometricFunctions
	public double[] calculateTrigonometricFunctions(double angle)
	{
		double[] temp = new double[3];

		double radians = Math.toRadians(angle);
		temp[0] = Math.sin(radians);
		temp[1] = Math.cos(radians);
		temp[2] = Math.tan(radians);

		return temp;

	}
}

