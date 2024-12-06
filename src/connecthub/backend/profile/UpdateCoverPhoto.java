package connecthub.backend.profile;

import connecthub.backend.models.User;
import connecthub.frontend.utils.ImageManager;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

public class UpdateCoverPhoto {

    User user;

    public UpdateCoverPhoto(User user) {
        this.user = user;
    }

    public ImageIcon update() throws IOException {
        File file = ImageManager.uploadImage();
        Image image = ImageManager.getImageFromFile(file, 600, 400);
        if (image != null) {
            ImageIcon coverPhoto = new ImageIcon(image);
            String path = ImageManager.copyImageToProgramFiles(user, file);
            user.setCoverPhoto(path);
            return coverPhoto;
        }
        return null;
    }
}
