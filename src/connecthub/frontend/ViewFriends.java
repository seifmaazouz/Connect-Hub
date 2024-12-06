package connecthub.frontend;

import connecthub.backend.models.User;
import java.util.ArrayList;
import java.util.List;

public class ViewFriends extends javax.swing.JDialog {
    
    List<User> friends;
    private static int friendIndex;
    
    public ViewFriends(java.awt.Frame parent, boolean modal, List<User> friends) {
        super(parent, modal);
        initComponents();
        this.friends = friends;
        friendName.setText(friends.getLast().getUsername());
        friendIndex = 1;
        friendNumber.setText(Integer.toString(friendIndex));
        totalFriendsNumber.setText(Integer.toString(friends.size()));
        changeFriend(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nextFriend = new javax.swing.JToggleButton();
        friendName = new javax.swing.JLabel();
        friendStatus = new javax.swing.JLabel();
        postText = new javax.swing.JLabel();
        friendNumber = new javax.swing.JLabel();
        separator = new javax.swing.JLabel();
        totalFriendsNumber = new javax.swing.JLabel();
        previousFriend = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nextFriend.setText("Next Friend");
        nextFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextFriendActionPerformed(evt);
            }
        });

        friendName.setText("Friend Name");
        friendName.setToolTipText("");

        friendStatus.setText("Status");

        friendNumber.setText("##");

        separator.setText("/");

        totalFriendsNumber.setText("##");

        previousFriend.setText("Previous Friend");
        previousFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousFriendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(postText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(previousFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(friendNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(separator)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalFriendsNumber)
                        .addGap(59, 59, 59)
                        .addComponent(nextFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(friendName)
                            .addComponent(friendStatus))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(friendStatus)
                .addGap(7, 7, 7)
                .addComponent(postText)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previousFriend)
                    .addComponent(nextFriend)
                    .addComponent(friendNumber)
                    .addComponent(separator)
                    .addComponent(totalFriendsNumber))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void changeFriend(int i)
    {
        friendIndex += i;
        friendName.setText(friends.get(friends.size() - friendIndex).getUsername());
        friendStatus.setText(friends.get(friends.size() - friendIndex).getStatus());
    }
    
    private void nextFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextFriendActionPerformed
        int i = Integer.parseInt(friendNumber.getText());
        int n = Integer.parseInt(totalFriendsNumber.getText());
        if (i < n) {
            i++;
            friendNumber.setText(Integer.toString(i));
            changeFriend(1);
        }
    }//GEN-LAST:event_nextFriendActionPerformed

    private void previousFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousFriendActionPerformed
        int i = Integer.parseInt(friendNumber.getText());
        if (i > 1) {
            i--;
            friendNumber.setText(Integer.toString(i));
            changeFriend(-1);
        }
    }//GEN-LAST:event_previousFriendActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewFriends dialog = new ViewFriends(new javax.swing.JFrame(), true, new ArrayList<>());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel friendName;
    private javax.swing.JLabel friendNumber;
    private javax.swing.JLabel friendStatus;
    private javax.swing.JToggleButton nextFriend;
    private javax.swing.JLabel postText;
    private javax.swing.JToggleButton previousFriend;
    private javax.swing.JLabel separator;
    private javax.swing.JLabel totalFriendsNumber;
    // End of variables declaration//GEN-END:variables
}
