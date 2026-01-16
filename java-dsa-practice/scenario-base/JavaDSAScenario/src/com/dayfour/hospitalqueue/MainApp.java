/*
1. HospitalQueue – Patient Sorting by Criticality (Bubble Sort)
Story: At Apollo Hospital, patients in the ER are initially listed by arrival time. However, before
assigning beds, the staff wants to sort them by criticality level (1–10). Since the list is usually
small (10–20 patients), Bubble Sort is used to quickly sort patients in-place without using extra
memory.
Key Concepts:
● Bubble Sort for small datasets
● In-place comparison
● Swapping adjacent patient records
*/


package com.dayfour.hospitalqueue;

import java.util.Scanner;
public class MainApp {
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		
		System.out.println("Enter Number of Patients with name and criticallity level");
		int n = in.nextInt();
		in.nextLine();
		
		String[][] patientsOrder = new String[n][2];
		
		for(int i=0; i<n; i++) {
			System.out.println("Enter Details of Patient " + (i+1));
			System.out.print("Name :  ");			
			patientsOrder[i][0] = in.nextLine();
			System.out.print("Criticalty level: ");
			patientsOrder[i][1] = in.nextLine();
		}
		
		BubbleSort.bubbleSort(patientsOrder);
		
		System.out.println("After Sorting...");
		
		for(String patient[] : patientsOrder) {
			System.out.println(patient[0] + " " + patient[1]);
		}
	}
}
