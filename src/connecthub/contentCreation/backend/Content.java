package connecthub.contentCreation.backend;

import java.time.LocalTime;
import java.util.UUID;

public abstract class Content {
    private final String contentId;
    private final String authorId;
    private final ContentData content; // text and image paths (optional)
    private final LocalTime timestamp;

    public Content(String authorId, ContentData content) {
        contentId = UUID.randomUUID().toString();
        System.out.println(contentId);
        this.authorId = authorId;
        this.content = content;
        timestamp = LocalTime.now();
    }
    
    public String getContentId() {
        return contentId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public ContentData getContent() {
        return content;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }
    
}
