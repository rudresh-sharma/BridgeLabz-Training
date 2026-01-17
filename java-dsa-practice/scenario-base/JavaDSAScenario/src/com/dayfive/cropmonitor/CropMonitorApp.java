package com.dayfive.cropmonitor;

import java.util.Scanner;
import java.util.ArrayList;
public class CropMonitorApp {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<SensorData> data = new ArrayList<>();
		
		System.out.println("Do you want to start adding data(yes/no)");
		String choice = in.next();
		
		while(choice.equalsIgnoreCase("yes")) {
			
			System.out.println("Enter Instance Timestamp");
			long timestamp = in.nextLong();
			
			System.out.println("Enter Instance Temperature");
			double temperature = in.nextDouble();
			
			data.add(new SensorData(timestamp, temperature));
			in.nextLine();
			
			System.out.println("Do you want to add data(yes/no)");
			choice  = in.next();
			
		}
		
		
		if (data.size() > 0) {
		    QuickSort.quickSort(data, 0, data.size() - 1);
		}
		else {
			System.out.println("Sorry No data Entered to sort");
			System.exit(0);
		}
		
		
		for(SensorData d : data) {
			System.out.println(d);
		}
		
		
		
		
		
	}
		
}
