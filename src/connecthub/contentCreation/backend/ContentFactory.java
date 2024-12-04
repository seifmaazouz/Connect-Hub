package connecthub.contentCreation.backend;

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
