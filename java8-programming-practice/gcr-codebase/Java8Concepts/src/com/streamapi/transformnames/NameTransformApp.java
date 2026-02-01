package com.streamapi.transformnames;

import java.util.ArrayList;
import java.util.List;

public class NameTransformApp {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();

        customers.add(new Customer("rohan sharma"));
        customers.add(new Customer("priya jain"));
        customers.add(new Customer("amit patel"));
        customers.add(new Customer("sneha verma"));
        customers.add(new Customer("vikram singh"));
        customers.add(new Customer("neha gupta"));
        customers.add(new Customer("rahul joshi"));

        System.out.println("Customer Names - Uppercase & Sorted Alphabetically");
        System.out.println("-------------------------------------------------");

        customers.stream()
                 .map(c -> c.getName().toUpperCase())
                 .sorted()
                 .forEach(System.out::println);

        System.out.println("-------------------------------------------------");
        System.out.println("Total customers: " + customers.size());
    }
}