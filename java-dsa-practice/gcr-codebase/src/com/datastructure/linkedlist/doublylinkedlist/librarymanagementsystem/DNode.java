package com.datastructure.linkedlist.doublylinkedlist.librarymanagementsystem;

public class DNode {
    Book data;
    DNode next;
    DNode prev;

    public DNode(Book data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
