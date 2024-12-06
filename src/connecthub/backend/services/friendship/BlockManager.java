package connecthub.backend.services.friendship;

import connecthub.backend.models.User;

public class BlockManager {
    private final User user;

    public BlockManager(User user) {
        this.user = user;
    }

    public void blockUser(User other) {
        FriendManager friendManager = new FriendManager(user);
        friendManager.removeFriend(other);

        user.getFriendship().addUserToBlocked(other);
        other.getFriendship().getBlockedHAHA(user);
    }

    public void unblockUser(User other) {
        user.getFriendship().removeUserFromBlocked(other);
        other.getFriendship().removeUserFromBlockedBy(user);
    }
}
