package connecthub.backend.services;

import connecthub.backend.database.UserDatabase;
import connecthub.backend.models.User;
import connecthub.backend.utils.email.EmailValidator;
import connecthub.backend.utils.password.PBKDF2Validation;
import connecthub.backend.utils.password.ValidationBehaviour;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

public class UserService {

    private final UserDatabase databaseManager;
    private final ValidationBehaviour validationBehaviour;
    private HashMap<String, User> userMap;
    public static int numberOfUsers = 0;
    public UserService() {
        this.validationBehaviour = new PBKDF2Validation();
        this.databaseManager = new UserDatabase();
        this.userMap = databaseManager.getUsers();
        numberOfUsers++;
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
    public boolean signup(User newUser) {
        if (userMap != null && !userMap.containsKey(newUser.getUserId())) {
            databaseManager.saveUser(newUser);
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
            databaseManager.saveUser(user);
        }

        return user; // Return the authenticated user, or null if login failed
    }

    public User getUser(String email, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (userMap != null) {
            for (User user : userMap.values()) {
                String storedHash = user.getHashedPassword();
                String storedSalt = user.getSalt();
                if (user.getEmail().equals(email) && EmailValidator.isValidEmail(email) && validationBehaviour.validatePassword(password, storedHash, storedSalt)) {
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

    // Helper method to check if a user exists in the bdatabase
    public boolean userExists(String userId) {
        return userMap != null && userMap.containsKey(userId);
    }

//    public HashMap<String, User> searchUsers(HashMap<String, User> users, String searchQuery, User activeUser) {
//        HashMap<String, User> searchResults = new HashMap<>();
//        if (userMap != null) {
//            for (User user : users.values()) {
//                if (user.getUsername().contains(searchQuery) &&
//                        !user.getUserId().equals(activeUser.getUserId()) &&
//                        !activeUser.getFriendShip().isBlockedBy(user) &&
//                        !activeUser.getFriendShip().isBlocked(user)
//                ) {
//                    searchResults.put(user.getUserId(), user);
//                }
//            }
//        }
//        return searchResults;
//    }
}

