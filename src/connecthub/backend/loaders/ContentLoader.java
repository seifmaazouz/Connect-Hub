package connecthub.backend.loaders;

import connecthub.backend.models.Content;

import java.util.List;

public interface ContentLoader<T extends Content> {

    List<T> loadContentsFromFile();  // Load content from JSON file
    void saveContentsToFile(List<T> contents); // Save updated content to JSON file
}
