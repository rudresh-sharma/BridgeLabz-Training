package com.datastructure.linkedlist.singlylinkedlist.socialmediafriendconnections;

public class SocialMediaList {
    UserNode head;

    // Add new user
    public void addUser(User user) {
        UserNode newNode = new UserNode(user);
        newNode.next = head;
        head = newNode;
    }

    // Find user by ID
    private User findUser(int id) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.data.userId == id)
                return temp.data;
            temp = temp.next;
        }
        return null;
    }

    // Add friend to a user's friend list
    private void addFriendToList(User user, int friendId) {
        FriendNode newNode = new FriendNode(friendId);

        if (user.friends == null) {
            user.friends = newNode;
            return;
        }

        FriendNode temp = user.friends;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    // Add friend connection (both sides)
    public void addFriend(int id1, int id2) {
        User u1 = findUser(id1);
        User u2 = findUser(id2);

        if (u1 == null || u2 == null) {
            System.out.println("User not found");
            return;
        }

        addFriendToList(u1, id2);
        addFriendToList(u2, id1);

        System.out.println("Friend connection added");
    }

    // Display friends of a user
    public void displayFriends(int id) {
        User user = findUser(id);

        if (user == null) {
            System.out.println("User not found");
            return;
        }

        System.out.print("Friends of " + user.name + ": ");
        FriendNode temp = user.friends;

        if (temp == null) {
            System.out.println("No friends");
            return;
        }

        while (temp != null) {
            System.out.print(temp.friendId + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Count friends for each user
    public void countFriends() {
        UserNode temp = head;

        while (temp != null) {
            int count = 0;
            FriendNode f = temp.data.friends;

            while (f != null) {
                count++;
                f = f.next;
            }

            System.out.println(temp.data.name + " has " + count + " friends");
            temp = temp.next;
        }
    }

    // Search user by ID
    public void searchById(int id) {
        User u = findUser(id);
        if (u != null)
            u.display();
        else
            System.out.println("User not found");
    }

    // Search user by name
    public void searchByName(String name) {
        UserNode temp = head;

        while (temp != null) {
            if (temp.data.name.equalsIgnoreCase(name)) {
                temp.data.display();
                return;
            }
            temp = temp.next;
        }

        System.out.println("User not found");
    }

    // Find mutual friends
    public void mutualFriends(int id1, int id2) {
        User u1 = findUser(id1);
        User u2 = findUser(id2);

        if (u1 == null || u2 == null) {
            System.out.println("User not found");
            return;
        }

        System.out.println("Mutual Friends:");
        FriendNode f1 = u1.friends;

        boolean found = false;

        while (f1 != null) {
            FriendNode f2 = u2.friends;

            while (f2 != null) {
                if (f1.friendId == f2.friendId) {
                    System.out.println("Friend ID: " + f1.friendId);
                    found = true;
                }
                f2 = f2.next;
            }
            f1 = f1.next;
        }

        if (!found)
            System.out.println("No mutual friends");
    }
}

