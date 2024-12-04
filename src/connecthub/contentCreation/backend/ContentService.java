package connecthub.contentCreation.backend;

import java.util.List;

public class ContentService<T extends Content> {
    private final ContentDatabase<T> contentDatabase;

    public ContentService(ContentDatabase<T> contentDatabase) { // could make general contentDatabase interface to satisfy Dependency Inversion
        this.contentDatabase = contentDatabase;
    }

    public void createContent(T content) {
        contentDatabase.saveContent(content);
    }

    // now this method is not needed as I manually update the database after each change
    public void refreshContents() {
        contentDatabase.loadAllContents();
    }

    public void deleteExpiredContent()  {
        contentDatabase.deleteExpiredContent();
    }

    public List<T> getListOfContents() {
        return contentDatabase.getListOfContents();
    }
}
