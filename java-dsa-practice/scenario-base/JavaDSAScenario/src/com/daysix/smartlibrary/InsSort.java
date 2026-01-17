package com.daysix.smartlibrary;

import java.util.Scanner;
import java.util.ArrayList;
public class InsSort {
	
	public static void insshort(ArrayList<Books> userBooks, int i) {
		
		Books key = userBooks.get(i);
		
		int j = i-1;
		
		while(j>=0 && userBooks.get(i).getTitle().compareToIgnoreCase(key.getTitle())<0) {
			Books temp = userBooks.get(j);
			userBooks.set(j+1, temp);
			j--;
		}
		
		userBooks.set(j+1, key);
		
		
		
		
		
		
	}
	
	
	
}
