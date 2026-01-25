package com.day2.chatlogparser;

public class ChatMessage {

    private String timestamp;
    private String user;
    private String message;

    public ChatMessage(String timestamp, String user, String message) {
        this.timestamp = timestamp;
        this.user = user;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + user + ": " + message;
    }
}
