package com.dayseven.fitnesstracker;

import java.util.Scanner;

public class MainApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Number of people");
		int n = sc.nextInt();
		
		int[][] personsStepCount = new int[n][2];
		
		System.out.println("Enter Each Person Step counts");
		
		for(int i=0; i<n; i++) {
			System.out.println("Person " + (i+1) + " Detail");
			personsStepCount[i][0] = i+1;
			personsStepCount[i][1] = sc.nextInt();
		}
		
		
		FirBubbleSort.bubbleSort(personsStepCount);
		
		for(int i=0; i<n; i++) {
			System.out.println("Person " + personsStepCount[i][0] + " | Steps Count " + personsStepCount[i][1]);
		}
		
		
	}
}
