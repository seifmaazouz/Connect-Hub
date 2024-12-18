package connecthub.backend.models;

import java.time.LocalDateTime;

public class Notification {

    public enum Type {
        FRIEND_REQUEST, GROUP_ACTIVITY, NEW_POST, MESSAGE, COMMENT
    }

    private String message;
    private LocalDateTime timestamp;
    private Type type;
    private String senderId;

    public Notification() {
    }
    
    public Notification(String message, Type type, String senderId) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.senderId = senderId;
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
