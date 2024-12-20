package connecthub.frontend.FriendshipUI;

import connecthub.backend.models.Friendship;
import connecthub.backend.models.User;
import connecthub.backend.services.FriendshipService;
import connecthub.backend.services.UserService;
import connecthub.frontend.FriendProfile;
import connecthub.frontend.chatUI.chatWindow;
import connecthub.frontend.homepage.ProfilePhoto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FriendOptionsWindow extends JDialog{
    private JPanel panel1;
    private JButton viewProfileButton;
    private JButton blockButton;
    private JButton unfriendButton;
    private JButton cancelButton;
    private JLabel bigProfilePhotoLabel;
    private JButton messageButton;

    public FriendOptionsWindow(Friendship friendship, String activeUserId, User friend) throws IOException {
        setContentPane(panel1);
        setTitle("Friend Options");
        setMinimumSize(new Dimension(400, 400));
        setPreferredSize(new Dimension(400, 400));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

        Image profileImage = new ImageIcon(friend.getProfilePhoto()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ProfilePhoto profilePhotoPanel = new ProfilePhoto(profileImage, 150, 2);
        bigProfilePhotoLabel.setLayout(new BorderLayout());
        bigProfilePhotoLabel.add(profilePhotoPanel, BorderLayout.CENTER);
        revalidate();
        repaint();

        viewProfileButton.addActionListener(e -> {
            System.out.println("View Profile Button Clicked");
            new FriendProfile(friend).setVisible(true);
        });

        messageButton.addActionListener(e -> {
            System.out.println("Message Button Clicked");
            try {
                new chatWindow(UserService.getInstance().getUserById(activeUserId), friend).setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        blockButton.addActionListener(e -> {
            friendship.block(activeUserId, friend.getUserId());
            FriendshipService friendshipService = new FriendshipService();
            friendshipService.saveFriendship(friendship);
            dispose();
        });

        unfriendButton.addActionListener(e -> {
            friendship.unFriend(activeUserId, friend.getUserId());
            FriendshipService friendshipService = new FriendshipService();
            friendshipService.saveFriendship(friendship);
            dispose();
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });
    }
}
