package connecthub.frontend.FriendshipUI;

import connecthub.backend.models.Friendship;
import connecthub.backend.models.User;
import connecthub.backend.services.FriendshipService;
import connecthub.frontend.homepage.ProfilePhoto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BlockedOptionsWindow extends JDialog {
    private JPanel panel1;
    private JLabel bigProfilePhotoLabel;
    private JButton unblockButton;
    private JButton cancelButton;
    Friendship friendship;

    public BlockedOptionsWindow(Friendship friendship, String activeUserId, User blockedUser) {
        this.friendship = friendship;
        setContentPane(panel1);
        setTitle("Block Options");
        setMinimumSize(new Dimension(400, 400));
        setPreferredSize(new Dimension(400, 400));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

        Image profileImage = new ImageIcon(blockedUser.getProfilePhoto()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ProfilePhoto profilePhotoPanel = new ProfilePhoto(profileImage, 150, 2);
        bigProfilePhotoLabel.setLayout(new BorderLayout());
        bigProfilePhotoLabel.add(profilePhotoPanel, BorderLayout.CENTER);
        revalidate();
        repaint();

        unblockButton.addActionListener(e -> {
            FriendshipService friendshipService = new FriendshipService();
            friendship.unBlock(activeUserId, blockedUser.getUserId());
            friendshipService.saveFriendship(this.friendship);
            dispose();
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });
    }
}
