package main.java.connecthub.models;

public class Friendship {
    private User user;
    private User friend;
    private String status;

    public Friendship(User user, User friend, String status) {
        this.user = user;
        this.friend = friend;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public User getFriend() {
        return friend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void acceptFriendRequest() {
        this.status = "accepted";
    }

    public void rejectFriendRequest() {
        this.status = "rejected";
    }

    public void blockFriend() {
        this.status = "blocked";
    }

    public void unblockFriend() {
        this.status = "unblocked";
    }

    public boolean isBlocked() {
        return this.status.equals("blocked");
    }


}
