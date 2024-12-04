package main.java.connecthub.loaders;

import main.java.connecthub.models.Content;

import java.util.List;

public interface ContentLoader<T extends Content> {

    List<T> loadContentsFromFile();  // Load content from JSON file
    void saveContentsToFile(List<T> contents); // Save updated content to JSON file
}
