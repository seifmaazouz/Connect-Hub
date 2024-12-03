package connecthub.contentCreation.backend;

public class ContentData {
    String text;
    String[] imagePaths; // optional image paths

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getImages() {
        return imagePaths;
    }

    public void setImages(String[] imagePaths) {
        this.imagePaths = imagePaths;
    }
    
}
