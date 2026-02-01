package com.streamapi.transformnames;

public class Customer {
    private String name;

    // constructor
    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // good string representation
    @Override
    public String toString() {
        return name;
    }
}