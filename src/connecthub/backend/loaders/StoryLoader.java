package connecthub.backend.loaders;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.backend.models.Story;
import connecthub.backend.database.JSONParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static connecthub.backend.constants.FilePath.STORIES_FILE_PATH;

public class StoryLoader implements ContentLoader<Story> {
    JSONParser jsonParser;

    public StoryLoader() {
        jsonParser = new JSONParser();
    }

    @Override
    public List<Story> loadContentsFromFile() {
        // TODO: insert logic for loading story contents from stories.json file (SRP)
        List<Story> stories = new ArrayList<>();
        try {
            stories = jsonParser.readJSON(STORIES_FILE_PATH, new TypeReference<List<Story>>() {});
        } catch (IOException e) {
            System.err.println("Error reading stories from file: " + e.getMessage());
        }

        return stories;
    }

    @Override
    public void saveContentsToFile(List<Story> contents) {
        // TODO: use a JsonFileHandler class to update JSON file

        try {
            jsonParser.writeJSON(STORIES_FILE_PATH, contents);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
