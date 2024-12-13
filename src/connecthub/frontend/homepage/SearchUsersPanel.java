package connecthub.frontend.homepage;

import connecthub.backend.loaders.UserLoader;
import connecthub.backend.models.Friendship;
import connecthub.backend.models.User;
import connecthub.backend.services.UserService;
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

    public SearchUsersPanel(Friendship friendship, String activeUserId) throws IOException {
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
                    try {
                        new StrangerOptionsWindow(friendship, activeUserId, UserService.getInstance().getUserByUsername(clickedItem));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        searchBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String query = searchBox.getText();
                resultsList.setListData(searchUsers(friendship, activeUserId, query).toArray());
            }
        });

        add(panel1);
        setVisible(true);
    }

    private ArrayList<String> searchUsers(Friendship friendship, String activeUserId, String query) {
        HashMap<String, User> users = new UserLoader().loadUsers();
        ArrayList<String> results = new ArrayList<>();
        for (String userId : users.keySet()) {
            if (!userId.contains(query)) {
                users.remove(userId);
            }
        }
        for (String userId : users.keySet()) {
            if (friendship.hasBlocked(activeUserId, userId)) {
                users.remove(userId);
            }
        }
        for (String userId : users.keySet()) {
            results.add(users.get(userId).getUsername());
        }
        return results;
    }
}
