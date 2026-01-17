package com.daysix.fleetmanager;

import java.util.Scanner;
import java.util.ArrayList;
public class FleetManagerApp {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		ArrayList<VehicleData> list1 = new ArrayList<>();		
		ArrayList<VehicleData> list2 = new ArrayList<>();
		ArrayList<VehicleData> list3 = new ArrayList<>();
		
		
		 // Depot 1
        list1.add(new VehicleData("V101", 12000));
        list1.add(new VehicleData("V102", 15000));
        list1.add(new VehicleData("V103", 18000));
        list1.add(new VehicleData("V104", 20000));
        list1.add(new VehicleData("V105", 22000));

        // Depot 2
        list2.add(new VehicleData("V201", 11000));
        list2.add(new VehicleData("V202", 14000));
        list2.add(new VehicleData("V203", 17000));
        list2.add(new VehicleData("V204", 19000));
        list2.add(new VehicleData("V205", 21000));

        // Depot 3
        list3.add(new VehicleData("V301", 13000));
        list3.add(new VehicleData("V302", 16000));
        list3.add(new VehicleData("V303", 18000));
        list3.add(new VehicleData("V304", 20000));
        list3.add(new VehicleData("V305", 23000));
		
		
		
		ArrayList<VehicleData> finalList = new ArrayList<>();
		
		finalList.addAll(list1);
		finalList.addAll(list2);
		finalList.addAll(list3);
		
		
		MergeSort.mergeSort(finalList, 0, finalList.size()-1);
		
		
		
		for(VehicleData v : finalList) {
			System.out.println(v);
		}
		
	}
		
}
