package connecthub.backend.services;

import connecthub.backend.database.ContentDatabase;
import connecthub.backend.database.StoryDatabase;
import connecthub.backend.models.Content;

import java.util.List;

public class ContentService<T extends Content> {
    private final ContentDatabase<T> contentDatabase;

    public ContentService(ContentDatabase<T> contentDatabase) { // could make general contentDatabase interface to satisfy Dependency Inversion
        this.contentDatabase = contentDatabase;
    }

    public void createContent(T content) {
        contentDatabase.saveContent(content);
    }

    // use this method for when pressing refresh button
    public void refreshContents() {
        contentDatabase.loadAllContents();
    }

    // removes expired content
    public void deleteExpiredContent() {
        if (contentDatabase instanceof StoryDatabase) {
            ((StoryDatabase) contentDatabase).deleteExpiredStories();
        }
    }

    public List<T> getListOfContents() {
        return contentDatabase.getListOfContents();
    }
}
