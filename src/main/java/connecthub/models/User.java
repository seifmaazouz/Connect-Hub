package main.java.connecthub.models;

import connecthub.utils.hashing.HashingBehaviour;
import connecthub.utils.hashing.PBKDF2Hashing;
import connecthub.utils.hashing.SHA256Hashing;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;

public class User {
    private String userId;
    private String email;
    private String username;
    private String password;
    private Date dateOfBirth;
    private String status;
    private String[] hashedPasswordWithSalt;
    private HashingBehaviour hashingBehaviour;

    private ArrayList <Friendship> friends;

    public User(String userId, String email, String username, String password, Date dateOfBirth, String status) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.hashingBehaviour = new PBKDF2Hashing();
        this.hashedPasswordWithSalt = hashingBehaviour.hash(password);
    }


    public String getHashedPassword() {
        return hashedPasswordWithSalt[0];
    }
    public String getSalt() {
        return hashedPasswordWithSalt[1];
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
}