package connecthub.backend.loaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.backend.database.JSONParser;
import connecthub.backend.models.Post;
import static connecthub.backend.constants.FilePath.POSTS_FILE_PATH;


public class PostLoader implements ContentLoader<Post> {
    JSONParser jsonParser;

    public PostLoader() {
        jsonParser = new JSONParser();
    }

    @Override
    public List<Post> loadContentsFromFile() {
        // TODO: insert logic for loading post contents from posts.json file (SRP);
        List<Post> posts = new ArrayList<>();
        try {
            posts = jsonParser.readJSON(POSTS_FILE_PATH, new TypeReference<List<Post>>() {});
        } catch (IOException e) {
            System.err.println("Error reading posts from file: " + e.getMessage());
        }

        return posts;
    }

    @Override
    public void saveContentsToFile(List<Post> contents) {
        // TODO: use a JsonFileHandler class to update JSON file
        try {
            jsonParser.writeJSON(POSTS_FILE_PATH, contents);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
