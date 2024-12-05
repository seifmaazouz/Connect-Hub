package connecthub.services.friendship;

import connecthub.models.User;

public class FriendRequestManager {
    private final User sender;

    public FriendRequestManager(User sender) {
        this.sender = sender;
    }

    public void sendFriendRequest(User receiver) {
        sender.getFriendShip().addSentFriendRequest(receiver);
        receiver.getFriendShip().addReceivedFriendRequest(sender);
    }

    public void acceptFriendRequest(User receiver) {
        sender.getFriendShip().removeSentFriendRequest(receiver);
        receiver.getFriendShip().removeReceivedFriendRequest(sender);
        FriendManager friendManager = new FriendManager(sender);
        friendManager.addFriend(receiver);
    }

    public void rejectFriendRequest(User receiver) {
        sender.getFriendShip().removeSentFriendRequest(receiver);
        receiver.getFriendShip().removeReceivedFriendRequest(sender);
    }

    public void cancelFriendRequest(User receiver) {
        sender.getFriendShip().removeSentFriendRequest(receiver);
        receiver.getFriendShip().removeReceivedFriendRequest(sender);
    }
}
