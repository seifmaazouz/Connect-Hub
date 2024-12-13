package connecthub.backend.models;

import connecthub.backend.loaders.UserLoader;

import java.util.*;

public class Friendship {

    private HashMap<String, ArrayList<String>> friends;
    private HashMap<String, ArrayList<String>> blocked;
    private HashMap<String, ArrayList<String>> blockedBy;
    private HashMap<String, ArrayList<String>> friendRequests;
    private HashMap<String, ArrayList<String>> sentRequests;


    public Friendship() {
        friends = new HashMap<>();
        blocked = new HashMap<>();
        blockedBy = new HashMap<>();
        friendRequests = new HashMap<>();
        sentRequests = new HashMap<>();

        revise();
    }


//actions:
    public void makeFriends(String userId, String otherUserId) {
        if (!isFriend(userId, otherUserId)) {
            friends.get(userId).add(otherUserId);
            friends.get(otherUserId).add(userId);
        }
        if (hasSentRequest(userId, otherUserId)) {
            cancelRequest(userId, otherUserId);
        }
        if (hasReceivedRequest(userId, otherUserId)) {
            cancelRequest(otherUserId, userId);
        }
    }

    public void unFriend(String userId, String otherUserId) {
        if (isFriend(userId, otherUserId)) {
            friends.get(userId).remove(otherUserId);
            friends.get(otherUserId).remove(userId);
        }
    }

    public void block(String userId, String otherUserId) {
        if (isFriend(userId, otherUserId)) {
            unFriend(userId, otherUserId);
        } else if (hasSentRequest(userId, otherUserId) || hasReceivedRequest(userId, otherUserId)) {
            cancelRequest(userId, otherUserId);
        }
        if (!hasBlocked(userId, otherUserId)) {
            blocked.get(userId).add(otherUserId);
            blockedBy.get(otherUserId).add(userId);
        }
    }

    public void unBlock(String userId, String otherUserId) {
        blockedBy.get(userId).remove(otherUserId);
        blockedBy.get(otherUserId).remove(userId);
        blocked.get(otherUserId).remove(userId);
        blocked.get(userId).remove(otherUserId);
    }

    public void sendRequest(String userId, String otherUserId) {
        if (hasReceivedRequest(userId, otherUserId)) {
            acceptRequest(userId, otherUserId);
        } else {
            sentRequests.get(userId).add(otherUserId);
            friendRequests.get(otherUserId).add(userId);
        }
    }

    public void cancelRequest(String userId, String otherUserId) {
        if (hasSentRequest(userId, otherUserId)) {
            sentRequests.get(userId).remove(otherUserId);
            friendRequests.get(otherUserId).remove(userId);
        }
    }

    public void acceptRequest(String userId, String otherUserId) {
        if (hasReceivedRequest(userId, otherUserId)) {
            cancelRequest(otherUserId, userId);
            makeFriends(userId, otherUserId);
        }
    }

//checkers:
    public boolean isBlockedBy(String userId, String otherUserId) {
        return blockedBy.containsKey(userId) && blockedBy.get(userId).contains(otherUserId);
    }

    public boolean hasBlocked(String userId, String otherUserId) {
        return blocked.containsKey(userId) && blocked.get(userId).contains(otherUserId);
    }

        public boolean isFriend(String userId, String otherUserId) {
            return friends.get(userId).contains(otherUserId);
        }

    public boolean hasSentRequest(String userId, String otherUserId) {
        return sentRequests.containsKey(userId) && sentRequests.get(userId).contains(otherUserId);
    }

    public boolean hasReceivedRequest(String userId, String otherUserId) {
        return friendRequests.containsKey(userId) && friendRequests.get(userId).contains(otherUserId);
    }

//getters:
    public ArrayList<String> getUserFriends(String userId) {
        return this.friends.get(userId);
    }

    public ArrayList<String> getUserBlocked(String userId) {
        return this.blocked.get(userId);
    }

    public ArrayList<String> getUserBlockedBy(String userId) {
        return this.blockedBy.get(userId);
    }

    public ArrayList<String> getUserFriendRequests(String userId) {
        return this.friendRequests.get(userId);
    }

    public ArrayList<String> getUserSentRequests(String userId) {
        return this.sentRequests.get(userId);
    }

    public HashMap<String, ArrayList<String>> getFriends() {
        return friends;
    }

    public HashMap<String, ArrayList<String>> getBlocked() {
        return blocked;
    }

    public HashMap<String, ArrayList<String>> getBlockedBy() {
        return blockedBy;
    }

    public HashMap<String, ArrayList<String>> getFriendRequests() {
        return friendRequests;
    }

    public HashMap<String, ArrayList<String>> getSentRequests() {
        return sentRequests;
    }

//setters:
    public void setFriends(String userId, ArrayList<String> friends) {
        this.friends.put(userId, friends);
    }

    public void setBlocked(String userId, ArrayList<String> blocked) {
        this.blocked.put(userId, blocked);
    }

    public void setBlockedBy(String userId, ArrayList<String> blockedBy) {
        this.blockedBy.put(userId, blockedBy);
    }

    public void setReceivedRequests(String userId, ArrayList<String> friendRequests) {
        this.friendRequests.put(userId, friendRequests);
    }

    public void setSentRequests(String userId, ArrayList<String> sentRequests) {
        this.sentRequests.put(userId, sentRequests);
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "friends=" + friends +
                ", blocked=" + blocked +
                ", blockedBy=" + blockedBy +
                ", friendRequests=" + friendRequests +
                ", sentRequests=" + sentRequests +
                '}';
    }

    private void reviseUserFriendship(String userId) {
        if (!friends.containsKey(userId)) {
            friends.put(userId, new ArrayList<>());
        }
        if (!blocked.containsKey(userId)) {
            blocked.put(userId, new ArrayList<>());
        }
        if (!blockedBy.containsKey(userId)) {
            blockedBy.put(userId, new ArrayList<>());
        }
        if (!friendRequests.containsKey(userId)) {
            friendRequests.put(userId, new ArrayList<>());
        }
        if (!sentRequests.containsKey(userId)) {
            sentRequests.put(userId, new ArrayList<>());
        }
    }

    private void revise() {
//        Map<String, User> allUsers = (new UserLoader().loadUsers());
        Set<String> userIds = (new UserLoader().loadUsers().keySet());
        for (String userId : userIds) { //allUsers.keySet()
            reviseUserFriendship(userId);
        }
    }
}
