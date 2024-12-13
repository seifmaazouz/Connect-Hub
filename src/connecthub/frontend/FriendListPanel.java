package connecthub.frontend;

import connecthub.backend.loaders.UserLoader;
import connecthub.backend.models.Friendship;
import connecthub.backend.models.User;
import connecthub.backend.services.FriendshipService;
import connecthub.backend.services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static connecthub.backend.constants.FilePath.FRIENDS_FILE_PATH;

public class FriendListPanel extends JPanel {
    private JList friendsList;
    private JPanel panel1;
    Friendship friendship;
    String activeUserId;
    HashMap<String, String> onlineStatus;

    public FriendListPanel(Friendship friendship, String activeUserId) {
        friendsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.onlineStatus = new HashMap<>();

        friendsList.setCellRenderer(new ListCellRenderer<String>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
                DefaultListCellRenderer renderer = new DefaultListCellRenderer();

                // Set the default renderer properties
                Component component = renderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Set custom color based on the item
                if (onlineStatus.get(value).equals("online")) {
                    component.setForeground(Color.decode("#00A000"));
                } else {
                    component.setForeground(Color.decode("#808080"));
                }

                // Return the customized component
                return component;
            }
        });

        friendsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = friendsList.locationToIndex(e.getPoint());

                // If an item is clicked (not clicking outside the list)
                if (index >= 0) {
                    String clickedItem = (String) friendsList.getModel().getElementAt(index);
                    // Show a message when the item is clicked
                    System.out.println("You clicked: " + clickedItem);
                }
            }
        });

        this.friendship = friendship;
        this.activeUserId = activeUserId;
        add(panel1);
        friendsList.setListData(getListContents().toArray());
        setVisible(true);
    }

    private ArrayList<String> getListContents() {
        ArrayList<String> userIds = this.friendship.getUserFriends(activeUserId);
        UserService userService = new UserService();

        for (String userId : userIds) {
            onlineStatus.put(userService.getUserById(userId).getUsername(), userService.getUserById(userId).getStatus());
        }
        return new ArrayList<>(onlineStatus.keySet());
    }
}
