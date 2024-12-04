package main.java.connecthub.loaders;

import main.java.connecthub.models.ContentData;
import main.java.connecthub.models.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryLoader implements ContentLoader<Story> {
    // for testing purposes
    List<Story> stories = new ArrayList<>();

    @Override
    public List<Story> loadContentsFromFile() {
        // TODO: insert logic for loading story contents from stories.json file (SRP)

        // for testing purposes
        stories.add(new Story("1", new ContentData("test data 1", "img.png")));

        return stories;
    }

    @Override
    public void saveContentsToFile(List<Story> contents) {
        // TODO: use a JsonFileHandler class to update JSON file

        // for testing purposes
        stories.addAll(contents);
    }
}
