package com.daytwo.callcenter;

import java.util.*;

public class CallCenter {

    private Queue<Customer> normalQueue = new LinkedList<>();
    private PriorityQueue<Customer> vipQueue = new PriorityQueue<>();
    private HashMap<String, Integer> callCount = new HashMap<>();

    // Add incoming call
    public void addCall(Customer customer) {
        if (customer.isVIP()) {
            vipQueue.add(customer);
        } else {
            normalQueue.add(customer);
        }

        // Update call count
        callCount.put(customer.getId(), callCount.getOrDefault(customer.getId(), 0) + 1);

        System.out.println("Call added for: " + customer.getName());
    }

    // Process next call
    public void processCall() {
        Customer customer;

        if (!vipQueue.isEmpty()) {
            customer = vipQueue.poll();
        } else if (!normalQueue.isEmpty()) {
            customer = normalQueue.poll();
        } else {
            System.out.println("No calls in queue.");
            return;
        }

        System.out.println("Processing call of: " + customer.getName());
    }

    // Show call count
    public void showCallCount(String id) {
        int count = callCount.getOrDefault(id, 0);
        System.out.println("Customer ID " + id + " has called " + count + " times.");
    }
}
