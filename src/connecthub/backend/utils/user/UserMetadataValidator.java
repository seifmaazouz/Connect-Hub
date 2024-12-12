package connecthub.backend.utils.user;
import connecthub.backend.models.User;

import java.util.HashMap;
import java.util.regex.Pattern;

public class UserMetadataValidator {
    // Define the regex pattern for a valid email
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static boolean isValidEmail(String email) {
        // Compile the regex and match it with the input email
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean emailExists(String newUserEmail, HashMap<String, User> users) {
        for (User user: users.values()) {
            if (user.getEmail().equals(newUserEmail))
                return true;
        }
        return false;
    }


    public static boolean usernameExists(String newUsername, HashMap<String, User> users) {
        for (User user: users.values()) {
            if (user.getUsername().equals(newUsername))
                return true;
        }
        return false;
    }
}

