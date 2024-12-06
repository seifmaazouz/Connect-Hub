package connecthub.backend.profile;

import connecthub.backend.database.UserDatabase;
import connecthub.backend.models.User;
import connecthub.frontend.utils.ImageManager;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

public class UpdateProfilePhoto {

    private User user;
    private UserDatabase userDatabase;

    public UpdateProfilePhoto(User user, UserDatabase userDatabase) {
        this.user = user;
        this.userDatabase = userDatabase;
    }

    public ImageIcon update() throws IOException {
        File file = ImageManager.uploadImage();
        Image image = ImageManager.getImageFromFile(file, 150, 150);
        if (image != null) {
            ImageIcon profilePhoto = new ImageIcon(image);
            String path = ImageManager.copyImageToProgramFiles(user, file);
            user.setProfilePhoto(path);
            userDatabase.saveUser(user);
            return profilePhoto;
        }
        return null;
    }
}
