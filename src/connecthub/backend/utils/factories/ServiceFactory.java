package connecthub.backend.utils.factories;

import connecthub.backend.database.PostDatabase;
import connecthub.backend.database.StoryDatabase;
import connecthub.backend.loaders.PostLoader;
import connecthub.backend.loaders.StoryLoader;
import connecthub.backend.services.PostService;
import connecthub.backend.services.StoryService;

// Factory Design Pattern
public class ServiceFactory {

    public static PostService createPostService() {
            PostLoader postLoader = new PostLoader();
            PostDatabase postDatabase = PostDatabase.getInstance(postLoader);
            return new PostService(postDatabase);
    }

    public static StoryService createStoryService() {
        StoryLoader storyLoader = new StoryLoader();
        StoryDatabase storyDatabase = StoryDatabase.getInstance(storyLoader);
        return new StoryService(storyDatabase);
    }
}
