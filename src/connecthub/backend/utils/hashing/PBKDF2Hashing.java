package connecthub.backend.utils.hashing;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import static connecthub.backend.constants.Constants.*;

public class PBKDF2Hashing implements HashingBehaviour {
    @Override
    public String[] hash(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        // Concatenate password and salt
        String passwordAndSalt = password + Base64.getEncoder().encodeToString(salt);

        // Hash the concatenated password+salt
        KeySpec spec = new PBEKeySpec(passwordAndSalt.toCharArray(), salt, ITERATIONS_FOR_PBKDF2, KEY_LENGTH_FOR_PBKDF2);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();

        // Encode hash and salt for storage
        String encodedHash = Base64.getEncoder().encodeToString(hash);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);

        return new String[]{encodedHash, encodedSalt};
    }
}
