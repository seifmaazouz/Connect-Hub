package connecthub.backend.database;

import connecthub.backend.loaders.StoryLoader;
import connecthub.backend.models.Story;

// apply singleton design pattern
public class StoryDatabase extends ExpirableContentDatabase<Story> {
    private static StoryDatabase instance;

    private StoryDatabase(StoryLoader storyLoader) {
        super(storyLoader);
    }

    public static StoryDatabase getInstance(StoryLoader storyLoader) {
        if (instance == null) {
            instance = new StoryDatabase(storyLoader);
        }
        return instance;
    }

    @Override
    public void deleteExpiredContent() {
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
