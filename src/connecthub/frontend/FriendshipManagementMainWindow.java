package connecthub.frontend;

import connecthub.backend.loaders.UserLoader;
import connecthub.backend.models.Friendship;
import connecthub.backend.models.User;
import connecthub.backend.services.FriendshipService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


import static connecthub.backend.constants.FilePath.FRIENDS_FILE_PATH;

public class FriendshipManagementMainWindow extends JFrame {
    private JPanel panel1;
    private JTabbedPane friendshipPanel;
    private JPanel friendsPanel;
    private JPanel receivedRequestsPanel;
    private JPanel sentRequestsPanel;
    private JPanel blockedPanel;
    private JButton refreshButton;

    private final HashMap<String, User> users;

    public FriendshipManagementMainWindow(Friendship friendship, String activeUserId) throws IOException {
        setContentPane(panel1);
        setTitle("Friendship Management");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

        this.users = (new UserLoader()).loadUsers();

        this.friendsPanel = new FriendListPanel(friendship, activeUserId);
        friendshipPanel.addTab("Friends", friendsPanel);


        refreshButton.addActionListener(e -> {
            System.out.println("Refresh Button Clicked");
        });
    }

    private ArrayList<String> getListContents(ArrayList<String> userIds) {
        ArrayList<String> usernames = new ArrayList<>();
        for (String userId : userIds) {
            usernames.add(users.get(userId).getUsername());
        }
        return usernames;
    }

    public static void main(String[] args) throws IOException {
        HashMap<String, User> users = (new UserLoader()).loadUsers();
        Friendship friendship1 = (new FriendshipService(FRIENDS_FILE_PATH)).loadFriendship();
        new FriendshipManagementMainWindow(friendship1, "1");
    }
}
