package com.dayone.browserbuddy;

import java.util.Stack;

public class Browser {
    private Stack<Tab> closedTabs = new Stack<>();
    private Tab currentTab;

    public void openTab(String name, String homepage) {
        currentTab = new Tab(name, homepage);
        System.out.println("Opened new tab: " + name + " with homepage " + homepage);
    }

    public void closeCurrentTab() {
        if (currentTab != null) {
            closedTabs.push(currentTab);
            System.out.println("Closed tab: " + currentTab.getName());
            currentTab = null;
        } else {
            System.out.println("No tab to close!");
        }
    }

    public void reopenLastClosedTab() {
        if (!closedTabs.isEmpty()) {
            currentTab = closedTabs.pop();
            System.out.println("Reopened tab: " + currentTab.getName());
        } else {
            System.out.println("No closed tabs to reopen!");
        }
    }

    public Tab getCurrentTab() {
        return currentTab;
    }
}
