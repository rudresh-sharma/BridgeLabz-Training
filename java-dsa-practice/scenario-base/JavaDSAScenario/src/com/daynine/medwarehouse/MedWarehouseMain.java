package com.daynine.medwarehouse;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class MedWarehouseMain {
	
	public static void main(String[] args) {
		
		ArrayList<MedicineData> finalData = new ArrayList<>();
		
	    // ArrayList 1: Tablets
        ArrayList<MedicineData> tablets = new ArrayList<>();
        tablets.add(new MedicineData("Paracetamol", LocalDate.parse("2026-05-10")));
        tablets.add(new MedicineData("Aspirin",     LocalDate.parse("2026-06-12")));
        tablets.add(new MedicineData("Ibuprofen",   LocalDate.parse("2026-07-15")));
        tablets.add(new MedicineData("Crocin",      LocalDate.parse("2026-09-25")));
        tablets.add(new MedicineData("Brufen",      LocalDate.parse("2026-09-25")));

        // ArrayList 2: Syrups
        ArrayList<MedicineData> syrups = new ArrayList<>();
        syrups.add(new MedicineData("Benadryl",    LocalDate.parse("2025-12-10")));
        syrups.add(new MedicineData("Corex",       LocalDate.parse("2025-11-18")));
        syrups.add(new MedicineData("Ascoril",     LocalDate.parse("2026-01-05")));
        syrups.add(new MedicineData("Zedex",       LocalDate.parse("2026-02-14")));
        syrups.add(new MedicineData("Torex",       LocalDate.parse("2026-02-10")));

        // ArrayList 3: Injections
        ArrayList<MedicineData> injections = new ArrayList<>();
        injections.add(new MedicineData("Insulin",     LocalDate.parse("2025-10-01")));
        injections.add(new MedicineData("HepatitisB",  LocalDate.parse("2026-04-11")));
        injections.add(new MedicineData("Tetanus",     LocalDate.parse("2026-06-22")));
        injections.add(new MedicineData("Rabies",      LocalDate.parse("2026-07-19")));
        injections.add(new MedicineData("VitaminB12",  LocalDate.parse("2026-08-08")));
		
		
		
        
        finalData.addAll(injections);
        finalData.addAll(syrups);
        finalData.addAll(tablets);
        
        
        MedMergeSort.mergeSort(finalData,0,finalData.size()-1);
        
        LocalDate today = LocalDate.now();
        System.out.printf("%-20s%-20s%-20s\n\n","Medicine Name", "Expiry Date", "Days Left");
        int noOfMedExpired = 0;
        for(MedicineData m : finalData) {
            LocalDate expiry = m.getExpiryDate();
            long daysDiff = ChronoUnit.DAYS.between(today, expiry);

            if (daysDiff <= 30 && daysDiff >= 0) {
                // Near expiry
            	System.err.printf("%-20s%-20s%-20d\n",m.getMedName(), m.getExpiryDate(),daysDiff);
            }
            else if(daysDiff>30){
            	System.out.printf("%-20s%-20s%-20d\n",m.getMedName(), m.getExpiryDate(),daysDiff);

            }
            else {
            		noOfMedExpired++;
            }
        }
        
        
        System.err.printf("\n\n%-40s%-5d","No Of Medicine Expired",noOfMedExpired);
		
	}
	
	
	
	
	
	
}
