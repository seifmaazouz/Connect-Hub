package connecthub.frontend.chatUI;

import connecthub.backend.models.User;
import connecthub.frontend.homepage.ProfilePhoto;

import javax.swing.*;
import java.awt.*;

public class ChatMessageEntry extends JPanel {
    private JPanel panel1;
    private JLabel bigProfilePhotoLabel;
    private JLabel contentLabel;


    public ChatMessageEntry(User sender, String message) {
        add(panel1);

        Image profileImage = new ImageIcon(sender.getProfilePhoto()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ProfilePhoto profilePhotoPanel = new ProfilePhoto(profileImage, 150, 2);
        bigProfilePhotoLabel.setLayout(new BorderLayout());
        bigProfilePhotoLabel.add(profilePhotoPanel, BorderLayout.CENTER);
        setVisible(true);
        revalidate();
        repaint();

        contentLabel.setText(message);
    }
}
