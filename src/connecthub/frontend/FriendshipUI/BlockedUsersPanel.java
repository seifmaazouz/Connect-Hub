
package connecthub.frontend.FriendshipUI;

import connecthub.backend.models.Friendship;
import connecthub.backend.services.UserService;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class BlockedUsersPanel extends JPanel {
    private JList blockedUsersList;
    private JPanel panel1;
    private Friendship friendship;
    private String activeUserId;

    public BlockedUsersPanel(Friendship friendship, String activeUserId) {
        blockedUsersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        blockedUsersList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = blockedUsersList.locationToIndex(e.getPoint());

                // If an item is clicked (not clicking outside the list)
                if (index >= 0) {
                    String clickedItem = (String) blockedUsersList.getModel().getElementAt(index);
                    // Show a message when the item is clicked
                    System.out.println("You clicked: " + clickedItem);
                    new BlockedOptionsWindow(friendship, activeUserId, new UserService().getUserByUsername(clickedItem));
                }
            }
        });

        this.friendship = friendship;
        this.activeUserId = activeUserId;
        add(panel1);
        blockedUsersList.setListData(getListContents().toArray());
        setVisible(true);
    }

    private ArrayList<String> getListContents() {
        ArrayList<String> userIds = this.friendship.getUserBlocked(activeUserId);
        UserService userService = new UserService();

        ArrayList<String> blocks = new ArrayList<>();

        for (String userId : userIds) {
            blocks.add(userService.getUserById(userId).getUsername());
        }
        return blocks;
    }
}
