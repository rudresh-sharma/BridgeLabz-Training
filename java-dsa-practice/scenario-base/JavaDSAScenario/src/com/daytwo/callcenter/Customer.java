package com.daytwo.callcenter;

public class Customer implements Comparable<Customer> {
    private String id;
    private String name;
    private boolean isVIP;

    public Customer(String id, String name, boolean isVIP) {
        this.id = id;
        this.name = name;
        this.isVIP = isVIP;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isVIP() {
        return isVIP;
    }

    // VIP customers get higher priority
    @Override
    public int compareTo(Customer other) {
        return Boolean.compare(other.isVIP, this.isVIP);
    }
}
