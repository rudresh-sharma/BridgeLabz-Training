package com.collections.annotations.jsonfield;

public class User {

    @JsonField(name = "user_name")
    private String username;

    @JsonField(name = "user_email")
    private String email;

    private int age; // Not annotated → will not appear in JSON

    public User(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }

    // Getters (optional, not needed for reflection)
}
