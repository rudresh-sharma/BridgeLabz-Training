/*
11. Temperature Logger 🌡️
Record temperatures over 7 days.
● Use array and for-loop.
● Find average and max temperature.
● Use if for comparisons. Maintain readable naming and modular code blocks.

*/



import java.util.Scanner;
public class TemperatureLogger{
	public static void main(String[] args){

	// taking inputs
	Scanner in = new Scanner(System.in);
	float[] sevenDaytemperatures = new float[7];
	float sum = 0;
		for(int i=0; i<7; i++){
			System.out.print("Enter the temperature of day " + (i+1) + " : ");
			sevenDaytemperatures[i] = in.nextFloat();
			sum += sevenDaytemperatures[i];
		}


	float average = sum/7;
	float maximum = sevenDaytemperatures[0];
		for(int i=1; i<7; i++){
			if(sevenDaytemperatures[i] > maximum){
				maximum = sevenDaytemperatures[i];
			}
		}



	System.out.println("\nAverage of all given temperatures is: " + average);
	System.out.println("Maximum of all given temperatures is: " + maximum);


	in.close();

	}

}
