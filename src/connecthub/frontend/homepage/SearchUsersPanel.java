package connecthub.frontend.homepage;

import connecthub.backend.loaders.UserLoader;
import connecthub.backend.models.Friendship;
import connecthub.backend.models.User;
import connecthub.backend.services.UserService;
import connecthub.frontend.FriendshipUI.FriendOptionsWindow;
import connecthub.frontend.FriendshipUI.FriendRequestOptionsWindow;
import connecthub.frontend.FriendshipUI.SentRequestsOptionsWindow;
import connecthub.frontend.FriendshipUI.StrangerOptionsWindow;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchUsersPanel extends JPanel {
    private JTextField searchBox;
    private JList resultsList;
    private JPanel panel1;
    HashMap<String, User> users;

    public SearchUsersPanel(Friendship friendship, String activeUserId) {
        resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        resultsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = resultsList.locationToIndex(e.getPoint());

                // If an item is clicked (not clicking outside the list)
                if (index >= 0) {
                    String clickedItem = (String) resultsList.getModel().getElementAt(index);
                    // Show a message when the item is clicked
                    System.out.println("You clicked: " + clickedItem);
                    // check what clicked user status is and call the right window
                    String clickedUserId = UserService.getInstance().getUserByUsername(clickedItem).getUserId();
                    if (friendship.isFriend(activeUserId, clickedUserId)) {
                        try {
                            new FriendOptionsWindow(friendship, activeUserId, UserService.getInstance().getUserByUsername(clickedItem));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else if (friendship.hasSentRequest(activeUserId, clickedUserId)) {
                        try {
                            new SentRequestsOptionsWindow(friendship, activeUserId, UserService.getInstance().getUserByUsername(clickedItem));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else if (friendship.hasReceivedRequest(activeUserId, clickedUserId)) {
                        try {
                            new FriendRequestOptionsWindow(friendship, activeUserId, UserService.getInstance().getUserByUsername(clickedItem));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        try {
                            new StrangerOptionsWindow(friendship, activeUserId, UserService.getInstance().getUserByUsername(clickedItem));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        searchBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Check if the Enter key (keyCode 10) is pressed
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Handle Enter key press
                    System.out.println("Enter key was pressed!");
                    // You can perform any action here, such as closing the window, submitting data, etc.
                    String query = searchBox.getText();
                    System.out.println("Searching for: " + query);
                    resultsList.setListData(searchUsers(friendship, activeUserId, query).toArray());
                }
            }
        });

        add(panel1);
        setVisible(true);
    }

    private ArrayList<String> searchUsers(Friendship friendship, String activeUserId, String query) {
        HashMap<String, User> users = new UserLoader().loadUsers();
        ArrayList<String> results = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getUsername().contains(query)) {
                results.add(user.getUsername());
            }
        }
        for (User user : users.values()) {
            if (friendship.hasBlocked(activeUserId, user.getUserId())) {
                results.remove(user.getUsername());
            }
        }
        return results;
    }
}