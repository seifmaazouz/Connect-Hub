package connecthub.frontend.utils;

import connecthub.backend.models.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import static connecthub.backend.constants.FilePath.IMAGE_SAVE_DIRECTORY;
import static connecthub.backend.constants.FilePath.UPLOAD_IMAGE_DIRECTORY;
import java.awt.Image;
import javax.imageio.ImageIO;

public class ImageManager {

    public static String copyImageToProgramFiles(User user, File imageFile) {
        if (imageFile == null) {
            return null;
        }
        // Convert the File objects to Path objects
        Path sourcePath = imageFile.toPath();
        // check if directory exists, if it doesnt crrate directory
        File directory = new File(IMAGE_SAVE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        // Format LocalDateTime without invalid characters
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = LocalDateTime.now().format(formatter);

        // Create path with filename consisting of userId + current time to be unique
        File destinationFile = new File(IMAGE_SAVE_DIRECTORY + File.separator + user.getUserId() + "_" + timestamp + ".jpg");
        Path destinationPath = destinationFile.toPath();
        // Copy the file to the destination
        try {
            Files.copy(sourcePath, destinationPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return destinationPath.toString();
    }

    // uploads image
    public static File uploadImage() {
        JFileChooser fileChooser = createFileChooser();
        if (fileChooser == null) {
            return null;
        }
        int choice = fileChooser.showOpenDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String selectedImagePath = selectedFile.getAbsolutePath().toLowerCase();
            // Check if the file ends with one of the allowed extensions
            if (!selectedImagePath.endsWith(".png") && !selectedImagePath.endsWith(".jpg") && !selectedImagePath.endsWith(".jpeg")) {
                JOptionPane.showMessageDialog(null, "Error: Please select a valid image file (PNG, JPG, JPEG)!", "Invalid File", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            // Check if the selected file exists
            if (!selectedFile.exists()) {
                JOptionPane.showMessageDialog(null, "Error: \"" + selectedFile.getName() + "\" file does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return selectedFile;
        }
        return null;
    }

    // creates file chooser for uploading image
    public static JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg")); // Show only image files
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Upload Image");
        // Xheck if directory doesn't exist in documents
        if (!UPLOAD_IMAGE_DIRECTORY.exists()) {
                System.out.println("Failed to load the directory.");
                return null;
        }
        fileChooser.setCurrentDirectory(UPLOAD_IMAGE_DIRECTORY);
        return fileChooser;
    }

    public static Image getImageFromFile(File file, int x, int y) throws IOException {
        if (file == null) {
            return null;
        }
        Image image = ImageIO.read(file);
        image = image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        return image;
    }
}