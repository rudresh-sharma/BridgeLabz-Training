/*


Write a program the find the distance in yards and miles for the distance provided by user in feets
Hint => 1 mile = 1760 yards and 1 yard is 3 feet
I/P => distanceInFeet
O/P => Your Height in cm is ___ while in feet is ___ and inches is ___


*/


import java.util.Scanner;

// Program to convert distance from feet to yards and miles
public class DistanceConversion {
    public static void main(String[] args) {

        // ----- input -----
        // create Scanner object to read input from user
        Scanner input = new Scanner(System.in);

        // ask user to enter distance in feet
        System.out.print("Enter the distance in feets: ");
        float distance = input.nextFloat();

        // ----- calculation -----
        // convert feet to yards (1 yard = 3 feet)
        float distanceInYards = distance / 3;

        // convert yards to miles (1 mile = 1760 yards)
        float distanceInMiles = distanceInYards / 1760;

        // ----- output -----
        // print the distances in feet, yards, and miles
        System.out.printf(
            "The distance %.2f in %.2f in yards and %.2f in miles", 
            distance, distanceInYards, distanceInMiles
        );
    }
}
