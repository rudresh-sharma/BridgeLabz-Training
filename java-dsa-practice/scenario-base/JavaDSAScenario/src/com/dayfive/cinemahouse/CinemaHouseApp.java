package com.dayfive.cinemahouse;

import java.util.Scanner;

public class CinemaHouseApp {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter number of movies");
		int n = in.nextInt();
		in.nextLine();
		
		MovieData[] moviedata = new MovieData[n];
		
		for(int i=0; i<n; i++) {
			System.out.print("Enter Movie name: ");
			String name = in.nextLine();
			
			System.out.print("Enter show time(hours minutes) ");
			
			int hour = in.nextInt();
			int minutes = in.nextInt();
			
			in.nextLine();
			moviedata[i] = new MovieData(name, hour, minutes);
			
		}
		
		
		BubbleSort.bubbleSort(moviedata);
		
		
		for(int i=0; i<n; i++) {
			System.out.println(moviedata[i].getMovieName() + "----->" + moviedata[i].getFormattedTime());
		}
		
		
		in.close();
		
	}
}
