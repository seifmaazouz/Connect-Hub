package connecthub.backend.database;

import connecthub.backend.loaders.UserLoader;
import connecthub.backend.models.User;

import java.util.HashMap;

public class UserDatabase {
    protected final HashMap<String, User> users;
    protected final UserLoader userLoader;
    private static UserDatabase instance;

    private UserDatabase() {
        this.users = new HashMap<>();
        this.userLoader = new UserLoader();
        loadAllContents();
    }

    public static UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    public synchronized void saveUser(User user) {
        users.put(user.getUserId(), user);
        saveChangesToFile();
    }

    public synchronized void deleteUser(String userId) {
        users.remove(userId);
        saveChangesToFile();
    }

    public synchronized HashMap<String, User> getUsers() {
        return users;
    }

    protected synchronized void saveChangesToFile() {
        userLoader.saveUsers(getUsers());
    }

    public synchronized void loadAllContents() {
        for (User user: userLoader.loadUsers().values()) {
            users.put(user.getUserId(), user);
        }
    }
}
