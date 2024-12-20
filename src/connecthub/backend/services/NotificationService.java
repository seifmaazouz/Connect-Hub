package connecthub.backend.services;

import connecthub.backend.models.Notification;
import connecthub.backend.models.User;
import connecthub.backend.services.strategies.NotificationStrategy;
import connecthub.backend.services.strategies.NotifyAllFriendsStrategy;
import connecthub.backend.services.strategies.NotifySpecificUserStrategy;
import connecthub.backend.utils.builders.NotificationBuilder;

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
        NotificationBuilder builder = new NotificationBuilder()
                .setType(type)
                .setSenderUserId(senderUser.getUserId())
                .setContentId(contentId);

        switch (type) {
            case FRIEND_REQUEST:
                builder.setMessage(senderUser.getUsername() + " sent you a friend request.");
                break;
            case GROUP_ACTIVITY:
                builder.setMessage("");
                break;
            case NEW_POST:
                builder.setMessage(senderUser.getUsername() + " published a new post.");
                break;
            case MESSAGE:
                builder.setMessage(senderUser.getUsername() + " sent you a message.");
                break;
            case COMMENT:
                builder.setMessage(senderUser.getUsername() + " commented on your post.");
                break;
            case LIKE:
                builder.setMessage(senderUser.getUsername() + " liked your post.");
                break;
        }
        return builder.build();
    }
}
