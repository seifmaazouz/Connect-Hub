package connecthub.backend.services;

import connecthub.backend.models.Friendship;
import connecthub.backend.models.Notification;
import connecthub.backend.models.User;
import java.io.IOException;
import java.util.List;

public class NotificationService {

    private User senderUser;

    public NotificationService(User user) {
        this.senderUser = user;
    }

    public void sendNotificationToFriends(Notification.Type type, String receiverId) {
        try {
            Friendship friendship = new FriendshipService().loadFriendship();
            List<String> friendsId = friendship.getUserFriends(senderUser.getUserId());
            UserService userService = UserService.getInstance();
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
            }
            Notification notification = new Notification(message, type, senderUser.getUserId());
            if (receiverId == null) {
                for (String friendId : friendsId) {
                    User friend = userService.getUserById(friendId);
                    friend.sendNotification(notification);
                    userService.updateUser(friendId, friend);
                    userService.updateUser(senderUser.getUserId(), senderUser);
                }
            } else {
                User receiver = userService.getUserById(receiverId);
                receiver.sendNotification(notification);
                userService.updateUser(receiverId, receiver);
                userService.updateUser(senderUser.getUserId(), senderUser);
            }
        } catch (IOException e) {
            System.out.println("No Friends.");
        }
    }
}
