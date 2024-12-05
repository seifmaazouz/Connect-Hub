package main.java.connecthub.backend.services;

import main.java.connecthub.backend.database.*;
import main.java.connecthub.backend.models.*;
import main.java.connecthub.backend.utils.password.PBKDF2Validation;
import main.java.connecthub.backend.utils.password.ValidationBehaviour;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

public class UserService {

    private final DatabaseManager databaseManager;
    private final ValidationBehaviour validationBehaviour;
    private HashMap<String, User> userMap;

    public UserService() {
        this.validationBehaviour = new PBKDF2Validation();
        this.databaseManager = new DatabaseManager();
        this.userMap = databaseManager.getUserMap();
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
     * Add a new user.
     *
     * @param newUser The User object to add.
     * @return true if the user was added successfully, false if the userId already exists.
     */
    public boolean addUser(User newUser) {
        if (userMap != null && !userMap.containsKey(newUser.getUserId())) {
            userMap.put(newUser.getUserId(), newUser);
            databaseManager.saveUserMap(userMap);
            return true;
        }
        return false; // User already exists
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
            userMap.put(userId, updatedUser); // Replace the existing user with the updated one
            databaseManager.saveUserMap(userMap);
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
            userMap.remove(userId);
            databaseManager.saveUserMap(userMap);
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
    public boolean signup(User newUser) {
        if (userMap != null && !userMap.containsKey(newUser.getUserId())) {
            // User does not already exist, so we can add them
            userMap.put(newUser.getUserId(), newUser);
            databaseManager.saveUserMap(userMap); // Save the updated user map to file
            return true;
        }
        return false; // UserId already exists, signup failed
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
            databaseManager.saveUserMap(userMap);
        }

        return user; // Return the authenticated user, or null if login failed
    }

    private User getUser(String email, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (userMap != null) {
            for (User user : userMap.values()) {
                String storedHash = user.getHashedPassword();
                String storedSalt = user.getSalt();
                if (user.getEmail().equals(email) && validationBehaviour.validatePassword(password, storedHash, storedSalt)) {
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
            databaseManager.saveUserMap(userMap); // Save the updated user data
            return true;
        }

        return false; // User not found, logout failed
    }

    // Helper method to check if a user exists in the database
    public boolean userExists(String userId) {
        return userMap != null && userMap.containsKey(userId);
    }

}

