package com.datastructure.linkedlist.doublylinkedlist.undoredofunctionality;

public class UndoRedoList {
    private DNode head;
    private DNode tail;
    private DNode current;
    private int size = 0;
    private final int MAX = 10;   // history limit

    // Add new state
    public void addState(String text) {
        TextState state = new TextState(text);
        DNode newNode = new DNode(state);

        // If undo was used, remove redo history
        if (current != null && current.next != null) {
            current.next.prev = null;
            current.next = null;
            tail = current;
        }

        if (head == null) {
            head = tail = current = newNode;
            size = 1;
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        current = newNode;
        size++;

        // Maintain size limit
        if (size > MAX) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    // Undo
    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("Nothing to undo");
        }
    }

    // Redo
    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        } else {
            System.out.println("Nothing to redo");
        }
    }

    // Display current state
    public void showCurrent() {
        if (current != null)
            current.data.display();
        else
            System.out.println("No text available");
    }
}
