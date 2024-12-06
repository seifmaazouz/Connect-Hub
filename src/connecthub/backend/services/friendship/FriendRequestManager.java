package connecthub.backend.services.friendship;

import connecthub.backend.models.User;

public class FriendRequestManager {
    private final User sender;

    public FriendRequestManager(User sender) {
        this.sender = sender;
    }

    public void sendFriendRequest(User receiver) {
        sender.getFriendship().addSentFriendRequest(receiver);
        receiver.getFriendship().addReceivedFriendRequest(sender);
    }

    public void acceptFriendRequest(User receiver) {
        sender.getFriendship().removeSentFriendRequest(receiver);
        receiver.getFriendship().removeReceivedFriendRequest(sender);
        FriendManager friendManager = new FriendManager(sender);
        friendManager.addFriend(receiver);
    }

    public void rejectFriendRequest(User receiver) {
        sender.getFriendship().removeSentFriendRequest(receiver);
        receiver.getFriendship().removeReceivedFriendRequest(sender);
    }

    public void cancelFriendRequest(User receiver) {
        sender.getFriendship().removeSentFriendRequest(receiver);
        receiver.getFriendship().removeReceivedFriendRequest(sender);
    }
}
