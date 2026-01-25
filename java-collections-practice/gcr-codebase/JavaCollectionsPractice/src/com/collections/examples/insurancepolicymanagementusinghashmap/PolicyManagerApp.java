package com.collections.examples.insurancepolicymanagementusinghashmap;

import java.time.LocalDate;

public class PolicyManagerApp {
	public static void main(String[] args) {
		PolicyManager manager = new PolicyManager();

		// Adding sample policies
		manager.addPolicy(new Policy("P1001", "Amit Sharma", LocalDate.now().plusDays(15), "Health", 5200.0));
		manager.addPolicy(new Policy("P1002", "Priya Patel", LocalDate.now().plusDays(45), "Auto", 8900.0));
		manager.addPolicy(new Policy("P1003", "Rahul Verma", LocalDate.now().plusDays(8), "Health", 6100.0));
		manager.addPolicy(new Policy("P1004", "Sneha Gupta", LocalDate.now().minusDays(5), "Home", 14500.0));
		manager.addPolicy(new Policy("P1005", "Amit Sharma", LocalDate.now().plusDays(60), "Auto", 7200.0));

		manager.printAllPolicies("Initial State:");

		// Get policy by number
		System.out.println("\nFinding policy P1002:");
		System.out.println(manager.getPolicyByNumber("P1002"));

		// Expiring soon
		System.out.println("\nPolicies expiring in next 30 days:");
		manager.getExpiringSoon(30).forEach(System.out::println);

		// Policies by holder
		System.out.println("\nPolicies of Amit Sharma:");
		manager.getPoliciesByPolicyholder("Amit Sharma").forEach(System.out::println);

		// Remove expired
		System.out.println("\nRemoving expired policies...");
		manager.removeExpiredPolicies();

		manager.printAllPolicies("After removing expired policies:");
	}
}