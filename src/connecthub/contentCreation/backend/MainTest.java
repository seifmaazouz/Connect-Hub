package connecthub.contentCreation.backend;

import java.time.LocalDateTime;

public class MainTest {

    public static void main(String[] args) {
        // Create ContentService and ContentDatabase for both Post and Story
        ContentService<Post> postService = createPostService();
        ContentService<Story> storyService = createStoryService();

        // Create and save Post content
        ContentData postContentData = new ContentData("This is the first post content", new String[]{"image1.jpg", "image2.jpg"});
        Content post1 = ContentFactory.createContent("post", "author1", postContentData);
        postService.createContent((Post) post1);

        // Create and save Story content
        ContentData storyContentData = new ContentData("This is the first story content", new String[]{"story1.jpg"});
        Content story1 = ContentFactory.createContent("story", "author2", storyContentData);
        storyService.createContent((Story) story1);

        // Print content list after adding content
        System.out.println("After creating content:");
        printContentList(postService, storyService);
    }

    public static ContentService<Post> createPostService() {
        ContentLoader<Post> postLoader = new PostLoader();
        ContentDatabase<Post> postDatabase = new ContentDatabase<>(postLoader);
        return new ContentService<>(postDatabase);
    }

    public static ContentService<Story> createStoryService() {
        ContentLoader<Story> storyLoader = new StoryLoader();
        ContentDatabase<Story> storyDatabase = new ContentDatabase<>(storyLoader);
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
