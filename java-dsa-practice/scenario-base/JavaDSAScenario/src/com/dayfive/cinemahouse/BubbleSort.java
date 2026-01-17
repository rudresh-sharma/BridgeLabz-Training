package com.dayfive.cinemahouse;

public class BubbleSort {
	
	
	public static void bubbleSort(MovieData[] moviedata) {
		
		int n = moviedata.length;
		for(int i=0; i<n-1; i++) {
			for(int j=0; j<n-i-1; j++) {
				if(moviedata[j].getShowTime() > moviedata[j+1].getShowTime()) {
					MovieData temp = moviedata[j];
					moviedata[j] = moviedata[j+1];
					moviedata[j+1] = temp;
				}
			}
		}
		
		
	}
		
}
