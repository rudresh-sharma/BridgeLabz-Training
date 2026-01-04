package com.datastructure.linkedlist.singlylinkedlist.socialmediafriendconnections;

public class SocialMediaApp {
    public static void main(String[] args) {

        SocialMediaList sm = new SocialMediaList();

        sm.addUser(new User(1, "Ravi", 20));
        sm.addUser(new User(2, "Amit", 21));
        sm.addUser(new User(3, "Neha", 22));
        sm.addUser(new User(4, "Priya", 23));

        sm.addFriend(1, 2);
        sm.addFriend(1, 3);
        sm.addFriend(2, 3);

        sm.displayFriends(1);
        sm.mutualFriends(1, 2);
        sm.countFriends();
    }
}
