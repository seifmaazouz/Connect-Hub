package connecthub.backend.services;

import connecthub.backend.database.ContentDatabase;
import connecthub.backend.models.Content;
import java.util.ArrayList;
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

    public long getLastId() {
        List<T> contents = contentDatabase.getListOfContents();
        return Long.parseLong(contents.getLast().getContentId());
    }

    public List<T> getListOfUserContents(String userId) {
        List<T> contents = contentDatabase.getListOfContents();
        List<T> userContents = new ArrayList<>();
        for (T content : contents) {
            if (content.getAuthorId().equals(userId)) {
                userContents.add(content);
            }
        }
        return userContents;
    }
}
