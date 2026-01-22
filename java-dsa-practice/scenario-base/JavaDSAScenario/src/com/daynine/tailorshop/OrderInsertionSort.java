package com.daynine.tailorshop;

import java.util.ArrayList;

import com.dayeight.movietime.MovieData;

public class OrderInsertionSort {
public static void insertionsort(ArrayList<OrderData> data, int i) {
		
		OrderData key = data.get(i);
		int j = i-1;
		
		
		while(j>=0 && data.get(j).getDeadline().isAfter(key.getDeadline())) {
			data.set(j+1, data.get(j));
			j--;
		}
		
		data.set(j+1, key);
		
	}
	
	
	public static void showDetails(ArrayList<OrderData> data) {
		
		System.out.printf("%-20s%-10s\n", "Order Id", "DeadLine");
		
		for(OrderData m : data) {
			System.out.printf("%-20s%-10s\n",m.getOrderId(),m.getDeadline());
		}
		
		
	}
}
