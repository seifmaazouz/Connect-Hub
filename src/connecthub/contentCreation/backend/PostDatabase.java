package connecthub.contentCreation.backend;

// apply singleton design pattern
public class PostDatabase extends ContentDatabase<Post> {
    private static PostDatabase instance;

    private PostDatabase(ContentLoader<Post> contentLoader) {
        super(contentLoader);
    }

    public static PostDatabase getInstance(ContentLoader<Post> contentLoader) {
        if (instance == null) {
            instance = new PostDatabase(contentLoader);
        }
        return instance;
    }
}
