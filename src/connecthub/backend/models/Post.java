package connecthub.backend.models;

public class Post extends Content {

    // Default constructor (required for Jackson deserialization)
    public Post() {
        super();
    }

    public Post(String contentId, String authorId, ContentData contentData) {
        super(contentId, authorId, contentData);
    }
}
