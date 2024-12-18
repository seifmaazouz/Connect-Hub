package connecthub.backend.models;

import java.time.LocalDateTime;

public class Comment {
    private String username;
    private String text;
    private LocalDateTime timestamp;

    // Default constructor (required for Jackson deserialization)
    public Comment() {}

    public Comment(String username, String comment) {
        this.username = username;
        this.text = comment;
        timestamp = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}