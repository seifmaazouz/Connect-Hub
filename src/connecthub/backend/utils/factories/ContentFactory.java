package connecthub.backend.utils.factories;

import connecthub.backend.models.ContentData;
import connecthub.backend.models.Post;
import connecthub.backend.models.Story;
import connecthub.backend.services.PostService;
import connecthub.backend.services.StoryService;

// Applied Factory Design Pattern
public class ContentFactory {
        
    public static Post createPost(String authorId, String text, String imagePath) {
        ContentData contentData = new ContentData(text, imagePath);
        PostService postService = ServiceFactory.createPostService();
        String uniqueId = String.valueOf(postService.getLastId() + 1);
        return new Post(uniqueId, authorId, contentData);
    }
    public static Story createStory(String authorId, String text, String imagePath) {
        ContentData contentData = new ContentData(text, imagePath);
        StoryService storyService = ServiceFactory.createStoryService();
        String uniqueId = String.valueOf(storyService.getLastId() + 1);
        return new Story(uniqueId, authorId, contentData);
    }
}

