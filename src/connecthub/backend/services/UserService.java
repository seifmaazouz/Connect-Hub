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

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static connecthub.backend.constants.FilePath.COUNTER_FILE;

public class UserService {

    private static AtomicInteger userIdCounter;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final HashingBehaviour hashingBehaviour;

    private static class CounterData {
        private int counter;

        public CounterData() {
        }

        public CounterData(int counter) {
            this.counter = counter;
        }

        public int getCounter() {
            return counter;
        }

        public void setCounter(int counter) {
            this.counter = counter;
        }
    }


    private final UserDatabase databaseManager;
    private final ValidationBehaviour validationBehaviour;
    private HashMap<String, User> userMap;

    public UserService() {
        this.validationBehaviour = new PBKDF2Validation();
        this.databaseManager = new UserDatabase();
        this.userMap = databaseManager.getUsers();
        this.hashingBehaviour = new PBKDF2Hashing();
    }

    static {
        // Initialize counter from file
        userIdCounter = new AtomicInteger(loadCounter());
    }

    /**
     * Loads the counter value from the JSON file. If the file doesn't exist or is invalid,
     * it initializes the counter to 0.
     *
     * @return the initial counter value
     */
    private static int loadCounter() {
        File file = new File(COUNTER_FILE);
        if (!file.exists()) {
            return 0; // Start from 0 if the file doesn't exist
        }
        try {
            CounterData counterData = objectMapper.readValue(file, CounterData.class);
            return counterData.getCounter();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Saves the current counter value to the JSON file.
     */
    private static void saveCounter() {
        File file = new File(COUNTER_FILE);
        try {
            CounterData counterData = new CounterData(userIdCounter.get());
            objectMapper.writeValue(file, counterData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a unique ID for a new user. Increments the counter and saves it to the JSON file.
     *
     * @return the generated user ID
     */
    public static synchronized int generateUserId() {
        int newId = userIdCounter.incrementAndGet();
        saveCounter(); // Persist the updated counter
        return newId;
    }

    /**
     * Get a user by their userId.
     *
     * @param userId The ID of the user to fetch.
     * @return The User object if found, null otherwise.
     */
    public User getUserById(String userId) {
        return (userMap != null) ? userMap.get(userId) : null;
    }


    /**
     * Update an existing user's information.
     *
     * @param userId      The ID of the user to update.
     * @param updatedUser The updated User object.
     * @return true if the user was updated successfully, false if the userId does not exist.
     */
    public boolean updateUser(String userId, User updatedUser) {
        if (userExists(userId)) {
            databaseManager.deleteUser(userId);
            databaseManager.saveUser(updatedUser);
            return true;
        }
        return false; // UserId does not exist
    }

    /**
     * Delete a user by their userId.
     *
     * @param userId The ID of the user to delete.
     * @return true if the user was deleted successfully, false if the userId does not exist.
     */
    public boolean deleteUser(String userId) {
        if (userExists(userId)) {
            databaseManager.deleteUser(userId);
            return true;
        }
        return false; // UserId does not exist
    }


    /**
     * User signup (register) functionality.
     *
     * @param newUser The User object containing the user's details.
     * @return true if user is successfully created, false if userId already exists.
     */
    public Alert.alerts signup(User newUser) {
        if (userMap != null) {
            if (!UserMetadataValidator.isValidEmail(newUser.getEmail())) {
                return Alert.alerts.INVALID_EMAIL_FORMAT;
            }
            if (UserMetadataValidator.usernameExists(newUser.getUsername(), userMap)) {
                return Alert.alerts.USER_NAME_EXISTS;
            }
            if (UserMetadataValidator.emailExists(newUser.getEmail(), userMap)) {
                return Alert.alerts.USER_EMAIL_EXISTS;
            }
            if (!userMap.containsKey(newUser.getUserId()) && !UserMetadataValidator.emailExists(newUser.getEmail(), userMap)) {
                databaseManager.saveUser(newUser);
                // update database
                userMap = databaseManager.getUsers();
                return Alert.alerts.PROCESS_SUCCEEDED;
            }
        }

        return Alert.alerts.PROCESS_FAILED;
    }

    /**
     * User login functionality.
     *
     * @param email    The user's email.
     * @param password The user's password.
     * @return The User object if login is successful, null if credentials are incorrect.
     */
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

    /**
     * User logout functionality. Sets the user's status to "offline".
     *
     * @param userId The user's ID to change their status to offline.
     * @return true if logout is successful and status is updated, false if user does not exist.
     */
    public boolean logout(String userId) {
        if (userExists(userId)) {
            User user = userMap.get(userId);
            user.setStatus("offline"); // Set the user's status to offline
            databaseManager.saveUser(user); // Save the updated user data
            return true;
        }

        return false; // User not found, logout failed
    }

    // Helper method to check if a user exists in the database
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
}
