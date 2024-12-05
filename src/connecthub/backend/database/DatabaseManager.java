package connecthub.backend.database;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.backend.models.User;
import java.util.HashMap;


import static connecthub.backend.constants.FilePath.*;

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
     * Fetch all users from the users.json file.
     *
     * @return A list of users.
     */
    public HashMap<String, User> getUserMap() {
        try {
            return jsonParser.readJSON(usersFilePath, new TypeReference<HashMap<String, User>>() {
            });
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
    public void saveUserMap(HashMap<String, User> users) {
        try {
            jsonParser.writeJSON(usersFilePath, users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // TODO: Implement methods for fetching and saving friends and stories
}
