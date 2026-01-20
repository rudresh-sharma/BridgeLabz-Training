package com.dayseven.fitnesstracker;

public class FirBubbleSort {

	public static void bubbleSort(int[][] personData) {
		int n = personData.length;
		
		for(int i=0; i<n-1; i++) {
			for(int j=0; j<n-i-1; j++) {
				if(personData[j][1] < personData[j+1][1] ) {
					int temp1 = personData[j][1];
					personData[j][1] = personData[j+1][1];
					personData[j+1][1] = temp1;
					
					int temp2 = personData[j][0];
					personData[j][0] = personData[j+1][0];
					personData[j+1][0] = temp2;
					
				}
			}
		}
	}
	
	
	
	
	
}
