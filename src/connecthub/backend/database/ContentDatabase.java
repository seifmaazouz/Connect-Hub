package connecthub.backend.database;

import connecthub.backend.models.Content;
import connecthub.backend.loaders.ContentLoader;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// TODO: apply singleton Design Pattern
public abstract class ContentDatabase<T extends Content> {
    protected final Map<String, T> contents; // store <ContentId, Content> for quick efficient access O(1)
    protected final ContentLoader<T> contentLoader;

    public ContentDatabase(ContentLoader<T> contentLoader) {
        this.contentLoader = contentLoader;
        contents = new LinkedHashMap<>(); // linked to keep order
        loadAllContents(); // initial load contents data
    }

    public void saveContent(T content) {
        contents.put(content.getContentId(), content);
        saveChangesToFile(); // manually update file after change
    }

    public void deleteContent(String contentId) {
        contents.remove(contentId);
        saveChangesToFile(); // manually update file after change
    }

    public T getContent(String contentId) {
        return contents.get(contentId);
    }
    
    public List<T> getListOfContents() {
        return new LinkedList<>(contents.values());
    }

    // save content to file if changes made
    protected void saveChangesToFile() {
            contentLoader.saveContentsToFile(getListOfContents()); // Save content to file
    }

    // uses a separate class to manage loading content from json file to satisfy Single Responsibility Principle
    public void loadAllContents() {
         contents.clear(); // clear existing contents
        for(T content : contentLoader.loadContentsFromFile()) {
            contents.put(content.getContentId(), content);
        }
    }
}
