package connecthub.backend.profile;

import static connecthub.backend.constants.FilePath.DEFAULT_COVER_PHOTO;
import connecthub.backend.database.UserDatabase;
import connecthub.backend.models.User;
import connecthub.frontend.utils.ImageManager;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

public class UpdateCoverPhoto {

    private User user;
    private UserDatabase userDatabase;

    public UpdateCoverPhoto(User user, UserDatabase userDatabase) {
        this.user = user;
        this.userDatabase = userDatabase;
    }

    public ImageIcon update() throws IOException {
        // delete old cover photo path first
        String oldImagePath = user.getCoverPhoto();
        File oldImageFile = new File(oldImagePath);
        if(oldImageFile.exists() && oldImagePath.equals(DEFAULT_COVER_PHOTO)) {
            oldImageFile.delete();
        }
            
        File file = ImageManager.uploadImage();
        Image image = ImageManager.getImageFromFile(file, 600, 300);
        if (image != null) {
            ImageIcon coverPhoto = new ImageIcon(image);
            String path = ImageManager.copyImageToProgramFiles(user, file);
            user.setCoverPhoto(path);
            userDatabase.saveUser(user);
            return coverPhoto;
        }
        return null;
    }
}
