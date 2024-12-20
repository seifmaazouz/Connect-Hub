package connecthub.frontend.chatUI;

import connecthub.backend.models.User;
import connecthub.frontend.homepage.ProfilePhoto;

import javax.swing.*;
import java.awt.*;

public class ChatMessageEntry extends JPanel {
    private JPanel panel1;
    private JLabel bigProfilePhotoLabel;
    private JLabel contentLabel;
    private JLabel senderName;


    public ChatMessageEntry(User sender, String message, Color color) {
        add(panel1);

        Image profileImage = new ImageIcon(sender.getProfilePhoto()).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ProfilePhoto profilePhotoPanel = new ProfilePhoto(profileImage, 40, 2);
        bigProfilePhotoLabel.setLayout(new BorderLayout());
        bigProfilePhotoLabel.add(profilePhotoPanel, BorderLayout.CENTER);
        setVisible(true);
        revalidate();
        repaint();

        contentLabel.setText(message);
        contentLabel.setForeground(color);
    }
}
