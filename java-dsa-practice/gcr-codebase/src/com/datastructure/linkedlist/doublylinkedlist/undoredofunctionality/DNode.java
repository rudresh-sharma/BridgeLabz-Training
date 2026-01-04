package com.datastructure.linkedlist.doublylinkedlist.undoredofunctionality;

public class DNode {
    TextState data;
    DNode next;
    DNode prev;

    public DNode(TextState data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

