package com.datastructure.linkedlist.doublylinkedlist.undoredofunctionality;

public class TextState {
    String content;

    public TextState(String content) {
        this.content = content;
    }

    public void display() {
        System.out.println("Current Text: " + content);
    }
}
