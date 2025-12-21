/*

Write a program Quadratic to find the roots of the equation ax2+ bx + c. Use Math functions Math.pow() and Math.sqrt()
Hint => 
Take a, b, and c as input values to find the roots of x.
The roots are computed using the following formulae
delta = b2+ 4*a*c
If delta is positive the find the two roots using formulae 
root1 of x = (-b + delta)/(2*a) 
root1 of x = (-b - delta)/(2*a)
If delta is zero then there is only one root of x  
root of x = -b/(2*a)
If delta is negative return empty array or nothing 
Write a Method to find find the roots of a quadratic equation and return the roots


*/


import java.util.Scanner;
 class QuadraticEquation{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the value of a: ");
	float a = in.nextFloat();
	System.out.print("Enter the value of b: ");
	float b = in.nextFloat();
	System.out.print("Enter the value of c: ");
	float c = in.nextFloat();
    if (a == 0) {
            System.out.println("Not a quadratic equation");
            return;
        }
        
        
	// Printing roots of Given Equation
	float roots[] = rootsOfQuadraticEquation(a,b,c);
	if (roots.length == 0) {
            System.out.println("No real roots exist");
        } 
	else if (roots.length == 1) {
            System.out.println("Root of quadratic equation: " + a+"x2"+b+"x"+c  +" is " + roots[0] );
        }
        else{
            System.out.print("Roots of quadratic equation: " + a+"x2"+b+"x"+c  +" are " + roots[0] + " and " + roots[1] );
        }
	




	in.close();


	}


	// Finding roots of given Quadratic equation

	public static float[] rootsOfQuadraticEquation(float a, float b, float c){
		float[] roots = new float[2];
		float delta = ((float)Math.pow(b,2)) -(4*a*c);
		if(delta>0){
			roots[0] = (-b + (float)Math.sqrt(delta))/(2*a);
			roots[1] = (-b - (float)Math.sqrt(delta))/(2*a);
		}
		else if(delta==0){
			roots[0] = -b/(2*a);
		}
		
		return roots;

	}

}














