package com.datastructure.linkedlist.doublylinkedlist.moviemanagementsystem;

public class DNode {
    Movie data;
    DNode next;
    DNode prev;

    public DNode(Movie data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
