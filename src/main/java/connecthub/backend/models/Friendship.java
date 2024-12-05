package main.java.connecthub.backend.models;

import java.util.ArrayList;

public class Friendship {
    private final ArrayList<User> friends;
    private final ArrayList<User> blocked;
    private final ArrayList<User> blockedBy;
    private final ArrayList<User> friendRequests;
    private final ArrayList<User> sentRequests;
    private final User user;

    public Friendship(User user) {
        this.user = user;
        friends = new ArrayList<>();
        blocked = new ArrayList<>();
        blockedBy = new ArrayList<>();
        friendRequests = new ArrayList<>();
        sentRequests = new ArrayList<>();
    }

    public User getUser() { return this.user;}

    public void addSentFriendRequest(User receiver) {
        sentRequests.add(receiver);
    }

    public void addReceivedFriendRequest(User sender) {
        friendRequests.add(sender);
    }

    public void addUserToFriends(User user) {
        friends.add(user);
    }

    public void addUserToBlocked(User user) {
        blocked.add(user);
    }

    public void removeSentFriendRequest(User receiver) {
        sentRequests.remove(receiver);
    }

    public void removeReceivedFriendRequest(User sender) {
        friendRequests.remove(sender);
    }

    public void removeUserFromFriends(User user) {
        friends.remove(user);
    }

    public void removeUserFromBlocked(User user) {
        blocked.remove(user);
    }

    public void getBlockedHAHA(User user) {
        blockedBy.add(user);
    }

    public void removeUserFromBlockedBy(User user) {
        blockedBy.remove(user);
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public ArrayList<User> getBlockedUsers() {
        return blocked;
    }

    public ArrayList<User> getBlockedByUsers() {
        return blockedBy;
    }

    public ArrayList<User> getSentRequests() {
        return sentRequests;
    }

    public ArrayList<User> getFriendRequests() {
        return friendRequests;
    }



    public boolean isFriend(User other) {
        //todo
        return false;
    }
}
