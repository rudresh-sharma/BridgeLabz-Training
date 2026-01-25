package com.day2.chatlogparser;

import java.util.*;

public class IdleChatFilter implements MessageFilter<ChatMessage> {

    private Set<String> idleKeywords = new HashSet<>();

    public IdleChatFilter() {
        idleKeywords.add("lol");
        idleKeywords.add("brb");
        idleKeywords.add("haha");
        idleKeywords.add("bye");
        idleKeywords.add("ok");
    }

    @Override
    public boolean allow(ChatMessage msg) {

        String text = msg.getMessage().toLowerCase();

        for (String keyword : idleKeywords) {
            if (text.contains(keyword)) {
                return false; // Filter out idle chat
            }
        }

        return true; // Productive message
    }
}
