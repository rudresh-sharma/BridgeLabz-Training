package com.daysix.icecreamrush;

import java.util.Scanner;
public class IceCreamRushMain {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		String[] flavours = {"Vanilla", "Chocolate", "Strawberry ", "Mango ", "Butterscotch ", "Pistachio ", "Coffee ", "Blueberry"};
		int[] unitsSold = new int[8];
		
		System.out.println("Enter number of units sold of each flavours");
		
		
		for(int i=0; i<8; i++) {
			System.out.print(flavours[i] + "---> ");
			unitsSold[i] = in.nextInt();
	
		}
		
		BubbleSort.bubbleSort(flavours, unitsSold);
		
		
		System.out.println("\n\nAfter Sorting...");
		for(int i=0; i<8; i++) {
			System.out.println(flavours[i] + "----->" + unitsSold[i]);
		}
		
		
		
		
		
	}
	
	
	
	
}
