package main.java.connecthub.backend.services.friendship;

import main.java.connecthub.backend.models.User;

public class BlockManager {
    private final User user;

    public BlockManager(User user) {
        this.user = user;
    }

    public void blockUser(User other) {
        FriendManager friendManager = new FriendManager(user);
        friendManager.removeFriend(other);

        user.getFriendShip().addUserToBlocked(other);
        other.getFriendShip().getBlockedHAHA(user);
    }

    public void unblockUser(User other) {
        user.getFriendShip().removeUserFromBlocked(other);
        other.getFriendShip().removeUserFromBlockedBy(user);
    }
}
