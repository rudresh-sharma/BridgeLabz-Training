package com.streamapi.foreach.emailnotification;

import java.util.ArrayList;
import java.util.List;

public class EmailNotificationApp {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        users.add(new User("Rohan", "rohan.sharma@gmail.com"));
        users.add(new User("Priya", "priya.jain@yahoo.com"));
        users.add(new User("Amit", "amit.patel@outlook.com"));
        users.add(new User("Sneha", "sneha.verma@hotmail.com"));
        users.add(new User("Vikram", "vikram.singh@proton.me"));
        users.add(new User("Neha", "neha.gupta@gmail.com"));

        System.out.println("Sending Welcome / Update Notification Emails");
        System.out.println("---------------------------------------------");

        users.stream()
             .forEach(user -> {
                 String email = user.getEmail();
                 String name = user.getName();

                 System.out.println("To: " + email);
                 System.out.println("Subject: Important Update - Gym Membership / Event / Offer");
                 System.out.println("Dear " + name + ",");
                 System.out.println("We hope you're doing well!");
                 System.out.println("This is a quick notification from our team.");
                 System.out.println("Stay tuned for more updates.");
                 System.out.println("Best regards,");
                 System.out.println("Team Support");
                 System.out.println("---------------------------------------------");
             });

        System.out.println("All " + users.size() + " notification emails sent.");
    }
}