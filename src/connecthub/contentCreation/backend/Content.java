package connecthub.contentCreation.backend;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Content {
    private final String contentId;
    private final String authorId;
    private final ContentData contentData; // text and image paths (optional)
    private final LocalDateTime timestamp;

    public Content(String authorId, ContentData content) {
        contentId = UUID.randomUUID().toString(); // TODO: change this to handle unique numbers even after closing and re-opening program
        System.out.println(contentId);
        this.authorId = authorId;
        this.contentData = content;
        timestamp = LocalDateTime.now();
    }
    
    public String getContentId() {
        return contentId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public ContentData getContentData() {
        return contentData;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // for testing purposes
    public void displayContent() {
        System.out.println("Type: " + this.getClass().getSimpleName());
        ContentData contentData = getContentData();
        System.out.println("Content: " + contentData.getText());
        String[] imagePaths = contentData.imagePaths;
        if(imagePaths != null)
            System.out.println("Images: " + String.join(", ", imagePaths));
        else
            System.out.println("Images: null"); // Handles null or empty imagePaths
        System.out.println();
    }
}
