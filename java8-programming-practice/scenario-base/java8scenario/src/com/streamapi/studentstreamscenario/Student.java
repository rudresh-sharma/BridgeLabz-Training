package com.streamapi.studentstreamscenario;

import java.util.List;

public class Student {
    private String name;
    private int id;
    private String department;  // Changed from firstName
    private int age;
    private String gender;
    private String city;
    private int rank;
    private List<String> contacts;

    // Default Constructor
    public Student() {
    }

    // Parameterized Constructor
    public Student(String name, int id, String department, int age,
            String gender, String city, int rank,
            List<String> contacts) {

 this.name = name;
 this.id = id;
 this.department = department;
 this.age = age;
 this.gender = gender;
 this.city = city;
 this.rank = rank;
 this.contacts = contacts;
}


    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }


    @Override
    public String toString() {
        return 
                "\n name= " + name  +
                "\n id=" + id +
                "\n department='" + department  +
                "\n age=" + age +
                "\n gender='" + gender +
                "\n city='" + city   +
                "\n rank=" + rank +
                "\n contacts='" + contacts  + "\n"
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}