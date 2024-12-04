package connecthub.contentCreation.backend;

import java.util.ArrayList;
import java.util.List;

public class PostLoader implements ContentLoader<Post> {
    // for testing purposes
    List<Post> posts = new ArrayList<>();

    @Override
    public List<Post> loadContentsFromFile() {
        // TODO: insert logic for loading post contents from posts.json file (SRP)

        // for testing purposes
        posts.add(new Post("1", new ContentData("test data 1")));

        return posts;
    }

    @Override
    public void saveContentsToFile(List<Post> contents) {
        // TODO: use a JsonFileHandler class to update JSON file

        // for testing purposes
        posts.addAll(contents);
    }
}
