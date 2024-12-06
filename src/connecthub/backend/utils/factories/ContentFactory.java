package connecthub.backend.utils.factories;

import connecthub.backend.models.ContentData;
import connecthub.backend.models.Post;
import connecthub.backend.models.Story;

// Applied Factory Design Pattern
public class ContentFactory {
        
    public static Post createPost(String authorId, String text, String imagePath) {
        ContentData contentData = new ContentData(text, imagePath);
        return new Post(authorId, contentData);
    }
    public static Story createStory(String authorId, String text, String imagePath) {
        ContentData contentData = new ContentData(text, imagePath);
        return new Story(authorId, contentData);
    }
}

