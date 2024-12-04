package connecthub.database;

import connecthub.loaders.ContentLoader;
import connecthub.models.Post;

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
