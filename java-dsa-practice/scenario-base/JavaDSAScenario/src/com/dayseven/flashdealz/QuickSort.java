package com.dayseven.flashdealz;

import java.util.ArrayList;

public class QuickSort {
	
	public static int partition(ArrayList<Product> list, int low , int high) {
		
		Product pivot = list.get(high);
		int i = low-1;
		
		for(int j = low; j<= high-1 ; j++) {
			if(list.get(j).getDiscount() > pivot.getDiscount()) {
				i++;
				
				// swap
				
				Product temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
			}
		}
		i++;
		Product temp = list.get(i);
		list.set(i, pivot);
		list.set(high, temp);
		return i;

	}
	
	public static void quickSort(ArrayList<Product> list, int low , int high) {
		if(low<high) {
			
			int pidx = partition(list, low, high);
			
			quickSort(list, low, pidx-1);
			quickSort(list, pidx+1, high);
			
		}
	}

}