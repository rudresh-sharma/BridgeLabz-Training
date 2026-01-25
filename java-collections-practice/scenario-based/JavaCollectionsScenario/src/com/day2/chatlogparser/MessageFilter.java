package com.day2.chatlogparser;

public interface MessageFilter<T> {
    boolean allow(T message);
}
