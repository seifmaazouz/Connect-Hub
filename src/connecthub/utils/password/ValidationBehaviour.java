package connecthub.utils.password;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface ValidationBehaviour {
    boolean validatePassword(String password, String storedHash, String storedSalt) throws InvalidKeySpecException, NoSuchAlgorithmException;
}
