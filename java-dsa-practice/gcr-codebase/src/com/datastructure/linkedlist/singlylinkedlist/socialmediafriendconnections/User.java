package com.datastructure.linkedlist.singlylinkedlist.socialmediafriendconnections;

public class User {
    int userId;
    String name;
    int age;
    FriendNode friends;   // head of friend list

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friends = null;
    }

    public void display() {
        System.out.println("ID: " + userId + ", Name: " + name + ", Age: " + age);
    }
}
