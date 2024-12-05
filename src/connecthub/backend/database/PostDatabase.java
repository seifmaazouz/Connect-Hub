package connecthub.backend.database;

import connecthub.backend.loaders.PostLoader;
import connecthub.backend.models.Post;

// apply singleton design pattern
public class PostDatabase extends ContentDatabase<Post> {
    private static PostDatabase instance;

    private PostDatabase(PostLoader postLoader) {
        super(postLoader);
    }

    public static PostDatabase getInstance(PostLoader postLoader) {
        if (instance == null) {
            instance = new PostDatabase(postLoader);
        }
        return instance;
    }
}
