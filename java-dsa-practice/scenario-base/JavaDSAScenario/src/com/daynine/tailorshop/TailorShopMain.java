package com.daynine.tailorshop;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.dayeight.movietime.MovieData;
import com.dayeight.movietime.MovieInsertionSort;
public class TailorShopMain {
	
	public static void main(String[] args) {
		
Scanner sc = new Scanner(System.in);
		
		
		
		ArrayList<OrderData> orderdata = new ArrayList<>();		
		int isAdd = 1;
		int i=0;
			while(isAdd == 1) {
				
				System.out.println("Enter Order Id:");
				String orderId = sc.nextLine();
				
				System.out.println("Enter deadline data(yyyy-M-d)");
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
				LocalDate deadline = LocalDate.parse(sc.nextLine(), formatter);

				
				orderdata.add(new OrderData(orderId, deadline));
				
				OrderInsertionSort.insertionsort(orderdata, i);
				i++;
				System.out.println("Enter 1 to see sorted ELSE 0 ");
				int isWant = sc.nextInt();
				sc.nextLine();
				if(isWant  == 1) {
					OrderInsertionSort.showDetails(orderdata);
				}
				System.out.print("Want to add order(1 for yes 0 for no)");
				
				isAdd = sc.nextInt();
				sc.nextLine();
			}
		
				
	}
	
	
	
	
}
