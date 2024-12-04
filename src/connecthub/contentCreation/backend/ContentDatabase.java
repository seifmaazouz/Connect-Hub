package connecthub.contentCreation.backend;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// TODO: apply singleton Design Pattern
public class ContentDatabase<T extends Content> {
    private final Map<String, T> contents; // store <ContentId, Content> for quick efficient access O(1)
    private final ContentLoader<T> contentLoader;

    public ContentDatabase(ContentLoader<T> contentLoader) {
        this.contentLoader = contentLoader;
        contents = new LinkedHashMap<>(); // linked to keep order
        loadAllContents(); // initial load contents data
    }

    public void saveContent(T content) {
        contents.put(content.getContentId(), content);
        saveChanges(); // manually update file after change
    }

    public void deleteContent(String contentId) {
        contents.remove(contentId);
        saveChanges(); // manually update file after change
    }

    public void deleteExpiredContent() {
        boolean isUpdateNeeded = false;

        for(T content : getListOfContents()) {
            if(content instanceof Expirable && ((Expirable)content).isExpired()) {
                contents.remove(content.getContentId());
                isUpdateNeeded = true;
            }
        }
        if(isUpdateNeeded) // only save if at least one content is removed due to expiry
            saveChanges();
    }

    // save content to file if changes made
    private void saveChanges() {
            contentLoader.saveContentsToFile(getListOfContents()); // Save content to file
    }

    // uses a separate class to manage loading content from json file to satisfy Single Responsibility Principle
    public void loadAllContents() {
        for(T content : contentLoader.loadContentsFromFile()) {
            contents.put(content.getContentId(), content);
        }
    }

    public List<T> getListOfContents() {
        return new ArrayList<>(contents.values());
    }
}
