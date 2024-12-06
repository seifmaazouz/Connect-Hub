package connecthub.backend.profile;

public class ProfileProperties {

    private String profilePhoto;
    private String coverphoto;
    private String bio;
    private String password; // to be hashed

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setCoverphoto(String coverphoto) {
        this.coverphoto = coverphoto;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public String getCoverphoto() {
        return coverphoto;
    }

    public String getBio() {
        return bio;
    }

    public String getPassword() {
        return password;
    }
}
