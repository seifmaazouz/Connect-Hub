package connecthub.backend.models;

import java.time.LocalDateTime;

public class Notification {
    public enum Type {
        FRIEND_REQUEST, GROUP_ACTIVITY, NEW_POST
    }

    private String message;
    private LocalDateTime timestamp;
    private Type type;

    public Notification(String message, Type type) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.type = type;
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
}
