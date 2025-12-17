/* 
Create a program to convert the distance of 10.8 kilometers to miles.
Hint: 1 km = 1.6 miles
I/P => NONE
O/P => The distance  ___ km in miles is ___

*/


// Program to convert kilometers into miles
public class KilometersToMiles {

    // main method
    public static void main(String[] args) {

        // distance given in kilometers
        float distanceInKilometers = 10.8f;

        // value of one kilometer in miles
        float oneKmInMiles = 0.62f;

        // converting kilometers to miles
        float distanceInMiles = distanceInKilometers * oneKmInMiles;

        // printing the converted distance
        System.out.println("The distance " + distanceInKilometers +
                           " km in miles is " + distanceInMiles);
    }
}
