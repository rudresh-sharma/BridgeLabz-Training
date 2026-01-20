package com.dayseven.eduresults;

import java.util.ArrayList;

public class MainApp {
	
	public static void main(String[] args) {
		
		// District one data
		 ArrayList<Student> district1 = new ArrayList<>();
	        district1.add(new Student("D1S1", "Delhi", 85));
	        district1.add(new Student("D1S2", "Delhi", 78));
	        district1.add(new Student("D1S3", "Delhi", 90));
	        district1.add(new Student("D1S4", "Delhi", 72));
	        district1.add(new Student("D1S5", "Delhi", 88));

	        // District 2 list
	        ArrayList<Student> district2 = new ArrayList<>();
	        district2.add(new Student("D2S1", "Mumbai", 81));
	        district2.add(new Student("D2S2", "Mumbai", 75));
	        district2.add(new Student("D2S3", "Mumbai", 92));
	        district2.add(new Student("D2S4", "Mumbai", 69));
	        district2.add(new Student("D2S5", "Mumbai", 86));

	        // District 3 list
	        ArrayList<Student> district3 = new ArrayList<>();
	        district3.add(new Student("D3S1", "Chennai", 88));
	        district3.add(new Student("D3S2", "Chennai", 74));
	        district3.add(new Student("D3S3", "Chennai", 91));
	        district3.add(new Student("D3S4", "Chennai", 70));
	        district3.add(new Student("D3S5", "Chennai", 84));

	        // District 4 list
	        ArrayList<Student> district4 = new ArrayList<>();
	        district4.add(new Student("D4S1", "Kolkata", 79));
	        district4.add(new Student("D4S2", "Kolkata", 83));
	        district4.add(new Student("D4S3", "Kolkata", 87));
	        district4.add(new Student("D4S4", "Kolkata", 76));
	        district4.add(new Student("D4S5", "Kolkata", 90));

	        // District 5 list
	        ArrayList<Student> district5 = new ArrayList<>();
	        district5.add(new Student("D5S1", "Bangalore", 82));
	        district5.add(new Student("D5S2", "Bangalore", 77));
	        district5.add(new Student("D5S3", "Bangalore", 89));
	        district5.add(new Student("D5S4", "Bangalore", 73));
	        district5.add(new Student("D5S5", "Bangalore", 91));
		
	        
	        
	        ArrayList<Student> finalList = new ArrayList<>();
	        
	        finalList.addAll(district1);
	        finalList.addAll(district2);
	        finalList.addAll(district3);
	        finalList.addAll(district4);
	        finalList.addAll(district5);
	        
	        
	        
	        MergeSort.mergeSort(finalList, 0, finalList.size()-1);

	        
	        System.out.printf("%-13s%-10s%-10s\n", "District", "EnrollNo", "Score");
	        System.out.print("_________________________________\n");
	        for(Student s : finalList) {
	        		System.out.printf("%-13s%-10s%-10d",s.getDistrict(),s.getEnrollNo(),s.getScore());
	        		System.out.println();
	        }
		
		
		
	}
	
	
	
	
}
