package connecthub.frontend.FriendshipUI;

import connecthub.backend.models.Friendship;
import connecthub.backend.services.FriendshipService;

import javax.swing.*;
import java.io.IOException;

public class FriendshipManagementMainWindow extends JFrame {
    private JPanel panel1;
    private JTabbedPane friendshipPanel;
    private JButton refreshButton;
    Friendship friendship;

    public FriendshipManagementMainWindow(Friendship friendship, String activeUserId) throws IOException {
        this.friendship = friendship;
        setContentPane(panel1);
        setTitle("Friendship Management");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

        JPanel friendsPanel = new FriendListPanel(friendship, activeUserId);
        friendshipPanel.addTab("Friends", friendsPanel);
        JPanel friendRequestsPanel = new FriendRequestsPanel(friendship, activeUserId);
        friendshipPanel.addTab("Friend Requests", friendRequestsPanel);
        JPanel sentRequestsPanel = new SentRequestsPanel(friendship, activeUserId);
        friendshipPanel.addTab("Sent Requests", sentRequestsPanel);
        JPanel blockedPanel = new BlockedUsersPanel(friendship, activeUserId);
        friendshipPanel.addTab("Blocked Users", blockedPanel);

        refreshButton.addActionListener(e -> {
            friendsPanel.removeAll();
            friendsPanel.add(new FriendListPanel(friendship, activeUserId));
            friendsPanel.revalidate();
            friendsPanel.repaint();
            friendRequestsPanel.removeAll();
            friendRequestsPanel.add(new FriendRequestsPanel(friendship, activeUserId));
            friendRequestsPanel.revalidate();
            friendRequestsPanel.repaint();
            sentRequestsPanel.removeAll();
            sentRequestsPanel.add(new SentRequestsPanel(friendship, activeUserId));
            sentRequestsPanel.revalidate();
            sentRequestsPanel.repaint();
            blockedPanel.removeAll();
            blockedPanel.add(new BlockedUsersPanel(friendship, activeUserId));
            blockedPanel.revalidate();
            blockedPanel.repaint();
        });
    }

    public static void main(String[] args) throws IOException {
        Friendship friendship = (new FriendshipService()).loadFriendship();
        new FriendshipManagementMainWindow(friendship, "1");
    }
}
