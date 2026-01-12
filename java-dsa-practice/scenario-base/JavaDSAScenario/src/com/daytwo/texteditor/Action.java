package com.daytwo.texteditor;

public class Action {
    String type;   // INSERT, DELETE, FORMAT
    String data;   // affected text

    public Action(String type, String data) {
        this.type = type;
        this.data = data;
    }
}
