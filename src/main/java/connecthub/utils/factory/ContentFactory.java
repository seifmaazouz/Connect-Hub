package main.java.connecthub.utils.factory;

import main.java.connecthub.models.ContentData;
import main.java.connecthub.models.Content;
import main.java.connecthub.models.Post;
import main.java.connecthub.models.Story;

// Applied Factory Design Pattern
public class ContentFactory {

    public static Content createContent(String type, String authorId, ContentData contentData) {
        switch (type.toLowerCase()) {
            case "post":
                return new Post(authorId, contentData);
            case "story":
                return new Story(authorId, contentData);
            default:
                throw new IllegalArgumentException("Invalid content type: " + type);
        }
    }
}
