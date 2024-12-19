package connecthub.frontend;

import static connecthub.backend.constants.FilePath.DEFAULT_COVER_PHOTO;
import static connecthub.backend.constants.FilePath.DEFAULT_PROFILE_PHOTO;
import connecthub.frontend.homepage.Homepage;
import connecthub.backend.database.UserDatabase;
import connecthub.backend.models.Post;
import connecthub.backend.models.User;
import connecthub.backend.profile.FetchFriends;
import connecthub.backend.profile.FetchPosts;
import connecthub.backend.profile.UpdateBio;
import connecthub.backend.profile.UpdateCoverPhoto;
import connecthub.backend.profile.UpdatePassword;
import connecthub.backend.profile.UpdateProfilePhoto;
import connecthub.backend.services.UserService;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Profile extends javax.swing.JFrame {

    private User user;
    private UserDatabase userDatabase;
    private ImageIcon profilePhoto;
    private ImageIcon coverPhoto;
    private ImageIcon scaledProfilePhoto;
    private ImageIcon scaledCoverPhoto;

    public Profile(User user) {
        initComponents();
        try {
            this.user = user;
            userDatabase = UserDatabase.getInstance();
            bio.setText(user.getBio());
            profilePhoto = new ImageIcon(user.getProfilePhoto());
            coverPhoto = new ImageIcon(user.getCoverPhoto());
            Image image1 = profilePhoto.getImage();
            Image image2 = coverPhoto.getImage();
            image1 = image1.getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Width and height in pixels
            image2 = image2.getScaledInstance(600, 200, Image.SCALE_SMOOTH); // Width and height in pixels
            scaledProfilePhoto = new ImageIcon(image1);
            scaledCoverPhoto = new ImageIcon(image2);
            profilePhotoLabel.setIcon(scaledProfilePhoto);
            coverPhotoLabel.setIcon(scaledCoverPhoto);
        } catch (Exception e) {
            System.out.println("Image cannot be found!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        coverPhotoLabel = new javax.swing.JLabel();
        profilePhotoLabel = new javax.swing.JLabel();
        bio = new javax.swing.JLabel();
        bioTitle = new javax.swing.JLabel();
        changePassword = new javax.swing.JToggleButton();
        viewPosts = new javax.swing.JToggleButton();
        viewFriends = new javax.swing.JToggleButton();
        removeCoverPhoto = new javax.swing.JToggleButton();
        removeProfilePhoto = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Profile");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        coverPhotoLabel.setToolTipText("Click to change cover photo.");
        coverPhotoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                coverPhotoLabelMouseClicked(evt);
            }
        });

        profilePhotoLabel.setToolTipText("Click to change profile photo.");
        profilePhotoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilePhotoLabelMouseClicked(evt);
            }
        });

        bio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bio.setText("Enter bio");
        bio.setToolTipText("Click to change bio.");
        bio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bioMouseClicked(evt);
            }
        });

        bioTitle.setText("Bio");

        changePassword.setText("Change Password");
        changePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordActionPerformed(evt);
            }
        });

        viewPosts.setText("View Posts");
        viewPosts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPostsActionPerformed(evt);
            }
        });

        viewFriends.setText("View Friends");
        viewFriends.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewFriendsActionPerformed(evt);
            }
        });

        removeCoverPhoto.setText("Remove Cover Photo");
        removeCoverPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeCoverPhotoActionPerformed(evt);
            }
        });

        removeProfilePhoto.setText("Remove Profile Photo");
        removeProfilePhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeProfilePhotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bioTitle)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(removeCoverPhoto)
                .addGap(102, 102, 102)
                .addComponent(changePassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(removeProfilePhoto)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(viewPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                .addComponent(viewFriends, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(profilePhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(coverPhotoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(coverPhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(profilePhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(bioTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bio)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changePassword)
                    .addComponent(removeCoverPhoto)
                    .addComponent(removeProfilePhoto))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewPosts)
                    .addComponent(viewFriends))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void coverPhotoLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coverPhotoLabelMouseClicked
        try {
            UpdateCoverPhoto updateCoverPhoto = new UpdateCoverPhoto(user, userDatabase);
            coverPhoto = updateCoverPhoto.update();
            if (coverPhoto != null) {
                coverPhotoLabel.setIcon(coverPhoto);
                JOptionPane.showMessageDialog(null, "Cover photo has changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading image file!");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_coverPhotoLabelMouseClicked

    private void profilePhotoLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePhotoLabelMouseClicked
        try {
            UpdateProfilePhoto updateProfilePhoto = new UpdateProfilePhoto(user, userDatabase);
            profilePhoto = updateProfilePhoto.update();
            if (profilePhoto != null) {
                profilePhotoLabel.setIcon(profilePhoto);
                JOptionPane.showMessageDialog(null, "Profile photo has changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading image file!");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_profilePhotoLabelMouseClicked

    private void bioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bioMouseClicked
        UpdateBio updateBio = new UpdateBio(user, userDatabase);
        String text = updateBio.update();
        if (text != null) {
            bio.setText(text);
            JOptionPane.showMessageDialog(null, "Bio has changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_bioMouseClicked

    private void changePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordActionPerformed
        UpdatePassword updatePassword = new UpdatePassword(user);
        updatePassword.update();
    }//GEN-LAST:event_changePasswordActionPerformed

    private void viewPostsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPostsActionPerformed
        FetchPosts fetch = new FetchPosts(user.getUserId());
        List<Post> posts = fetch.fetch();
        if (posts != null && !posts.isEmpty())
            new ViewPosts(this, true, posts, user.getUsername()).setVisible(true);
    }//GEN-LAST:event_viewPostsActionPerformed

    private void viewFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewFriendsActionPerformed
        try {
            FetchFriends fetch = new FetchFriends(user);
            List<String> friendsIds = fetch.fetch();
            if (friendsIds != null && !friendsIds.isEmpty()) {
                List<User> friends = new ArrayList<>();
                UserService userService = UserService.getInstance();
                for (String friendId : friendsIds) {
                    User friend = userService.getUserById(friendId);
                    friends.add(friend);
                }
                new ViewFriends(this, true, friends).setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_viewFriendsActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
            Homepage.getInstance(user).setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void removeCoverPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeCoverPhotoActionPerformed
        if (!user.getCoverPhoto().equals(DEFAULT_COVER_PHOTO)) {
            File oldImageFile = new File(user.getCoverPhoto());
            if (oldImageFile.exists()) {
                oldImageFile.delete();
            }
            coverPhoto = new ImageIcon(DEFAULT_COVER_PHOTO);
            Image image = coverPhoto.getImage();
            image = image.getScaledInstance(600, 200, Image.SCALE_SMOOTH); // Width and height in pixels
            scaledCoverPhoto = new ImageIcon(image);
            coverPhotoLabel.setIcon(scaledCoverPhoto);
            JOptionPane.showMessageDialog(null, "Cover photo has been removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            user.setCoverPhoto(DEFAULT_COVER_PHOTO);
            userDatabase.saveUser(user);
        }
    }//GEN-LAST:event_removeCoverPhotoActionPerformed

    private void removeProfilePhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeProfilePhotoActionPerformed
        if (!user.getProfilePhoto().equals(DEFAULT_PROFILE_PHOTO)) {
            File oldImageFile = new File(user.getProfilePhoto());
            if (oldImageFile.exists()) {
                oldImageFile.delete();
            }
            profilePhoto = new ImageIcon(DEFAULT_PROFILE_PHOTO);
            Image image = profilePhoto.getImage();
            image = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Width and height in pixels
            scaledProfilePhoto = new ImageIcon(image);
            profilePhotoLabel.setIcon(scaledProfilePhoto);
            JOptionPane.showMessageDialog(null, "Profile photo has been removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            user.setProfilePhoto(DEFAULT_PROFILE_PHOTO);
            userDatabase.saveUser(user);
        }
    }//GEN-LAST:event_removeProfilePhotoActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Profile(new User("userID", "email", "asser", null, "online", "hashedpass", "salt")).setVisible(true);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeySpecException ex) {
                    Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bio;
    private javax.swing.JLabel bioTitle;
    private javax.swing.JToggleButton changePassword;
    private javax.swing.JLabel coverPhotoLabel;
    private javax.swing.JLabel profilePhotoLabel;
    private javax.swing.JToggleButton removeCoverPhoto;
    private javax.swing.JToggleButton removeProfilePhoto;
    private javax.swing.JToggleButton viewFriends;
    private javax.swing.JToggleButton viewPosts;
    // End of variables declaration//GEN-END:variables
}
