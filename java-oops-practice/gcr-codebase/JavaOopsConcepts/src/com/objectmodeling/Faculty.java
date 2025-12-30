package com.objectmodeling;

public class Faculty {
    private String name;

    public Faculty(String name) {
        this.name = name;
    }

    public void showFaculty() {
        System.out.println("Faculty: " + name);
    }
}
