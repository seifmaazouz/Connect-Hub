package connecthub.utils.password;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PBKDF2Validation implements ValidationBehaviour {
    @Override
    public boolean validatePassword(String password, String storedHash, String storedSalt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        // Decode the stored salt
        byte[] salt = Base64.getDecoder().decode(storedSalt);

        // Concatenate password and stored salt
        String passwordAndSalt = password + storedSalt;

        // Hash the concatenated password+salt
        KeySpec spec = new PBEKeySpec(passwordAndSalt.toCharArray(), salt, 65536, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();

        // Encode the calculated hash for comparison
        String calculatedHash = Base64.getEncoder().encodeToString(hash);

        // Compare the calculated hash with the stored hash
        return calculatedHash.equals(storedHash);
    }

}
