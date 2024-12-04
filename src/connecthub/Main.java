package connecthub;

import connecthub.database.*;
import connecthub.loaders.*;
import connecthub.models.*;
import connecthub.services.*;
import connecthub.utils.factory.ContentFactory;

import java.time.LocalDateTime;

public class Main  {
    public static void main(String[] args) {
        // Create Post and Story Service
        ContentService<Post> postService = createPostService();
        ContentService<Story> storyService = createStoryService();

        // Create and save Post content
        ContentData postContentData = new ContentData("This is the first post content","image1.jpg");
        Content post1 = ContentFactory.createContent("post", "author1", postContentData);
        postService.createContent((Post) post1);

        // Create and save Story content
        ContentData storyContentData = new ContentData("This is the first story content");
        Content story1 = ContentFactory.createContent("story", "author2", storyContentData);
        storyService.createContent((Story) story1);

        // Print content list after adding content
        System.out.println("After creating content:");
        printContentList(postService, storyService);

        // Delete expired content and refresh content
        postService.deleteExpiredContent();
        storyService.deleteExpiredContent();
        postService.refreshContents();
        storyService.refreshContents();
        System.out.println("After deleting expired content:");
        printContentList(postService, storyService);
    }

    public static ContentService<Post> createPostService() {
        ContentLoader<Post> postLoader = new PostLoader();
        ContentDatabase<Post> postDatabase = PostDatabase.getInstance(postLoader);
        return new ContentService<>(postDatabase);
    }

    public static ContentService<Story> createStoryService() {
        ContentLoader<Story> storyLoader = new StoryLoader();
        ContentDatabase<Story> storyDatabase = StoryDatabase.getInstance(storyLoader);
        return new ContentService<>(storyDatabase);
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
