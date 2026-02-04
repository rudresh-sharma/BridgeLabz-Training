package com.multithreading.printshopscheduler;

public class PrintJob implements Runnable {
    private final String jobName;
    private final int totalPages;
    private final int priority; // 1 = lowest, 10 = highest

    public PrintJob(String jobName, int pages, int priority) {
	this.jobName = jobName;
	this.totalPages = pages;
	this.priority = Math.max(1, Math.min(10, priority)); // clamp between 1–10
    }

    public int getPriority() {
	return priority;
    }

    @Override
    public void run() {
	Thread current = Thread.currentThread();
	current.setPriority(priority); // Set thread priority

	String priorityLabel = getPriorityLabel(priority);

	System.out.println("[" + priorityLabel + "] Starting job: " + getJobName() + " (" + totalPages + " pages, priority "
		+ priority + ")");

	try {
	    for (int page = 1; page <= totalPages; page++) {
		// Simulate printing one page
		Thread.sleep(100);

		System.out.printf("[%s] Printing %s - Page %d of %d%n", priorityLabel, getJobName(), page, totalPages);

		// Small chance (makes priority effect more visible)
		if (Math.random() < 0.15) {
		    Thread.yield();
		}
	    }

	    System.out.println("[" + priorityLabel + "] Completed: " + getJobName());

	} catch (InterruptedException e) {
	    System.out.println("[" + getJobName() + "] Printing interrupted!");
	    Thread.currentThread().interrupt();
	}
    }

    private String getPriorityLabel(int prio) {
	if (prio >= 8)
	    return "High Priority";
	if (prio >= 5)
	    return "Medium Priority";
	return "Low Priority";
    }

    public String getJobName() {
	return jobName;
    }
}