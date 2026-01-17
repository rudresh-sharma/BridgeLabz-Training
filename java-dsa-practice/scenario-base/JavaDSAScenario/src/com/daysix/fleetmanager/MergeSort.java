package com.daysix.fleetmanager;

import java.util.ArrayList;

public class MergeSort {
	
	public static void mergeSort(ArrayList<VehicleData> data, int left, int right) {
		
		if(right<=left) return;
		int mid = left + (right-left)/2;
		
		mergeSort(data,left,mid);
		mergeSort(data,mid+1,right);
		merge(data,left,mid,right);
				
	}
	
	public static void merge(ArrayList<VehicleData> data, int left, int mid, int right) {

	    int n1 = mid - left + 1;
	    int n2 = right - mid;

	    ArrayList<VehicleData> list1 = new ArrayList<>();
	    ArrayList<VehicleData> list2 = new ArrayList<>();

	    for (int i = 0; i < n1; i++) list1.add(data.get(left + i));
	    for (int j = 0; j < n2; j++) list2.add(data.get(mid + 1 + j));

	    int i = 0, j = 0, k = left;

	    while (i < n1 && j < n2) {
	        if (list1.get(i).getMileage() <= list2.get(j).getMileage()) { // <= keeps order stable
	            data.set(k++, list1.get(i++));
	        } else {
	            data.set(k++, list2.get(j++));
	        }
	    }

	    while (i < n1) data.set(k++, list1.get(i++));
	    while (j < n2) data.set(k++, list2.get(j++));
	}

}
