package com.day1.feedbackguru;

public class Feedback<T> {

    private T type;              // e.g., "Service", "Product"
    private String message;     // full feedback line
    private int rating;         // extracted rating (1–10)

    public Feedback(T type, String message, int rating) {
        this.type = type;
        this.message = message;
        this.rating = rating;
    }

    public T getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "[" + type + "] Rating: " + rating + "/10 -> " + message;
    }
}
