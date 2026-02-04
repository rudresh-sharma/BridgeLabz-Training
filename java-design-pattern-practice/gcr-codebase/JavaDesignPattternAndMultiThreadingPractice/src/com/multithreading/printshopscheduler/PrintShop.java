package com.multithreading.printshopscheduler;

import java.util.ArrayList;
import java.util.List;

public class PrintShop {
    public static void main(String[] args) {
	System.out.println("====== Print Shop Job Scheduler ======\n");
	System.out.println("Starting print jobs...\n");

	long startTime = System.currentTimeMillis();

	// Create print jobs with different sizes and priorities
	List<PrintJob> jobs = new ArrayList<>();
	jobs.add(new PrintJob("Job1", 10, 5)); // Medium
	jobs.add(new PrintJob("Job2", 5, 8)); // High
	jobs.add(new PrintJob("Job3", 15, 3)); // Low
	jobs.add(new PrintJob("Job4", 8, 6)); // Medium
	jobs.add(new PrintJob("Job5", 12, 7)); // High

	// Create and start threads
	List<Thread> threads = new ArrayList<>();

	for (PrintJob job : jobs) {
	    Thread t = new Thread(job, job.getClass().getSimpleName() + "-" + job.getJobName());
	    t.setPriority(job.getPriority()); // Set priority again (optional reinforcement)
	    threads.add(t);
	    t.start();
	}

	// Wait for all print jobs to complete
	for (Thread t : threads) {
	    try {
		t.join();
	    } catch (InterruptedException e) {
		System.out.println("Main thread interrupted");
		Thread.currentThread().interrupt();
	    }
	}

	long endTime = System.currentTimeMillis();
	long totalTimeMs = endTime - startTime;

	System.out.println("\n=====================================");
	System.out.printf("   All jobs completed in %d ms%n", totalTimeMs);
	System.out.println("=====================================");
    }
}