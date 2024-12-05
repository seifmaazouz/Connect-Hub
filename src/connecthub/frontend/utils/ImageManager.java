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


public class ImageManager {
    
    public static String copyImageToProgramFiles(User user, File imageFile) {
        if(imageFile == null)
            return null;
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
    public static File uploadImage(File UPLOAD_IMAGE_DIRECTORY) {
        JFileChooser fileChooser = createFileChooser(UPLOAD_IMAGE_DIRECTORY);
        if (fileChooser == null) return null;
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
    public static JFileChooser createFileChooser(File UPLOAD_IMAGE_DIRECTORY) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg")); // Show only image files
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Upload Image");
        // Create the folder if it doesn't exist in documents
        if (!UPLOAD_IMAGE_DIRECTORY.exists()) {
            boolean isCreated = UPLOAD_IMAGE_DIRECTORY.mkdirs();
            if (!isCreated) {
                System.out.println("Failed to create the directory.");
                return null;
            }
        }
        fileChooser.setCurrentDirectory(UPLOAD_IMAGE_DIRECTORY);
        return fileChooser;
    }
}
