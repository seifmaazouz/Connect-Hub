package connecthub.backend.models;

import java.time.LocalDateTime;

public class Post extends Content {

    // Default constructor (required for Jackson deserialization)
    public Post() {
        super();
    }

    public Post(String authorId, ContentData contentData) {
        super(authorId, contentData);
    }

    public Post(String contentId, String authorId, ContentData contentData, LocalDateTime timestamp) {
        super(contentId, authorId, contentData, timestamp);
    }
}
