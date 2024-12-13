// src/connecthub/backend/services/NotificationService.java
package connecthub.backend.services;

import connecthub.backend.models.Notification;
import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private List<Notification> notifications;

    public NotificationService() {
        notifications = new ArrayList<>();
    }

    public void addNotification(String receiverId, String message, Notification.Type type, String senderId) {
        notifications.add(new Notification(receiverId, message, type, senderId));
    }
    
    public void removeNotification(Notification notification) {
        notifications.remove(notification);
    }
    
    public List<Notification> getNotifications() {
        return notifications;
    }

    public void clearNotifications() {
        notifications.clear();
    }
}