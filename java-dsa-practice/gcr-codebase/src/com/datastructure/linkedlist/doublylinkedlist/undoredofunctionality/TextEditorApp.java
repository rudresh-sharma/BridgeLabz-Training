package com.datastructure.linkedlist.doublylinkedlist.undoredofunctionality;

public class TextEditorApp {
    public static void main(String[] args) {

        UndoRedoList editor = new UndoRedoList();

        editor.addState("Hello");
        editor.addState("Hello World");
        editor.addState("Hello World!");
        editor.addState("Hello World! Java");

        editor.showCurrent();   // Latest

        editor.undo();
        editor.showCurrent();

        editor.undo();
        editor.showCurrent();

        editor.redo();
        editor.showCurrent();

        editor.addState("Hello World! Java Programming");
        editor.showCurrent();
    }
}
