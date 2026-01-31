package com.methodreferences.nameuppercasing;
public class Employee {
    private String name;
    private String id;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
