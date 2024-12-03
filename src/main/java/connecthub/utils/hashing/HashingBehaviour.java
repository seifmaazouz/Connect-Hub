package connecthub.utils.hashing;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface HashingBehaviour {
    String[] hash(String input) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
