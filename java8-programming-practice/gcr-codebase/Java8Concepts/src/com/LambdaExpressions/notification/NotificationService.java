package com.LambdaExpressions.notification;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NotificationService {

    // Method to filter notifications based on Predicate
    public List<Notification> filterNotifications(List<Notification> notifications, Predicate<Notification> filter) {
        return notifications.stream()
                            .filter(filter)
                            .collect(Collectors.toList());
    }
}
