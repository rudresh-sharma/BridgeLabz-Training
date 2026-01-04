/*

the Total Bonus Amount and display it in a Tabular Format
Write a program for Euclidean distance between two points as well as the equation of the line using those two points. Use Math functions Math.pow() and Math.sqrt()
Hint => 
Take inputs for 2 points x1, y1, and x2, y2 
Method to find the Euclidean distance between two points and return the distance
distance = (x2-x1)2 +(y2-y1)2 
Write a Method to find the equation of a line given two points and return the equation which includes the slope and the y-intercept
The equation of a line is given by the equation y = m*x + b where m is the slope and b is the y-intercept. So firstly compute the slope using the formulae 
m = (y2 - y1)/(x2 - x1)
Post that compute the y-intercept b using the formulae 
b = y1 - m*x1  
Finally, return an array having slope m and y-intercept b 

*/


import java.util.Scanner;
public class EuclideanDistance {
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter coordinates of point1: ");
	double x1 = in.nextDouble();
	double y1 = in.nextDouble();
	System.out.print("Enter coordinates of point2: ");
	double x2 = in.nextDouble();
	double y2 = in.nextDouble();	


	// Printing Euclidean Distance and equation of line by twopoints
	double distance = getEuclideanDistance(x1,y1,x2,y2);
	System.out.println("Euclidean Distance = " + distance);
	printEquation(x1,y1,x2,y2);



	in.close();

	}


	// Method to Find Euclidean distance
	public static double getEuclideanDistance(double x1,double y1,double x2,double y2){
		double part1 = Math.pow((x2-x1),2);
		double part2 = Math.pow((y2-y1),2);

		double distance = Math.sqrt(part1+part2);
		return distance;
	}


	// Method to print equation of line
	public static void printEquation (double x1,double y1,double x2,double y2){
				
		if(x2-x1 == 0){
			System.out.println("Invalid points");
		}
		else{
		double m = (y2-y1)/(x2-x1);
		double b = y1 - m*x1;
		
			if (b >= 0) {
   				 System.out.println("Equation of line is y = " + m + "x + " + b);
			} else {
   				 System.out.println("Equation of line is y = " + m + "x - " + Math.abs(b));
			}
		}

	}	

}












