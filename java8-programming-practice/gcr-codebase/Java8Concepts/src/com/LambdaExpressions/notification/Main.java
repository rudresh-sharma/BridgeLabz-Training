package com.LambdaExpressions.notification;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        // Sample notifications
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification("Medication", "Patient needs medication at 10 AM"));
        notifications.add(new Notification("Appointment", "Patient appointment at 2 PM"));
        notifications.add(new Notification("Emergency", "Patient heart rate abnormal"));
        notifications.add(new Notification("Lab Result", "Blood test results available"));

        // User preference: Only show Emergency and Medication alerts
        Predicate<Notification> userPreference = n -> 
            n.getType().equals("Emergency") || n.getType().equals("Medication");

        NotificationService service = new NotificationService();
        List<Notification> filtered = service.filterNotifications(notifications, userPreference);

        // Display filtered notifications
        System.out.println("Filtered Notifications:");
        filtered.forEach(System.out::println);
        
        
    }
}
