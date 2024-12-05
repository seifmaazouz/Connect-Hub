package connecthub.backend.utils.email;
import java.util.regex.Pattern;

public class EmailValidator {
    // Define the regex pattern for a valid email
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static boolean isValidEmail(String email) {
        // Compile the regex and match it with the input email
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }
}

