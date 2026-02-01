package com.streamapi.foreach.emailnotification;

public class User {
    private String email;
    private String name;

    // constructor
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}