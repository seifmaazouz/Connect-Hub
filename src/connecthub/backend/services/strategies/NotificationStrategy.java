package connecthub.backend.services.strategies;

import connecthub.backend.models.Notification;

public interface NotificationStrategy {
    void sendNotification(Notification notification, String receiverId);
}
