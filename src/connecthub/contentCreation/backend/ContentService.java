package connecthub.contentCreation.backend;

import java.util.List;

public class ContentService {
    private final ContentDatabase contentDatabase;

    public ContentService(ContentDatabase contentDatabase) { // could make general contentDatabase interface to satisfy Dependency Inversion
        this.contentDatabase = contentDatabase;
    }
    
    public void createContent(Content content) {
        contentDatabase.saveContent(content);
    }

    public void refreshContents() {
        contentDatabase.loadAllContents();
    }

    public void deleteExpiredStories() {
        for(Content content : contentDatabase.getListOfContents()) {
            if(content instanceof Expirable && ((Expirable)content).isExpired()) // Open/Closed Principle
                contentDatabase.deleteContent(content.getContentId());
        }
    }
    
    public List<Content> getListOfContents() {
        return contentDatabase.getListOfContents();
    }
}
