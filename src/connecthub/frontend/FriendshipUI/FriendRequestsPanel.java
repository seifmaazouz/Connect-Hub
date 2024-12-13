package connecthub.frontend.FriendshipUI;

import connecthub.backend.models.Friendship;
import connecthub.backend.services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class FriendRequestsPanel extends JPanel {
    private JList friendRequestsList;
    private JPanel panel1;
    private Friendship friendship;
    private String activeUserId;

    public FriendRequestsPanel(Friendship friendship, String activeUserId) {
        friendRequestsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        friendRequestsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = friendRequestsList.locationToIndex(e.getPoint());

                // If an item is clicked (not clicking outside the list)
                if (index >= 0) {
                    String clickedItem = (String) friendRequestsList.getModel().getElementAt(index);
                    // Show a message when the item is clicked
                    System.out.println("You clicked: " + clickedItem);
                }
            }
        });

        this.friendship = friendship;
        this.activeUserId = activeUserId;
        add(panel1);
        friendRequestsList.setListData(getListContents().toArray());
        setVisible(true);
    }

    private ArrayList<String> getListContents() {
        ArrayList<String> userIds = this.friendship.getUserFriendRequests(activeUserId);
        UserService userService = UserService.getInstance();

        ArrayList<String> requests = new ArrayList<>();

        for (String userId : userIds) {
            requests.add(userService.getUserById(userId).getUsername());
        }
        return requests;
    }
}
