package connecthub.constants;

public interface Constants {
    int SALT_LENGTH = 16;
    int ITERATIONS_FOR_PBKDF2 = 65536;
    int KEY_LENGTH_FOR_PBKDF2 = 256;
    long EXPIRATION_AFTER_SET_HOURS = 24;
}
