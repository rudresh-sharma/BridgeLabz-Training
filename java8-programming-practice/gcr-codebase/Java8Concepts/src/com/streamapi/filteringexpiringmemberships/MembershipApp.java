package com.streamapi.filteringexpiringmemberships;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MembershipApp {
	public static void main(String[] args) {
		List<GymMemberShip> members = new ArrayList<>();

		members.add(new GymMemberShip("Rohan", LocalDate.now().plusDays(10))); // expiring soon
        members.add(new GymMemberShip("Amit", LocalDate.now().plusDays(40)));
        members.add(new GymMemberShip("Priya", LocalDate.now().plusDays(5))); // expiring soon
        members.add(new GymMemberShip("Neha", LocalDate.now().plusDays(100)));
        members.add(new GymMemberShip("Karan", LocalDate.now().plusDays(25))); // expiring soon
        members.add(new GymMemberShip("Sneha", LocalDate.now().minusDays(5))); // already expired
        members.add(new GymMemberShip("Arjun", LocalDate.now().plusDays(60)));
        members.add(new GymMemberShip("Meera", LocalDate.now().plusDays(20))); // expiring soon
        members.add(new GymMemberShip("Vikram", LocalDate.now().minusDays(1))); // already expired
        members.add(new GymMemberShip("Anjali", LocalDate.now().plusDays(15))); // expiring
        
        
        LocalDate today = LocalDate.now();
        
        
        members.stream()
        .filter(m -> {
            long daysLeft = ChronoUnit.DAYS.between(today, m.getMembershipDate());
            return daysLeft >= 0 && daysLeft <= 30; // within next 30 days
        })
        .forEach(System.out::println);
	
	}
}
