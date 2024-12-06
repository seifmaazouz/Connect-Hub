package connecthub.backend.utils.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import static connecthub.backend.constants.Constants.SALT_LENGTH;

public class SHA256Hashing implements HashingBehaviour {

    @Override
    public String[] hash(String password) throws NoSuchAlgorithmException {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        // Concatenate password and salt
        String passwordAndSalt = password + Base64.getEncoder().encodeToString(salt);

        // Hash the concatenated password+salt using SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(passwordAndSalt.getBytes());

        // Encode hash and salt for storage
        String encodedHash = Base64.getEncoder().encodeToString(hashBytes);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);

        return new String[]{encodedHash, encodedSalt};
    }
}
