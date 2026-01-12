package com.daytwo.texteditor;

import java.util.Stack;

public class TextEditor {

    private StringBuilder text = new StringBuilder();
    private Stack<Action> undoStack = new Stack<>();
    private Stack<Action> redoStack = new Stack<>();

    // Insert text
    public void insert(String value) {
        text.append(value);
        undoStack.push(new Action("INSERT", value));
        redoStack.clear();
    }

    // Delete last n characters
    public void delete(int n) {
        if (n > text.length()) {
            System.out.println("Not enough characters to delete.");
            return;
        }

        String deleted = text.substring(text.length() - n);
        text.delete(text.length() - n, text.length());

        undoStack.push(new Action("DELETE", deleted));
        redoStack.clear();
    }

    // Format = convert to uppercase
    public void formatUpper() {
        String oldText = text.toString();
        text = new StringBuilder(text.toString().toUpperCase());

        undoStack.push(new Action("FORMAT", oldText));
        redoStack.clear();
    }

    // Undo last operation
    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }

        Action action = undoStack.pop();

        if (action.type.equals("INSERT")) {
            text.delete(text.length() - action.data.length(), text.length());
        }
        else if (action.type.equals("DELETE")) {
            text.append(action.data);
        }
        else if (action.type.equals("FORMAT")) {
            text = new StringBuilder(action.data);
        }

        redoStack.push(action);
    }

    // Redo last undone operation
    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo.");
            return;
        }

        Action action = redoStack.pop();

        if (action.type.equals("INSERT")) {
            text.append(action.data);
        }
        else if (action.type.equals("DELETE")) {
            text.delete(text.length() - action.data.length(), text.length());
        }
        else if (action.type.equals("FORMAT")) {
            text = new StringBuilder(text.toString().toUpperCase());
        }

        undoStack.push(action);
    }

    // Display current text
    public void showText() {
        System.out.println("Current Text: " + text);
    }
}
