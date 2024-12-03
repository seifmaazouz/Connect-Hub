package connecthub.database;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.models.Friendship;
import connecthub.models.Post;
import connecthub.models.Story;
import connecthub.models.User;

import java.util.List;

import static connecthub.constants.FilePath.*;

public class DatabaseManager {

    private final JSONParser jsonParser;
    private final String postsFilePath = POSTS_FILE_PATH;
    private final String usersFilePath = USERS_FILE_PATH;
    private final String friendsFilePath = FRIENDS_FILE_PATH;
    private final String storiesFilePath = STORIES_FILE_PATH;

    public DatabaseManager() {
        this.jsonParser = new JSONParser();
    }

    /**
     * Save all posts to the posts.json file.
     *
     * @param posts The list of posts to save.
     */
    public void savePosts(List<Post> posts) {
        try {
            jsonParser.writeJSON(postsFilePath, posts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetch all posts from the posts.json file.
     *
     * @return A list of posts.
     */
    public List<Post> getAllPosts() {
        try {
            return jsonParser.readJSON(postsFilePath, new TypeReference<List<Post>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fetch all users from the users.json file.
     *
     * @return A list of users.
     */
    public List<User> getAllUsers() {
        try {
            return jsonParser.readJSON(usersFilePath, new TypeReference<List<User>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Save all users to the users.json file.
     *
     * @param users The list of users to save.
     */
    public void saveUsers(List<User> users) {
        try {
            jsonParser.writeJSON(usersFilePath, users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // TODO: Implement methods for fetching and saving friends and stories
}
