
package connecthub.frontend.FriendshipUI;

import connecthub.backend.models.Friendship;
import connecthub.backend.services.UserService;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class SentRequestsPanel extends JPanel {
    private JList sentRequestsList;
    private JPanel panel1;
    private Friendship friendship;
    private String activeUserId;

    public SentRequestsPanel(Friendship friendship, String activeUserId) {
        sentRequestsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        sentRequestsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = sentRequestsList.locationToIndex(e.getPoint());

                // If an item is clicked (not clicking outside the list)
                if (index >= 0) {
                    String clickedItem = (String) sentRequestsList.getModel().getElementAt(index);
                    // Show a message when the item is clicked
                    System.out.println("You clicked: " + clickedItem);
                    try {
                        new SentRequestsOptionsWindow(friendship, activeUserId, new UserService().getUserByUsername(clickedItem));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        this.friendship = friendship;
        this.activeUserId = activeUserId;
        add(panel1);
        sentRequestsList.setListData(getListContents().toArray());
        setVisible(true);
    }

    private ArrayList<String> getListContents() {
        ArrayList<String> userIds = this.friendship.getUserSentRequests(activeUserId);
        UserService userService = new UserService();

        ArrayList<String> sentRequests = new ArrayList<>();

        for (String userId : userIds) {
            sentRequests.add(userService.getUserById(userId).getUsername());
        }
        return sentRequests;
    }
}
