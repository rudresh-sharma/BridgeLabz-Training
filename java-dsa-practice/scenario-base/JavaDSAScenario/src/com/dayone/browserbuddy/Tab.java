package com.dayone.browserbuddy;

public class Tab {
    private String name;
    private HistoryNode current; // pointer to current page

    public Tab(String name, String homepage) {
        this.name = name;
        current = new HistoryNode(homepage);
    }

    public String getName() {
        return name;
    }

    public void visit(String url) {
        HistoryNode newNode = new HistoryNode(url);
        newNode.prev = current;
        current.next = newNode;
        current = newNode;
        System.out.println("Visited: " + url);
    }

    public void back() {
        if (current.prev != null) {
            current = current.prev;
            System.out.println("Back to: " + current.url);
        } else {
            System.out.println("No previous page!");
        }
    }

    public void forward() {
        if (current.next != null) {
            current = current.next;
            System.out.println("Forward to: " + current.url);
        } else {
            System.out.println("No forward page!");
        }
    }

    public void showCurrent() {
        System.out.println("Current Page: " + current.url);
    }

    public void showHistory() {
        System.out.println("Full History in this tab:");
        HistoryNode temp = current;
        while (temp.prev != null) temp = temp.prev; // go to start
        while (temp != null) {
            System.out.println(" - " + temp.url);
            temp = temp.next;
        }
    }
}
