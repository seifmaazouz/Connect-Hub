package connecthub.backend.constants;

import java.io.File;
import javax.swing.filechooser.FileSystemView;

public interface FilePath {
    String USERS_FILE_PATH = "src/connecthub/backend/database/files/users.json";
    String POSTS_FILE_PATH = "src/connecthub/backend/database/files/posts.json";
    String STORIES_FILE_PATH = "src/connecthub/backend/database/files/stories.json";
    String FRIENDS_FILE_PATH = "src/connecthub/backend/database/files/friendship.json";
    String COUNTER_FILE = "src/connecthub/backend/database/files/userIdCounter.json";
    String IMAGE_SAVE_DIRECTORY = "src/connecthub/backend/database/images";
    File UPLOAD_IMAGE_DIRECTORY = FileSystemView.getFileSystemView().getHomeDirectory(); // get home directory
    String DEFAULT_PROFILE_PHOTO = IMAGE_SAVE_DIRECTORY + File.separator + "defaultProfilePhoto.jpg";
    String DEFAULT_COVER_PHOTO = IMAGE_SAVE_DIRECTORY + File.separator + "defaultCoverPhoto.jpg";
}
