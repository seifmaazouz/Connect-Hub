package connecthub.backend.services.strategies;

import connecthub.backend.models.Notification;
import connecthub.backend.models.User;
import connecthub.backend.services.UserService;

public class NotifySpecificUserStrategy implements NotificationStrategy {

    @Override
    public void sendNotification(Notification notification, String receiverId) {
        UserService userService = UserService.getInstance();
        User receiver = userService.getUserById(receiverId);
        receiver.sendNotification(notification);
        userService.updateUser(receiverId, receiver);
    }
}
