package connecthub.contentCreation.backend;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ContentDatabase {
    private final Map<String, Content> contents; // store <ContentId, Content> for quick efficient access O(1)

    public ContentDatabase() {
        contents = new LinkedHashMap<>(); // linked to keep order
    }

    public void saveContent(Content content) {
        contents.put(content.getContentId(), content);
    }

    public void deleteContent(String contentId) {
        contents.remove(contentId);
    }

    public List<Content> getListOfContents() {
        return new ArrayList<>(contents.values());
    }
}
