package connecthub.backend.models;

import connecthub.backend.services.UserService;
import connecthub.backend.utils.hashing.HashingBehaviour;
import connecthub.backend.utils.hashing.PBKDF2Hashing;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

public class User {
    private String userId;
    private String email;
    private String username;
    private String password;
    private Date dateOfBirth;
    private String status;
    private String hashedPassword;
    private String salt;
    private transient HashingBehaviour hashingBehaviour;

    public User() {

    }

    public User(String email, String username, String password, Date dateOfBirth, String status) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        this.userId = UserService.numberOfUsers + "";
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.hashingBehaviour = new PBKDF2Hashing();
        String[] hashedPasswordWithSalt = hashingBehaviour.hash(password);
        this.hashedPassword = hashedPasswordWithSalt[0];
        this.salt = hashedPasswordWithSalt[1];
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOnline() {
        return status.equals("online");
    }

}