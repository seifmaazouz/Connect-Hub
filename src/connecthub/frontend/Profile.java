package connecthub.frontend;

import connecthub.frontend.newsfeed.NewsFeed;
import connecthub.backend.database.UserDatabase;
import connecthub.backend.models.Post;
import connecthub.backend.models.User;
import connecthub.backend.profile.FetchPosts;
import connecthub.backend.profile.UpdateBio;
import connecthub.backend.profile.UpdateCoverPhoto;
import connecthub.backend.profile.UpdatePassword;
import connecthub.backend.profile.UpdateProfilePhoto;
import java.awt.Image;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
        //get profile photo, cover photo, bio from database
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
        if(posts != null && !posts.isEmpty())
            new ViewPosts(this, true, posts, user.getUsername()).setVisible(true);
    }//GEN-LAST:event_viewPostsActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        new NewsFeed(user).setVisible(true);
    }//GEN-LAST:event_formWindowClosed

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
    private javax.swing.JToggleButton viewFriends;
    private javax.swing.JToggleButton viewPosts;
    // End of variables declaration//GEN-END:variables
}
