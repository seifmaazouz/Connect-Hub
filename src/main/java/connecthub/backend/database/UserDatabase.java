package connecthub.backend.database;

import connecthub.backend.loaders.UserLoader;
import connecthub.backend.models.User;

import java.util.LinkedHashMap;

public class UserDatabase {

    protected final LinkedHashMap<String, User> users;
    protected final UserLoader userLoader;

    public UserDatabase(UserLoader userLoader) {
        this.users = new LinkedHashMap<>();
        this.userLoader = userLoader;
        loadAllContents();
    }

    public void saveUser(User user) {
        users.put(user.getUserId(), user);
        saveChangesToFile();
    }

    public void deleteUser(String userId) {
        users.remove(userId);
        saveChangesToFile();
    }

    public LinkedHashMap<String, User> getUsers() {
        return new LinkedHashMap<>(users);
    }

    protected void saveChangesToFile() {
        userLoader.saveUsers(getUsers());
    }

    public void loadAllContents() {
        for (User user: userLoader.loadUsers().values()) {
            users.put(user.getUserId(), user);
        }
    }
}
