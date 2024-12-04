package connecthub.contentCreation.backend;

// apply singleton design pattern
public class StoryDatabase extends ContentDatabase<Story> {
    private static StoryDatabase instance;

    private StoryDatabase(ContentLoader<Story> contentLoader) {
        super(contentLoader);
    }

    public static StoryDatabase getInstance(ContentLoader<Story> contentLoader) {
        if (instance == null) {
            instance = new StoryDatabase(contentLoader);
        }
        return instance;
    }

    public void deleteExpiredStories() {
        boolean isUpdateNeeded = false;

        for(Story story : getListOfContents()) {
            if(story.isExpired()) {
                contents.remove(story.getContentId());
                isUpdateNeeded = true;
            }
        }

        if(isUpdateNeeded) // only save if at least one content is removed due to expiry
            saveChangesToFile();
    }



}
