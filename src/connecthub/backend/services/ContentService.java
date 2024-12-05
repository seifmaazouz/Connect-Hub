package connecthub.backend.services;

import connecthub.backend.database.ContentDatabase;
import connecthub.backend.models.Content;
import java.util.List;

// Dependency Inversion Principle
public abstract class ContentService<T extends Content> {
    protected final ContentDatabase<T> contentDatabase;

    public ContentService(ContentDatabase<T> contentDatabase) { 
        this.contentDatabase = contentDatabase;
    }

    public void createContent(T content) {
        contentDatabase.saveContent(content);
    }

    // use this method for when pressing refresh button
    public void refreshContents() {
        contentDatabase.loadAllContents();
    }

    public List<T> getListOfContents() {
        return contentDatabase.getListOfContents();
    }
}
