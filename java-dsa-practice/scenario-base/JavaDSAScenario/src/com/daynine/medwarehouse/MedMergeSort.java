package com.daynine.medwarehouse;

import java.util.ArrayList;

public class MedMergeSort {

	
	 public static void mergeSort(ArrayList<MedicineData>arr, int left, int right) {
	        if (left < right) {
	            int mid = left + (right-left) / 2;

	            mergeSort(arr, left, mid);
	            mergeSort(arr, mid + 1, right);

	            merge(arr, left, mid, right);
	        }
	    }

	    private static void merge(ArrayList<MedicineData>arr, int left, int mid, int right) {

	        int n1 = mid - left + 1;
	        int n2 = right - mid;

	        ArrayList<MedicineData> L = new ArrayList<>();
	        ArrayList<MedicineData> R = new ArrayList<>();

	        for (int i = 0; i < n1; i++)
	            L.add(arr.get(left + i));

	        for (int j = 0; j < n2; j++)
	            R.add(arr.get(mid + 1 + j));

	        int i = 0, j = 0, k = left;

	        while (i < n1 && j < n2) {
	            if (L.get(i).getExpiryDate().isBefore(R.get(j).getExpiryDate())) {
	                arr.set(k++, L.get(i++));
	            } else {
	            		arr.set(k++, R.get(j++));
	            }
	          
	        }

	        while (i < n1) {
	        	 arr.set(k++, L.get(i++));
	        }

	        while (j < n2) {
        		arr.set(k++, R.get(j++));
	        }
	    }
	
	
	
	
	
	
	
}
