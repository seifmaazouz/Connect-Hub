package connecthub.frontend;

import connecthub.backend.models.Friendship;
import connecthub.backend.models.Notification;
import connecthub.backend.models.Post;
import connecthub.backend.models.User;
import connecthub.backend.services.FriendshipService;
import connecthub.backend.services.PostService;
import connecthub.backend.services.UserService;
import connecthub.backend.utils.factories.ServiceFactory;
import connecthub.frontend.chatUI.chatWindow;
import connecthub.frontend.homepage.ViewPostComments;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewNotifications extends javax.swing.JDialog {

    private User user;
    private UserService userService;
    private List<Notification> notifications;
    private static int notificationIndex;
    private int size;

    public ViewNotifications(java.awt.Frame parent, boolean modal, User user) {
        super(parent, modal);
        initComponents();
        this.user = user;
        this.userService = UserService.getInstance();
        this.notifications = this.user.getNotifications();
        size = notifications.size();
        notificationIndex = 1;
        notificationNumber.setText(Integer.toString(notificationIndex));
        totalNotificationsNumber.setText(Integer.toString(size));
        changeNotification(0);
    }

    private void changeNotification(int i) {
        notificationIndex += i;
        Notification notification = notifications.get(size - notificationIndex);
        Notification.Type type = notification.getType();
        notificationText.setText(notification.getMessage());
        notificationTime.setText(notification.getTimestamp().toString());
        switch (type) {
            case FRIEND_REQUEST:
                notificationLabel.setText("Friend Request");
                yes.setText("Accept");
                no.setText("Decline");
                break;
            case GROUP_ACTIVITY:
                notificationLabel.setText("Group Activity");
                yes.setText("View");
                no.setText("Ignore");
                break;
            case NEW_POST:
                notificationLabel.setText("New Post");
                yes.setText("View");
                no.setText("Ignore");
                break;
            case MESSAGE:
                notificationLabel.setText("Message");
                yes.setText("View");
                no.setText("Ignore");
                break;
            case COMMENT:
                notificationLabel.setText("Comment");
                yes.setText("View");
                no.setText("Ignore");
                break;
            case LIKE:
                notificationLabel.setText("Like");
                yes.setText("View");
                no.setText("Ignore");
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        notificationLabel = new javax.swing.JLabel();
        notificationText = new javax.swing.JLabel();
        notificationNumber = new javax.swing.JLabel();
        separator = new javax.swing.JLabel();
        totalNotificationsNumber = new javax.swing.JLabel();
        previousNotification = new javax.swing.JToggleButton();
        nextNotification = new javax.swing.JToggleButton();
        yes = new javax.swing.JToggleButton();
        no = new javax.swing.JToggleButton();
        notificationTime = new javax.swing.JLabel();
        clearNotifications = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Notifiactions");
        setResizable(false);

        notificationLabel.setText("Notification Label");
        notificationLabel.setToolTipText("");

        notificationText.setText("Notification Text");

        notificationNumber.setText("##");

        separator.setText("/");

        totalNotificationsNumber.setText("##");

        previousNotification.setText("Previous Notification");
        previousNotification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousNotificationActionPerformed(evt);
            }
        });

        nextNotification.setText("Next Notification");
        nextNotification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextNotificationActionPerformed(evt);
            }
        });

        yes.setText("Yes");
        yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesActionPerformed(evt);
            }
        });

        no.setText("No");
        no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noActionPerformed(evt);
            }
        });

        notificationTime.setText("Notification Time");

        clearNotifications.setText("Clear All");
        clearNotifications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearNotificationsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(previousNotification)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(notificationNumber))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(yes, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(separator)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(totalNotificationsNumber)
                                .addGap(32, 32, 32)
                                .addComponent(nextNotification))
                            .addComponent(no, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(notificationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearNotifications))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(notificationText)
                            .addComponent(notificationTime))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(notificationLabel)
                    .addComponent(clearNotifications))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(notificationText)
                .addGap(12, 12, 12)
                .addComponent(notificationTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yes)
                    .addComponent(no))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previousNotification)
                    .addComponent(nextNotification)
                    .addComponent(notificationNumber)
                    .addComponent(separator)
                    .addComponent(totalNotificationsNumber))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void previousNotificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousNotificationActionPerformed
        int i = Integer.parseInt(notificationNumber.getText());
        if (i > 1) {
            i--;
            notificationNumber.setText(Integer.toString(i));
            changeNotification(-1);
        }
    }//GEN-LAST:event_previousNotificationActionPerformed

    private void nextNotificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextNotificationActionPerformed
        int i = Integer.parseInt(notificationNumber.getText());
        int n = Integer.parseInt(totalNotificationsNumber.getText());
        if (i < n) {
            i++;
            notificationNumber.setText(Integer.toString(i));
            changeNotification(1);
        }
    }//GEN-LAST:event_nextNotificationActionPerformed

    private void yesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesActionPerformed
        try {
            // TODO add your handling code here:
            Notification notification = notifications.get(size - notificationIndex);
            Notification.Type type = notification.getType();
            PostService postService = ServiceFactory.createPostService();
            postService.refreshContents();
            String postId = notification.getContentId();
            List<Post> posts = null;
            User author = null;
            Post post = null;
            if(postId != null) {
                post = postService.getContentFromId(postId);
                posts = new ArrayList<>();
                posts.add(post);
                author = userService.getUserById(post.getAuthorId());
            }
            switch (type) {
                case FRIEND_REQUEST:
                    FriendshipService friendshipService = new FriendshipService();
                    Friendship friendship = friendshipService.loadFriendship();
                    friendship.acceptRequest(this.user.getUserId(), notification.getSenderId());
                    friendshipService.saveFriendship(friendship);
                    System.out.println("Friend request accepted");
                    break;
                case GROUP_ACTIVITY:
                    //view group
                    //wait for nader
                    break;
                case NEW_POST:
                    //view post
                    new ViewPosts(null, true, posts, author.getUsername()).setVisible(true);
                    break;
                case MESSAGE:
                    User friend = userService.getUserById(notification.getSenderId());
                    new chatWindow(user, friend).setVisible(true);
                    break;
                case COMMENT:
                    //view comments
                    new ViewPosts(null, true, posts, author.getUsername()).setVisible(true);
                    new ViewPostComments(null, true, post.getComments()).setVisible(true);
                    break;
                case LIKE:
                    //view my post
                    new ViewPosts(null, true, posts, author.getUsername()).setVisible(true);
                    JOptionPane.showMessageDialog(null, post.getLikes(), "Number of Likes", JOptionPane.PLAIN_MESSAGE);
                    break;
            }
            user.deleteNotification(notification);
            userService.updateUser(user.getUserId(), user);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(ViewNotifications.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_yesActionPerformed

    private void noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noActionPerformed
        try {
            Notification notification = notifications.get(size - notificationIndex);
            Notification.Type type = notification.getType();
            switch (type) {
                case FRIEND_REQUEST:
                    FriendshipService friendshipService = new FriendshipService();
                    Friendship friendship = friendshipService.loadFriendship();
                    friendship.cancelRequest(notification.getSenderId(), user.getUserId());
                    friendshipService.saveFriendship(friendship);
                    System.out.println("Friend request declined");
                    break;
                default:
                    break;
            }
            user.deleteNotification(notification);
            userService.updateUser(user.getUserId(), user);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(ViewNotifications.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_noActionPerformed

    private void clearNotificationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearNotificationsActionPerformed
        user.clearNotifications();
        userService.updateUser(user.getUserId(), user);
        this.dispose();
    }//GEN-LAST:event_clearNotificationsActionPerformed

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ViewNotifications dialog = new ViewNotifications(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton clearNotifications;
    private javax.swing.JToggleButton nextNotification;
    private javax.swing.JToggleButton no;
    private javax.swing.JLabel notificationLabel;
    private javax.swing.JLabel notificationNumber;
    private javax.swing.JLabel notificationText;
    private javax.swing.JLabel notificationTime;
    private javax.swing.JToggleButton previousNotification;
    private javax.swing.JLabel separator;
    private javax.swing.JLabel totalNotificationsNumber;
    private javax.swing.JToggleButton yes;
    // End of variables declaration//GEN-END:variables
}
