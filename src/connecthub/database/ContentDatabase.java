package connecthub.database;

import connecthub.constants.FilePath;
import connecthub.models.Content;
import connecthub.loaders.ContentLoader;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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

    public List<T> getListOfContents() {
        return new ArrayList<>(contents.values());
    }

    // save content to file if changes made
    protected void saveChangesToFile() {
            contentLoader.saveContentsToFile(getListOfContents()); // Save content to file
    }

    // uses a separate class to manage loading content from json file to satisfy Single Responsibility Principle
    public void loadAllContents() {
        // contents.clear();
        for(T content : contentLoader.loadContentsFromFile()) {
            contents.put(content.getContentId(), content);
        }
    }
}
