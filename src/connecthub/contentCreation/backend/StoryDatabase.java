package connecthub.contentCreation.backend;

public class StoryDatabase extends ContentDatabase<Story> {

    public StoryDatabase(ContentLoader<Story> contentLoader) {
        super(contentLoader);
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
