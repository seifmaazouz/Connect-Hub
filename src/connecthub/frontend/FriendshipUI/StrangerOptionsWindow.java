package connecthub.frontend.FriendshipUI;

import connecthub.backend.models.Friendship;
import connecthub.backend.models.Notification;
import connecthub.backend.models.User;
import connecthub.backend.services.FriendshipService;
import connecthub.backend.services.NotificationService;
import connecthub.backend.services.UserService;
import connecthub.frontend.FriendProfile;
import connecthub.frontend.homepage.ProfilePhoto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StrangerOptionsWindow extends JDialog{
    private JPanel panel1;
    private JButton viewProfileButton;
    private JButton blockButton;
    private JButton sendRequestButton;
    private JButton cancelButton;
    private JLabel bigProfilePhotoLabel;

    public StrangerOptionsWindow(Friendship friendship, String activeUserId, User stranger) throws IOException {
        setContentPane(panel1);
        setTitle("Friend Options");
        setMinimumSize(new Dimension(400, 400));
        setPreferredSize(new Dimension(400, 400));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

        Image profileImage = new ImageIcon(stranger.getProfilePhoto()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ProfilePhoto profilePhotoPanel = new ProfilePhoto(profileImage, 150, 2);
        bigProfilePhotoLabel.setLayout(new BorderLayout());
        bigProfilePhotoLabel.add(profilePhotoPanel, BorderLayout.CENTER);
        revalidate();
        repaint();

        viewProfileButton.addActionListener(e -> {
            System.out.println("View Profile Button Clicked");
            new FriendProfile(stranger).setVisible(true);
        });

        blockButton.addActionListener(e -> {
            friendship.block(activeUserId, stranger.getUserId());
            FriendshipService friendshipService = new FriendshipService();
            friendshipService.saveFriendship(friendship);
            dispose();
        });

        sendRequestButton.addActionListener(e -> {
            friendship.sendRequest(activeUserId, stranger.getUserId());
//            NotificationServiceOld notificationService = new NotificationServiceOld();
//            notificationService.addNotification(
//                    stranger.getUserId(),
//                    UserService.getInstance().getUserById(activeUserId).getUsername() + " has sent you a friend request.",
//                    Notification.Type.FRIEND_REQUEST,
//                    activeUserId);
            NotificationService notificationService1 = new NotificationService(stranger);
            notificationService1.sendNotification(Notification.Type.FRIEND_REQUEST, activeUserId, null);
//            try {
//                notificationService.saveNotifications();
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
            UserService userService = UserService.getInstance();
            userService.updateUser(stranger.getUserId(), stranger);
            userService.updateUser(activeUserId, userService.getUserById(activeUserId));
            FriendshipService friendshipService = new FriendshipService();
            friendshipService.saveFriendship(friendship);
            dispose();
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });
    }
}
