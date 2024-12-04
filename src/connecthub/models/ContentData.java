package connecthub.models;

public class ContentData {
    String text;
    String imagePath; // optional image paths

    // Default constructor (required for Jackson deserialization)
    public ContentData() {}

    // without images
    public ContentData(String text) {
        this.text = text;
        this.imagePath = null;
    }

    // with optional images
    public ContentData(String text, String imagePath) {
        this.text = text;
        this.imagePath = imagePath;
    }

    public String getText() {
        return text;
    }

    public String getImagePath() {
        return imagePath;
    }
}
