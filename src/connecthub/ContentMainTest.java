package connecthub;

import connecthub.backend.models.*;
import connecthub.backend.services.*;
import connecthub.backend.utils.factories.ContentFactory;
import connecthub.backend.utils.factories.ServiceFactory;

public class ContentMainTest {
    public static void main(String[] args) {
        // Create Post and Story Service
        PostService postService = ServiceFactory.createPostService();
        StoryService storyService = ServiceFactory.createStoryService();

        // Create and save Post content
        Post post1 = ContentFactory.createPost("post", "author1", "This is the first post content","image1.jpg");
        postService.createContent(post1);

        // Create and save Story content
        Story story1 = ContentFactory.createStory("story", "author2", "This is the first story content", null);
        storyService.createContent(story1);

        // Print content list after adding content
        System.out.println("After creating content:");
        printContentList(postService, storyService);

        // Delete expired content and refresh content
        storyService.deleteExpiredStories();
        postService.refreshContents();
        storyService.refreshContents();
        System.out.println("After deleting expired content:");
        printContentList(postService, storyService);
    }

    public static void printContentList(ContentService<Post> postService, ContentService<Story> storyService) {
        for (Post post : postService.getListOfContents()) {
            post.displayContent();
        }
        for (Story story : storyService.getListOfContents()) {
            story.displayContent();
        }
    }
}
