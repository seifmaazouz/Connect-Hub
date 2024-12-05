package connecthub.backend.utils.password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SHA256Validation implements ValidationBehaviour {

    @Override
    public boolean validatePassword(String password, String storedHash, String storedSalt) {
        try {
            // Decode the stored salt
            byte[] salt = Base64.getDecoder().decode(storedSalt);

            // Concatenate password and salt
            String passwordAndSalt = password + Base64.getEncoder().encodeToString(salt);

            // Hash the concatenated password+salt using SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(passwordAndSalt.getBytes());

            // Encode the hash for comparison
            String encodedHash = Base64.getEncoder().encodeToString(hashBytes);

            // Compare the encoded hash with the stored hash
            return encodedHash.equals(storedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}