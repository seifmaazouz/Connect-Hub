package connecthub.backend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import connecthub.backend.database.UserDatabase;
import connecthub.backend.models.User;
import connecthub.backend.utils.hashing.HashingBehaviour;
import connecthub.backend.utils.hashing.PBKDF2Hashing;
import connecthub.backend.utils.user.UserMetadataValidator;
import connecthub.backend.utils.errors.Alert;
import connecthub.backend.utils.password.PBKDF2Validation;
import connecthub.backend.utils.password.ValidationBehaviour;

import java.util.UUID;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserService {
    private static UserService instance = new UserService();


    private final HashingBehaviour hashingBehaviour;

    private final UserDatabase databaseManager;
    private final ValidationBehaviour validationBehaviour;
    private HashMap<String, User> userMap;

    private UserService() {
        this.databaseManager = new UserDatabase();
        this.userMap = databaseManager.getUsers();
        this.hashingBehaviour = new PBKDF2Hashing();
        this.validationBehaviour = new PBKDF2Validation();
    }

    public static UserService getInstance() {
        return instance;
    }

    public static synchronized String generateUserId() {
        String newId = UUID.randomUUID().toString();
        return newId;
    }


    public User getUserById(String userId) {
        return (userMap != null) ? userMap.get(userId) : null;
    }

    public User getUserByUsername(String username) {
        if (userMap != null) {
            for (User user : userMap.values()) {
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
        }
        return null;
    }


    public boolean updateUser(String userId, User updatedUser) {
        if (userExists(userId)) {
            databaseManager.deleteUser(userId);
            databaseManager.saveUser(updatedUser);
            return true;
        }
        return false; // UserId does not exist
    }

    public boolean deleteUser(String userId) {
        if (userExists(userId)) {
            databaseManager.deleteUser(userId);
            return true;
        }
        return false; // UserId does not exist
    }

    public Alert signup(User newUser) {
        if (userMap != null) {
            if (!UserMetadataValidator.isValidEmail(newUser.getEmail())) {
                return Alert.INVALID_EMAIL_FORMAT;
            }
            if (UserMetadataValidator.usernameExists(newUser.getUsername(), userMap)) {
                return Alert.USER_NAME_EXISTS;
            }
            if (UserMetadataValidator.emailExists(newUser.getEmail(), userMap)) {
                return Alert.USER_EMAIL_EXISTS;
            }
            if (!userMap.containsKey(newUser.getUserId()) && !UserMetadataValidator.emailExists(newUser.getEmail(), userMap)) {
                databaseManager.saveUser(newUser);
                // update database
                userMap = databaseManager.getUsers();
                return Alert.alerts.PROCESS_SUCCEEDED;
            }
        }

        return Alert.PROCESS_FAILED;
    }

    public User login(String email, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        User user = getUser(email, password);

        if (user != null) {
            user.setStatus("online");
            databaseManager.saveUser(user);
        }

        return user; // Return the authenticated user, or null if login failed
    }

    public User getUser(String email, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (userMap != null) {
            for (User user : userMap.values()) {
                String storedHash = user.getHashedPassword();
                String storedSalt = user.getSalt();
                if (user.getEmail().equals(email) && UserMetadataValidator.isValidEmail(email) && validationBehaviour.validatePassword(password, storedHash, storedSalt)) {
                    return user; // Authentication successful
                }
            }
        }
        return null; // Authentication failed
    }


    public boolean logout(String userId) {
        if (userExists(userId)) {
            User user = userMap.get(userId);
            user.setStatus("offline"); // Set the user's status to offline
            databaseManager.saveUser(user); // Save the updated user data
            return true;
        }

        return false; // User not found, logout failed
    }

    // Helper method to check if a user exists in the bdatabase
    public boolean userExists(String userId) {
        return userMap != null && userMap.containsKey(userId);
    }

    public void setUserPassword(String userId, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (userExists(userId)) {
            User user = userMap.get(userId);
            String[] hashedPasswordAndSalt = hashingBehaviour.hash(password);

            String hashedPassword = hashedPasswordAndSalt[0];
            String salt = hashedPasswordAndSalt[1];

            user.setHashedPassword(hashedPassword);
            user.setSalt(salt);

            databaseManager.saveUser(user);
        }
    }

    public void refreshContents() {
        databaseManager.loadAllContents();
        userMap = databaseManager.getUsers();
    }
}
