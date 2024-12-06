package connecthub.backend.profile;

import connecthub.backend.models.Post;
import connecthub.backend.services.PostService;
import connecthub.backend.utils.factories.ServiceFactory;
import java.util.List;

public class FetchPosts {

    private String userId;

    public FetchPosts(String userId) {
        this.userId = userId;
    }

    public List<Post> fetch() {
        PostService post = ServiceFactory.createPostService();
        List<Post> posts = post.getListOfUserContents(userId);
        return posts;
    }
}
