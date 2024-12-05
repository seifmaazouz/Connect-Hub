package connecthub.backend.models;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Content {
    private  String contentId;
    private  String authorId;
    private  ContentData contentData; // text and image paths (optional)
    private  LocalDateTime timestamp;

    // Default constructor (required for Jackson deserialization)
    public Content() {}

    public Content(String authorId, ContentData content) {
        contentId = UUID.randomUUID().toString(); // TODO: change this to handle unique numbers even after closing and re-opening program
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
        String imagePath = contentData.imagePath;
        if(imagePath != null)
            System.out.println("Image: " + String.join(", ", imagePath));
        else
            System.out.println("Image: null"); // Handles null or empty imagePaths
        System.out.println();
    }
}
