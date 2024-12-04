package connecthub.contentCreation.backend;

public class ContentData {
    String text;
    String[] imagePaths; // optional image paths

    // without images
    public ContentData(String text) {
        this.text = text;
        this.imagePaths = null;
    }

    // with optional images
    public ContentData(String text, String[] imagePaths) {
        this.text = text;
        this.imagePaths = imagePaths;
    }

    public String getText() {
        return text;
    }

    public String[] getImages() {
        return imagePaths;
    }
}
