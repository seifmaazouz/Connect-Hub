package connecthub.contentCreation.backend;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ContentDatabase {
    private final Map<String, Content> contents; // store <ContentId, Content> for quick efficient access O(1)
    private final ContentLoader contentLoader;

    public ContentDatabase(ContentLoader contentLoader) {
        this.contentLoader = contentLoader;
        contents = new LinkedHashMap<>(); // linked to keep order
        loadAllContents(); // initial load contents data
    }

    public void saveContent(Content content) {
        contents.put(content.getContentId(), content);
    }

    // uses a separate class to manage loading content from json file to satisfy Single Responsibility Principle
    public void loadAllContents() {
        for(Content content : contentLoader.loadContentsFromFile()) {
            contents.put(content.getContentId(), content);
        }
    }

    public void deleteContent(String contentId) {
        contents.remove(contentId);
    }

    public List<Content> getListOfContents() {
        return new ArrayList<>(contents.values());
    }
}
