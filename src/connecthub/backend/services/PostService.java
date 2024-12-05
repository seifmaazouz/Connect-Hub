package connecthub.backend.services;

import connecthub.backend.database.ContentDatabase;
import connecthub.backend.models.Post;

// apply singelton deisgn principle
public class PostService extends ContentService<Post> {
    
    public PostService(ContentDatabase<Post> contentDatabase) {
        super(contentDatabase);
    }
}
