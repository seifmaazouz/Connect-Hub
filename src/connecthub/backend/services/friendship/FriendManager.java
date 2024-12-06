package connecthub.backend.services.friendship;

import connecthub.backend.models.User;

public class FriendManager {
    private final User user;

    public FriendManager(User user) {
        this.user = user;
    }

    public void addFriend(User friend) {
        // Add friend to user's friend list
        user.getFriendship().addUserToFriends(friend);
        friend.getFriendship().addUserToFriends(user);
    }

    public void removeFriend(User friend) {
        // Remove friend from user's friend list
        user.getFriendship().removeUserFromFriends(friend);
        friend.getFriendship().removeUserFromFriends(user);
    }
}
