package connecthub.frontend;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Profile extends javax.swing.JFrame {

    ImageIcon profilePhoto;
    ImageIcon coverPhoto;
    ImageIcon scaledProfilePhoto;
    ImageIcon scaledCoverPhoto;

    public Profile() {
        initComponents();
        //get profile photo, cover photo, bio from database
        try {
            profilePhoto = new ImageIcon(getClass().getResource("prof.jpg"));
            coverPhoto = new ImageIcon(getClass().getResource("cov.jpg"));
            Image image1 = profilePhoto.getImage();
            Image image2 = coverPhoto.getImage();
            Image scaledImage1 = image1.getScaledInstance(125, 125, Image.SCALE_SMOOTH); // Width and height in pixels
            Image scaledImage2 = image2.getScaledInstance(600, 150, Image.SCALE_SMOOTH); // Width and height in pixels
            scaledProfilePhoto = new ImageIcon(scaledImage1);
            scaledCoverPhoto = new ImageIcon(scaledImage2);
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
        jLabel2 = new javax.swing.JLabel();
        changePassword = new javax.swing.JToggleButton();
        viewPosts = new javax.swing.JToggleButton();
        viewFriends = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

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
        bio.setText("One day I'll be something you've never expected!");
        bio.setToolTipText("Click to change bio.");
        bio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bioMouseClicked(evt);
            }
        });

        jLabel2.setText("Bio");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(coverPhotoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(profilePhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(237, 237, 237))))
            .addGroup(layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(changePassword)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(viewPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                .addComponent(viewFriends, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(coverPhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profilePhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bio)
                .addGap(18, 18, 18)
                .addComponent(changePassword)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewPosts)
                    .addComponent(viewFriends))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void coverPhotoLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coverPhotoLabelMouseClicked
        try {
            Image image = getImageFromFile();
            if (image != null)
            {
                Image scaledImage = image.getScaledInstance(600, 150, Image.SCALE_SMOOTH); // Width and height in pixels
                scaledCoverPhoto = new ImageIcon(scaledImage);
                coverPhotoLabel.setIcon(scaledCoverPhoto);
                JOptionPane.showMessageDialog(null, "Cover photo has changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                //save path to database
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading image file!");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_coverPhotoLabelMouseClicked

    private void profilePhotoLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePhotoLabelMouseClicked
        try {
            Image image = getImageFromFile();
            if (image != null)
            {
                Image scaledImage = image.getScaledInstance(125, 125, Image.SCALE_SMOOTH); // Width and height in pixels
                scaledCoverPhoto = new ImageIcon(scaledImage);
                profilePhotoLabel.setIcon(scaledCoverPhoto);
                JOptionPane.showMessageDialog(null, "Profile photo has changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                //save path to database
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading image file!");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_profilePhotoLabelMouseClicked

    private void bioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bioMouseClicked
        String text = JOptionPane.showInputDialog(null, "Enter bio");
        if (text != null) {
            bio.setText(text);
            JOptionPane.showMessageDialog(null, "Bio has changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            //save text to database
        }
    }//GEN-LAST:event_bioMouseClicked

    private void changePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordActionPerformed
        String password = JOptionPane.showInputDialog(null, "Enter current password");
        // hash it
        // get hashed password from database
        // compare both
        // if equal:
        String newPassword = JOptionPane.showInputDialog(null, "Enter new password");
        // hash it
        // save new hashed password to database
        // else:
        JOptionPane.showMessageDialog(null, "Wrong password!");
    }//GEN-LAST:event_changePasswordActionPerformed

    private void viewPostsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPostsActionPerformed
        //fetch posts
        //new posts JDialog
    }//GEN-LAST:event_viewPostsActionPerformed

    private void viewFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewFriendsActionPerformed
        //fetch friends
        //new posts JDialog
    }//GEN-LAST:event_viewFriendsActionPerformed

    private Image getImageFromFile() throws IOException {
        JFileChooser fileChooser = new JFileChooser(new File("C:\\Users\\Asser Hanafy\\Desktop\\FInal\\src\\connecthub\\Images"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Upload Image");
        int r = fileChooser.showOpenDialog(null);
        Image image = null;
        if (r == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String selectedImagePath = selectedFile.getAbsolutePath().toLowerCase();
            if (!selectedImagePath.endsWith(".png") && !selectedImagePath.endsWith(".jpg") && !selectedImagePath.endsWith(".jpeg")) {
                JOptionPane.showMessageDialog(null, "Error: Please select a valid image file (PNG, JPG, JPEG)!", "Invalid File", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            image = ImageIO.read(selectedFile);
        }
        return image;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Profile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bio;
    private javax.swing.JToggleButton changePassword;
    private javax.swing.JLabel coverPhotoLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel profilePhotoLabel;
    private javax.swing.JToggleButton viewFriends;
    private javax.swing.JToggleButton viewPosts;
    // End of variables declaration//GEN-END:variables
}
