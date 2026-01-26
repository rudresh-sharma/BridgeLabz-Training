package com.jsondata.question6;

import java.util.List;

public class Student {
    private String name;
    private int age;
    private String email;
    private List<String> subjects;

    // Constructor
    public Student(String name, int age, String email, List<String> subjects) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.subjects = subjects;
    }

    // Getters and Setters (required by Jackson)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<String> getSubjects() { return subjects; }
    public void setSubjects(List<String> subjects) { this.subjects = subjects; }
}
