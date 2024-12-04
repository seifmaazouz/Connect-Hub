package main.java.connecthub.models;

public class ContentData {
    String text;
    String imagePath; // optional image paths

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

    public String getImage() {
        return imagePath;
    }
}
