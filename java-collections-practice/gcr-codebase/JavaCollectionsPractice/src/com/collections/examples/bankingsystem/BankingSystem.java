package com.collections.examples.bankingsystem;

import java.util.*;

public class BankingSystem {

	// Fast lookup: account number -> balance
	private final Map<String, Double> accounts = new HashMap<>();

	// Sorted by balance (highest first)
	private final TreeMap<Double, List<String>> sortedByBalance = new TreeMap<>(Collections.reverseOrder());

	// Queue for withdrawal requests (FIFO)
	private final Queue<WithdrawalRequest> withdrawalQueue = new LinkedList<>();

	// Request class
	static class WithdrawalRequest {
		String accountNumber;
		double amount;

		public WithdrawalRequest(String accountNumber, double amount) {
			this.accountNumber = accountNumber;
			this.amount = amount;
		}

		@Override
		public String toString() {
			return "Withdraw $" + amount + " from " + accountNumber;
		}
	}

	// Add money to account
	public void deposit(String accountNumber, double amount) {
		double oldBalance = accounts.getOrDefault(accountNumber, 0.0);
		double newBalance = oldBalance + amount;
		accounts.put(accountNumber, newBalance);
		updateSortedBalance(accountNumber, oldBalance, newBalance);
		System.out.printf("Deposited $%.2f to %s -> Balance: $%.2f%n", amount, accountNumber, newBalance);
	}

	// Add withdrawal request to queue
	public void requestWithdrawal(String accountNumber, double amount) {
		double balance = accounts.getOrDefault(accountNumber, 0.0);
		if (balance < amount) {
			System.out.printf("Insufficient funds in %s (Balance: $%.2f, Requested: $%.2f)%n", accountNumber, balance,
					amount);
			return;
		}
		withdrawalQueue.offer(new WithdrawalRequest(accountNumber, amount));
		System.out.printf("Withdrawal request of $%.2f from %s added to queue.%n", amount, accountNumber);
	}

	// Process all pending withdrawals
	public void processWithdrawals() {
		if (withdrawalQueue.isEmpty()) {
			System.out.println("No withdrawal requests pending.");
			return;
		}
		System.out.println("\nProcessing withdrawal requests...\n");
		while (!withdrawalQueue.isEmpty()) {
			WithdrawalRequest req = withdrawalQueue.poll();
			double currentBalance = accounts.get(req.accountNumber);
			double newBalance = currentBalance - req.amount;
			accounts.put(req.accountNumber, newBalance);
			updateSortedBalance(req.accountNumber, currentBalance, newBalance);
			System.out.printf("Processed: %s -> New Balance: $%.2f%n", req, newBalance);
		}
		System.out.println();
	}

	// Update TreeMap when balance changes
	private void updateSortedBalance(String acc, double oldBal, double newBal) {
		if (oldBal > 0) {
			sortedByBalance.getOrDefault(oldBal, new ArrayList<>()).remove(acc);
			if (sortedByBalance.get(oldBal).isEmpty()) {
				sortedByBalance.remove(oldBal);
			}
		}
		sortedByBalance.computeIfAbsent(newBal, _ -> new ArrayList<>()).add(acc);
	}

	// Show current state of all collections
	public void displayAccounts() {
		System.out.println("\n=== Banking System Status ===\n");
		System.out.println("HashMap (fast lookup):");
		accounts.forEach((acc, bal) -> System.out.printf("  %s -> $%.2f%n", acc, bal));
		System.out.println("\nTreeMap (sorted by balance - richest first):");
		sortedByBalance.forEach((bal, accList) -> {
			for (String acc : accList) {
				System.out.printf("  $%.2f -> %s%n", bal, acc);
			}
		});
		System.out.println("\nPending Withdrawal Queue: " + withdrawalQueue);
		System.out.println("----------------------------------------\n");
	}

	public static void main(String[] args) {
		BankingSystem bank = new BankingSystem();
		bank.deposit("ACC001", 50000);
		bank.deposit("ACC002", 150000);
		bank.deposit("ACC003", 75000);
		bank.deposit("ACC004", 20000);
		bank.displayAccounts();
		bank.requestWithdrawal("ACC002", 30000);
		bank.requestWithdrawal("ACC001", 10000);
		bank.requestWithdrawal("ACC003", 80000);
		bank.requestWithdrawal("ACC004", 5000);
		bank.displayAccounts();
		bank.processWithdrawals();
		bank.displayAccounts();
	}
}