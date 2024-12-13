// src/connecthub/backend/services/NotificationService.java
package connecthub.backend.services;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.backend.database.JSONParser;
import connecthub.backend.models.Notification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static connecthub.backend.constants.FilePath.NOTIFICATIONS_FILE_PATH;
import static javafx.scene.input.KeyCode.T;

public class NotificationService {
    private HashMap<String, ArrayList<Notification>> notifications;

    public NotificationService() {
        notifications = new HashMap<>();
    }

    public void addNotification(String receiverId, String message, Notification.Type type, String senderId) {
        if (!notifications.containsKey(receiverId)) {
            notifications.put(receiverId, new ArrayList<>());
        }
        notifications.get(receiverId).add(new Notification(message, type, senderId));
    }
    
    public void removeNotification(Notification notification) {
        notifications.remove(notification);
    }
    
    public HashMap<String, ArrayList<Notification>> getNotifications() throws IOException {
        HashMap<String, ArrayList<Notification>> notificationMap = new JSONParser().readJSON(NOTIFICATIONS_FILE_PATH,
                new TypeReference <HashMap<String, ArrayList<Notification>>> () {});

        for (String receiverId : notificationMap.keySet()) {
            notifications.put(receiverId, notificationMap.get(receiverId));
        }
        return notifications;
    }

    public void saveNotifications() throws IOException {
        HashMap<String, ArrayList<Notification>> notificationMap = new HashMap<>();
        for (String receiverId : notifications.keySet()) {
            notificationMap.put(receiverId, notifications.get(receiverId));
        }
        new JSONParser().writeJSON(NOTIFICATIONS_FILE_PATH, notificationMap);
    }

    public void clearNotifications(String receiverId) {
        notifications.get(receiverId).clear();
    }

    public Notification getNotification(String receiverId, String senderId, Notification.Type type) {
        for (Notification notification : notifications.get(receiverId)) {
            if (notification.getSenderId().equals(senderId) && notification.getType().equals(type)) {
                return notification;
            }
        }
        return null;
    }
}