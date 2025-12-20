/*

Write a program calculate the wind chill temperature given the temperature and wind speed
Hint => 
Write a method to calculate the wind chill temperature using the formula 
windChill = 35.74 + 0.6215 *temp + (0.4275*temp - 35.75) * windSpeed0.16 
public double calculateWindChill(double temperature, double windSpeed)


*/


import java.util.Scanner;
public class WindChill{
	public static void main(String[] args){
	
	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the temperature: ");
	double temperature = in.nextFloat();
	System.out.print("Enter the wind speed: ");
	double windSpeed = in.nextFloat();


	// Printing wind chill 
	WindChill windChill = new WindChill();
	double windChillValue = windChill.calculateWindChill(temperature, windSpeed);
	System.out.println("Wind Chill = " + windChillValue);

	in.close();

	}


	// Method for calculating wind chill
	public double calculateWindChill(double temp, double windSpeed){
		return ( 35.74 + 0.6215 *temp + (0.4275*temp - 35.75) * windSpeed *0.16 );	
	}


}
	


	