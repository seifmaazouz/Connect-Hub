package connecthub.backend.services;

import connecthub.backend.models.Notification;
import connecthub.backend.models.User;
import connecthub.backend.services.strategies.NotificationStrategy;
import connecthub.backend.services.strategies.NotifyAllFriendsStrategy;
import connecthub.backend.services.strategies.NotifySpecificUserStrategy;

public class NotificationService {

    private final User senderUser;

    public NotificationService(User user) {
        this.senderUser = user;
    }

    public void sendNotification(Notification.Type type, String receiverId, String contentId) {
        Notification notification = createNotification(type, contentId);
        NotificationStrategy strategy;

        if (receiverId == null) {
            strategy = new NotifyAllFriendsStrategy(senderUser);
        } else {
            strategy = new NotifySpecificUserStrategy();
        }

        strategy.sendNotification(notification, receiverId);
    }

    private Notification createNotification(Notification.Type type, String contentId) {
        String message = null;
        switch (type) {
            case FRIEND_REQUEST:
                message = senderUser.getUsername() + " sent you a friend request.";
                break;
            case GROUP_ACTIVITY:
                message = "";
                break;
            case NEW_POST:
                message = senderUser.getUsername() + " published a new post.";
                break;
            case MESSAGE:
                message = senderUser.getUsername() + " sent you a message.";
                break;
            case COMMENT:
                message = senderUser.getUsername() + " commented on your post.";
                break;
            case LIKE:
                message = senderUser.getUsername() + " liked your post.";
                break;
        }
        return new Notification(message, type, senderUser.getUserId(), contentId);
    }
}
