package connecthub.services.friendship;

import connecthub.models.User;

public class FriendManager {
    private final User user;

    public FriendManager(User user) {
        this.user = user;
    }

    public void addFriend(User friend) {
        // Add friend to user's friend list
        user.getFriendShip().addUserToFriends(friend);
        friend.getFriendShip().addUserToFriends(user);
    }

    public void removeFriend(User friend) {
        // Remove friend from user's friend list
        user.getFriendShip().removeUserFromFriends(friend);
        friend.getFriendShip().removeUserFromFriends(user);
    }
}
