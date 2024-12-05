package connecthub.backend.loaders;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.backend.database.JSONParser;
import connecthub.backend.models.User;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static connecthub.backend.constants.FilePath.USERS_FILE_PATH;

public class UserLoader {
    JSONParser jsonParser;

    public UserLoader() {
        this.jsonParser = new JSONParser();
    }

    public Map<String, User> loadUsers() {
        HashMap<String, User> users = new HashMap<>();

        try {
            users = jsonParser.readJSON(USERS_FILE_PATH, new TypeReference<HashMap<String, User>>() {});
        } catch (Exception e) {
            System.err.println("Error loading users from file: " + e.getMessage());
        }

        return users;
    }

    public void saveUsers(Map<String, User> users) {
        try {
            jsonParser.writeJSON(USERS_FILE_PATH, users);
        } catch (Exception e) {
            System.err.println("Error saving users to file: " + e.getMessage());
        }
    }
}
