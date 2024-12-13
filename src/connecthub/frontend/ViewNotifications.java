package connecthub.frontend;

import connecthub.backend.models.Friendship;
import connecthub.backend.models.Notification;
import static connecthub.backend.models.Notification.Type.FRIEND_REQUEST;
import static connecthub.backend.models.Notification.Type.GROUP_ACTIVITY;
import static connecthub.backend.models.Notification.Type.NEW_POST;
import connecthub.backend.models.User;
import connecthub.backend.services.FriendshipService;
import connecthub.backend.services.NotificationService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewNotifications extends javax.swing.JDialog {
    
    private User user;
    private NotificationService notificationService;
    private static int notificationIndex;
    private List<Notification> notifications;
    private int size;

    public ViewNotifications(java.awt.Frame parent, boolean modal, User user, NotificationService notificationService) {
        super(parent, modal);
        initComponents();
        this.user = user;
        this.notificationService = notificationService;
        List<Notification> notifications = notificationService.getNotifications();
        size = notifications.size();
        notificationIndex = 1;
        notificationNumber.setText(Integer.toString(notificationIndex));
        totalNotificationsNumber.setText(Integer.toString(size));
        changeNotification(0);
    }

    private void changeNotification(int i) {
        notificationIndex += i;
        Notification.Type type = notifications.get(size - notificationIndex).getType();
        notificationText.setText(notifications.get(size - notificationIndex).getMessage());
        notificationTime.setText(notifications.get(size - notificationIndex).getTimestamp().toString());
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

        clearNotifications.setText("Clear");
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
        // TODO add your handling code here:
        Notification.Type type = notifications.get(size - notificationIndex).getType();
        switch (type) {
            case FRIEND_REQUEST:
                try {
                    Friendship friendship = new FriendshipService().loadFriendship();
                    friendship.acceptRequest(user.getUserId(), notifications.get(size - notificationIndex).getSenderId());
                } catch (IOException ex) {
                    Logger.getLogger(ViewNotifications.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case GROUP_ACTIVITY:
                //view group
                break;
            case NEW_POST:
                //view post
                break;
        }
        notificationService.removeNotification(notifications.get(size - notificationIndex));
    }//GEN-LAST:event_yesActionPerformed

    private void noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noActionPerformed
        Notification.Type type = notifications.get(size - notificationIndex).getType();
        switch (type) {
            case FRIEND_REQUEST:
                try {
                    Friendship friendship = new FriendshipService().loadFriendship();
                    friendship.cancelRequest(user.getUserId(), notifications.get(size - notificationIndex).getSenderId());
                } catch (IOException ex) {
                    Logger.getLogger(ViewNotifications.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case GROUP_ACTIVITY:
                //ignore
                break;
            case NEW_POST:
                //ignore
                break;
        }
        notificationService.removeNotification(notifications.get(size - notificationIndex));
    }//GEN-LAST:event_noActionPerformed

    private void clearNotificationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearNotificationsActionPerformed
        notificationService.clearNotifications();
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