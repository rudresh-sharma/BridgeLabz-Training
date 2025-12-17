/* 
Create a program to convert the distance of 10.8 kilometers to miles.
Hint: 1 km = 1.6 miles
I/P => NONE
O/P => The distance  ___ km in miles is ___

*/


public class KilometersToMiles{

	public static void main(String [] args){

		float distanceInKilometers = 10.8f;
		float oneKmInMiles = 1.6f;


		float distanceInMiles = distanceInKilometers * oneKmInMiles;

		System.out.println("The distance " +  distanceInKilometers +  " km in miles is " + distanceInMiles);


	}

}
