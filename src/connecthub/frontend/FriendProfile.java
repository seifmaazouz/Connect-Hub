package connecthub.frontend;

import connecthub.frontend.homepage.Homepage;
import connecthub.backend.models.User;
import java.awt.Image;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class FriendProfile extends javax.swing.JFrame {

    private User user;
    private ImageIcon profilePhoto;
    private ImageIcon coverPhoto;
    private ImageIcon scaledProfilePhoto;
    private ImageIcon scaledCoverPhoto;

    public FriendProfile(User user) {
        initComponents();
        try {
            this.user = user;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                try {
                    formWindowClosed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        coverPhotoLabel.setToolTipText("Click to change cover photo.");

        profilePhotoLabel.setToolTipText("Click to change profile photo.");

        bio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bio.setText("Bio");
        bio.setToolTipText("Click to change bio.");
        bio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bioTitle.setText("Bio");

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
                .addGap(225, 225, 225)
                .addComponent(profilePhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
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
                .addGap(112, 112, 112))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) throws IOException {//GEN-FIRST:event_formWindowClosed
        new Homepage(user).setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FriendProfile(new User("userID", "email", "asser", null, "online", "hashedpass", "salt")).setVisible(true);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(FriendProfile.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeySpecException ex) {
                    Logger.getLogger(FriendProfile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bio;
    private javax.swing.JLabel bioTitle;
    private javax.swing.JLabel coverPhotoLabel;
    private javax.swing.JLabel profilePhotoLabel;
    // End of variables declaration//GEN-END:variables
}
