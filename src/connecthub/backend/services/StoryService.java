package connecthub.backend.services;
import connecthub.backend.database.ExpirableContentDatabase;
import connecthub.backend.models.Content;
import connecthub.backend.models.Story;

public class StoryService extends ContentService<Story> {            
    public StoryService(ExpirableContentDatabase<Story> contentDatabase) {
        super(contentDatabase);
    }
    
    // removes expired stories
    public void deleteExpiredStories() {
        ((ExpirableContentDatabase<Story>)contentDatabase).deleteExpiredContent();
    }
}
