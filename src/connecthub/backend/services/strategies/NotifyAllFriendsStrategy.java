package connecthub.backend.services.strategies;

import connecthub.backend.models.Friendship;
import connecthub.backend.models.Notification;
import connecthub.backend.models.User;
import connecthub.backend.services.FriendshipService;
import connecthub.backend.services.UserService;

import java.io.IOException;
import java.util.List;

public class NotifyAllFriendsStrategy implements NotificationStrategy {
    private final User senderUser;

    public NotifyAllFriendsStrategy(User senderUser) {
        this.senderUser = senderUser;
    }

    @Override
    public void sendNotification(Notification notification, String receiverId) {
        Friendship friendship = null;
        try {
            friendship = new FriendshipService().loadFriendship();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UserService userService = UserService.getInstance();
        List<String> friendsId = friendship.getUserFriends(senderUser.getUserId());
        for (String friendId : friendsId) {
            User friend = userService.getUserById(friendId);
            friend.sendNotification(notification);
            userService.updateUser(friendId, friend);
        }
    }
}
