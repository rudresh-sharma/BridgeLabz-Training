/* 

Create a program to convert distance in kilometers to miles.
Hint => 
Create a variable km and assign type as double as in double km;
Create Scanner Object to take user input from Standard Input that is the Keyboard as in Scanner input = new Scanner(System.in);
Use Scanner Object to take user input for km as in km = input.nextInt();
Use 1 mile = 1.6 km formulae to calculate miles and show the output
I/P => km
O/P => The total miles is ___ mile for the given ___ km


*/


import java.util.Scanner;

// Program to convert distance from kilometers to miles
public class KmToMilesInput {

    // main method
    public static void main(String[] args) {

        // ----- input -----
        // creating Scanner object to read input from user
        Scanner input = new Scanner(System.in);

        // asking user to enter distance in kilometers
        System.out.print("Enter the Distance in Kilometer: ");

        // reading the distance entered by user
        float km = input.nextFloat();

        // conversion factor for 1 km to miles
        float oneKmInMiles = 0.6f;

        // ----- calculation -----
        // converting kilometers to miles
        float miles = km * oneKmInMiles;

        // ----- output -----
        // printing the converted distance
        System.out.println(
            "The total miles is " + miles + " mile for the given " + km + " km"
        );
    }
}

