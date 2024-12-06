package connecthub.backend.models;

import connecthub.backend.services.UserService;
import connecthub.backend.utils.hashing.HashingBehaviour;
import connecthub.backend.utils.hashing.PBKDF2Hashing;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

public class User {

    private String userId;
    private String email;
    private String username;
    private Date dateOfBirth;
    private String status;
    private String hashedPassword;
    private String salt;
    private String profilePhoto;
    private String coverPhoto;
    private String bio;
    private Friendship friendship;

    public User() {

    }

    public User(String userId, String email, String username, Date dateOfBirth, String status, String hashedPassword, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.profilePhoto = "src\\connecthub\\backend\\database\\images\\defaultProfilePhoto.jpg";
        this.coverPhoto = "src\\connecthub\\backend\\database\\images\\defaultCoverPhoto.jpg";
        this.bio = null;
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

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Friendship getFriendship() {
        return friendship;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public String getBio() {
        return bio;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
