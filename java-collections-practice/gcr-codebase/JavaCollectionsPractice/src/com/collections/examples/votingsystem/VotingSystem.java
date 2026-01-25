package com.collections.examples.votingsystem;

import java.util.*;

public class VotingSystem {

	// HashMap: Fast vote counting
	private final Map<String, Integer> voteCount = new HashMap<>();

	// LinkedHashMap: Keeps the order in which candidates first received votes
	private final Map<String, Integer> voteCountWithOrder = new LinkedHashMap<>();

	// TreeMap: Automatically sorted by candidate name (alphabetical)
	private final Map<String, Integer> sortedResults = new TreeMap<>();

	public void voteFor(String candidate) {
		// Update HashMap (fast lookup)
		voteCount.merge(candidate, 1, Integer::sum);

		// Update LinkedHashMap (preserves first appearance order)
		voteCountWithOrder.merge(candidate, 1, Integer::sum);

		// Update TreeMap (will be auto-sorted)
		sortedResults.merge(candidate, 1, Integer::sum);
	}

	// Get total votes for a candidate (fastest using HashMap)
	public int getVotes(String candidate) {
		return voteCount.getOrDefault(candidate, 0);
	}

	// Display results in different ways
	public void displayResults() {
		System.out.println("\n=== Current Voting Results ===\n");

		// 1. HashMap - No particular order (fastest storage)
		System.out.println("HashMap (unordered, fastest):");
		voteCount.forEach((c, v) -> System.out.printf("  %s : %d votes%n", c, v));

		System.out.println();

		// 2. LinkedHashMap - Order of first vote received
		System.out.println("LinkedHashMap (order of first vote):");
		voteCountWithOrder.forEach((c, v) -> System.out.printf("  %s : %d votes%n", c, v));

		System.out.println();

		// 3. TreeMap - Sorted alphabetically by candidate name
		System.out.println("TreeMap (sorted by candidate name):");
		sortedResults.forEach((c, v) -> System.out.printf("  %s : %d votes%n", c, v));

		System.out.println("----------------------------");
	}

	// Get winner, if tie -> returns one of them
	public String getWinner() {
		return voteCount.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey)
				.orElse("No votes yet");
	}

	public static void main(String[] args) {
		VotingSystem voting = new VotingSystem();

		System.out.println("Casting votes...");

		voting.voteFor("Rahul");
		voting.voteFor("Priya");
		voting.voteFor("Amit");
		voting.voteFor("Rahul");
		voting.voteFor("Priya");
		voting.voteFor("Sneha");
		voting.voteFor("Amit");
		voting.voteFor("Rahul");
		voting.voteFor("Priya");
		voting.voteFor("Rahul");

		voting.displayResults();

		System.out.println("Current Winner: " + voting.getWinner());
		System.out.println("Total votes for Rahul: " + voting.getVotes("Rahul"));
	}
}