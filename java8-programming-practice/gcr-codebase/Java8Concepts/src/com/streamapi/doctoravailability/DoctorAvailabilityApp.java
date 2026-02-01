package com.streamapi.doctoravailability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DoctorAvailabilityApp {
	public static void main(String[] args) {

		 List<Doctor> doctors = new ArrayList<>();

	        doctors.add(new Doctor("Dr. Sharma", "Cardiologist",
	                Arrays.asList("Monday", "Wednesday", "Friday")));

	        doctors.add(new Doctor("Dr. Mehta", "Dermatologist",
	                Arrays.asList("Tuesday", "Thursday")));

	        doctors.add(new Doctor("Dr. Kapoor", "Neurologist",
	                Arrays.asList("Monday", "Tuesday", "Saturday")));

	        doctors.add(new Doctor("Dr. Verma", "Orthopedic",
	                Arrays.asList("Wednesday", "Friday","Sunday")));

	        doctors.add(new Doctor("Dr. Reddy", "Pediatrician",
	                Arrays.asList("Monday", "Thursday")));

	        doctors.add(new Doctor("Dr. Iyer", "ENT Specialist",
	                Arrays.asList("Tuesday", "Friday")));

	        doctors.add(new Doctor("Dr. Singh", "General Physician",
	                Arrays.asList("Monday", "Tuesday", "Wednesday")));

	        doctors.add(new Doctor("Dr. Khan", "Psychiatrist",
	                Arrays.asList("Thursday", "Saturday","Sunday")));

	        doctors.add(new Doctor("Dr. Joshi", "Oncologist",
	                Arrays.asList("Wednesday", "Friday", "Saturday","Sunday")));

	        doctors.add(new Doctor("Dr. Patel", "Gynecologist",
	                Arrays.asList("Monday", "Thursday", "Saturday","Sunday")));

	    Comparator<Doctor> comparator = Comparator.comparing(Doctor::getSpecialty);
        doctors.stream().filter(d -> (d.getWorkingDays().contains("Saturday") && d.getWorkingDays().contains("Sunday"))).sorted(comparator).toList().forEach(System.out::println);
        
        
        
        
        
        
        
        
        
        
	}
}
