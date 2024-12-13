package connecthub.frontend.homepage;

import connecthub.backend.models.Friendship;
import connecthub.backend.models.Post;
import connecthub.backend.models.Story;
import connecthub.backend.models.User;
import connecthub.backend.services.FriendshipService;
import connecthub.backend.services.StoryService;
import connecthub.backend.services.PostService;

import java.awt.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import connecthub.backend.services.UserService;
import connecthub.backend.utils.factories.ServiceFactory;
import connecthub.frontend.ContentCreator;
import connecthub.frontend.Login;
import connecthub.frontend.Profile;
import connecthub.frontend.ViewStories;
import java.util.ArrayList;
import java.util.List;

import static connecthub.backend.constants.FilePath.FRIENDS_FILE_PATH;

public class NewsFeed extends javax.swing.JFrame {
    private final User user;
    private final UserService userService;
    private final PostService postService;
    private final StoryService storyService;
    private final FriendshipService friendshipService;
    private Friendship friendship;
    private boolean profileMode = false, exitMode = true;


    public NewsFeed(User user) {
        initComponents();
        
        this.user = user;
        friendshipService = new FriendshipService();
        try {
            friendship = friendshipService.loadFriendship();
        } catch (IOException e) {
            System.out.println("No Friends.");
        }
        userService = new UserService();
        postService = ServiceFactory.createPostService();
        storyService = ServiceFactory.createStoryService();
        
        // set profile photo
        Image profileImage = new ImageIcon(user.getProfilePhoto()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ProfilePhoto profilePhotoPanel = new ProfilePhoto(profileImage, 100, 2);
        profilePhotoLabel.setLayout(new BorderLayout());
        profilePhotoLabel.add(profilePhotoPanel, BorderLayout.CENTER);

        // set username
        String userName = user.getUsername();
        lblUsername.setText(userName);
        
        // display posts
        List<String> friends = friendship.getUserFriends(user.getUserId());
        List<Post> posts = new ArrayList<>();
        for(String friend : friends) {
            posts.addAll(postService.getListOfUserContents(friend));
        }
        displayPosts(posts);
    }

    private void refresh() {
        postService.refreshContents();
        userService.refreshContents();
        storyService.refreshContents();
        try {
            friendship = friendshipService.loadFriendship();
            friendshipService.saveFriendship(friendship);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        displayPosts(postService.getListOfContents());
        jDisplayPosts.getVerticalScrollBar().setValue(0); // reset to top
        revalidate();
        repaint();
    }
    
    private void displayPosts(List<Post> posts) {
        PostsPanel postsPanel = new PostsPanel(posts, userService);
        jDisplayPosts.setViewportView(postsPanel);
        jDisplayPosts.getVerticalScrollBar().setUnitIncrement(8); // Make scroll bar smoother
        postsPanel.revalidate();
        postsPanel.repaint();
        jDisplayPosts.revalidate();
        jDisplayPosts.repaint();
        // reset vertical scroll to top
        SwingUtilities.invokeLater(() -> {
            jDisplayPosts.getVerticalScrollBar().setValue(0);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        btnCreateContent = new javax.swing.JButton();
        profilePhotoLabel = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        btnViewStories = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jDisplayPosts = new javax.swing.JScrollPane();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Newsfeed ");
        setMinimumSize(new java.awt.Dimension(1007, 569));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setPreferredSize(new java.awt.Dimension(1007, 569));

        btnCreateContent.setBackground(new java.awt.Color(255, 204, 102));
        btnCreateContent.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCreateContent.setForeground(new java.awt.Color(0, 0, 0));
        btnCreateContent.setText("Create Content");
        btnCreateContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateContentActionPerformed(evt);
            }
        });

        profilePhotoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profilePhotoLabel.setToolTipText("Click to view Profile");
        profilePhotoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilePhotoLabelMouseClicked(evt);
            }
        });

        lblUsername.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setLabelFor(profilePhotoLabel);
        lblUsername.setText("Username");

        btnViewStories.setBackground(new java.awt.Color(255, 204, 102));
        btnViewStories.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnViewStories.setForeground(new java.awt.Color(0, 0, 0));
        btnViewStories.setText("View Friends Stories");
        btnViewStories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewStoriesActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(102, 0, 0));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jDisplayPosts.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                            .addGap(177, 177, 177)
                            .addComponent(jDisplayPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, backgroundLayout.createSequentialGroup()
                            .addGap(182, 182, 182)
                            .addComponent(btnCreateContent, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(169, 169, 169)
                            .addComponent(btnViewStories)))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRefresh)))
                .addGap(12, 316, Short.MAX_VALUE))
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(backgroundLayout.createSequentialGroup()
                    .addContainerGap(901, Short.MAX_VALUE)
                    .addComponent(profilePhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRefresh)
                .addGap(11, 11, 11)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateContent, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewStories, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout)
                .addGap(28, 28, 28)
                .addComponent(jDisplayPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(backgroundLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(profilePhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(695, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if(!profileMode) {
            userService.logout(user.getUserId());
            if(exitMode)
                System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        exitMode = false;
        profileMode = false;
        new Login().createAndShowGUI();
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnViewStoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewStoriesActionPerformed
//        if(friends == null) {
//            JOptionPane.showMessageDialog(null, "No friend Stories!", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        List<Story> stories = new ArrayList<>();
//        for(User friend : friends) {
//            stories.addAll(storyService.getListOfUserContents(friend.getUserId()));
//        }
//        if(!stories.isEmpty()) {
//            ViewStories viewStories = new ViewStories(this, true, stories);
//            viewStories.setVisible(true);
//        }
    }//GEN-LAST:event_btnViewStoriesActionPerformed

    private void profilePhotoLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePhotoLabelMouseClicked
        profileMode = true;
        exitMode = false;
        this.dispose();
        new Profile(user).setVisible(true);
    }//GEN-LAST:event_profilePhotoLabelMouseClicked

    private void btnCreateContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateContentActionPerformed
        ContentCreator contentCreator = new ContentCreator(this, true, user);
        contentCreator.setVisible(true);
    }//GEN-LAST:event_btnCreateContentActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        refresh();
    }//GEN-LAST:event_btnRefreshActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewsFeed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewsFeed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewsFeed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewsFeed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserService u = new UserService();
                    new NewsFeed(u.getUser("seif@gmail.com", "seif123")).setVisible(true);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(NewsFeed.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeySpecException ex) {
                    Logger.getLogger(NewsFeed.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnCreateContent;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnViewStories;
    private javax.swing.JScrollPane jDisplayPosts;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel profilePhotoLabel;
    // End of variables declaration//GEN-END:variables
}
