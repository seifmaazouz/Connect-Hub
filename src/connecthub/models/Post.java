package connecthub.models;

public class Post extends Content {

    // Default constructor (required for Jackson deserialization)
    public Post() {
        super();
    }

    public Post(String authorId, ContentData contentData) {
        super(authorId, contentData);
    }
}
