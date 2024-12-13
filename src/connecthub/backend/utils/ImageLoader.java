package connecthub.backend.utils;

import javax.swing.*;
import java.awt.*;

public class ImageLoader {
    public static Image loadImage(String path) {
        ImageIcon imageIcon = new ImageIcon(path); // Loads the image
        return imageIcon.getImage(); // Converts ImageIcon to Image
    }
}
