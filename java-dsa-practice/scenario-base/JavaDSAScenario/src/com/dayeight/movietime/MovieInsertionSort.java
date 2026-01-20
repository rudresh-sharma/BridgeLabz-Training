package com.dayeight.movietime;

import java.util.ArrayList;
public class MovieInsertionSort {
	
	
	public static void insertionsort(ArrayList<MovieData> data, int i) {
		
		MovieData key = data.get(i);
		int j = i-1;
		
		
		while(j>=0 && data.get(j).getTime().isAfter(key.getTime())) {
			data.set(j+1, data.get(j));
			j--;
		}
		
		data.set(j+1, key);
		
	}
	
	
	public static void showDetails(ArrayList<MovieData> data) {
		
		System.out.printf("%-20s%-10s\n", "Movie", "Time");
		
		for(MovieData m : data) {
			System.out.printf("%-20s%-10s\n",m.getTitle(),m.getTime());
		}
		
		
	}
	
	
}
