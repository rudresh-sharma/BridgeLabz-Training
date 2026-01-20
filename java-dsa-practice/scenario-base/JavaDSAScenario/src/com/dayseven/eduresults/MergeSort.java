package com.dayseven.eduresults;

import java.util.ArrayList;

public class MergeSort {
	
	
	public static void mergeSort(ArrayList<Student> data, int l, int r) {
		
		if(r<=l) return;
		
		int mid = l+(r-l)/2;
		mergeSort(data,l,mid);
		mergeSort(data,mid+1,r);
		
		merge(data,l,mid,r);
		
		
		
	}

	private static void merge(ArrayList<Student> data, int l, int mid, int r) {
		// TODO Auto-generated method stub
		
		int n1 = mid-l+1;
		int n2 = r-mid;
		
		Student[] left = new Student[n1];
		Student[] right = new Student[n2];
		
		
		for(int i=0; i<n1; i++) left[i] = data.get(l+i);
		for(int j=0; j<n2; j++) right[j] = data.get(mid+1+j);
		
		
		int i=0, j=0, k=l;
		while(i<n1 && j<n2) {
			if(left[i].getScore() > right[j].getScore()) {
				data.set(k++ , left[i++]);
			}
			else {
				data.set(k++ , right[j++]);
			}
		}
		
		
		while(i<n1) 		data.set(k++ , left[i++]);
		while(j<n2) 		data.set(k++ , right[j++]);

				
	}
	
}
