package connecthub.backend.models;

import java.time.LocalDateTime;

public class Notification {

    public enum Type {
        FRIEND_REQUEST, GROUP_ACTIVITY, NEW_POST
    }

    private String receiverId;
    private String message;
    private LocalDateTime timestamp;
    private Type type;
    private String senderId;

    public Notification(String receiverId, String message, Type type, String senderId) {
        this.receiverId = receiverId;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Type getType() {
        return type;
    }

    public String getSenderId() {
        return senderId;
    }
}
