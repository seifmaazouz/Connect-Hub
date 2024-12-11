package connecthub.frontend;

import connecthub.backend.models.Friendship;
import connecthub.backend.models.Post;
import connecthub.backend.models.Story;
import connecthub.backend.models.User;
import connecthub.backend.services.StoryService;
import connecthub.backend.services.PostService;
import java.awt.Image;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import connecthub.backend.services.UserService;
import connecthub.backend.utils.factories.ServiceFactory;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NewsFeed extends javax.swing.JFrame {
    private final User user;
    private ImageIcon profilePhoto;
    private final List<User> friends = null;
    private UserService userService;
    private PostService postService;
    private StoryService storyService;

    
    public NewsFeed(User user) {
        initComponents();
        this.user = user;
        userService = new UserService();
        postService = ServiceFactory.createPostService();
        //Friendship friendship = user.getFriendship();
//        if(friendship == null)
//            friends = null;
//        else
//            friends = friendship.getFriends();
        profilePhoto = new ImageIcon(user.getProfilePhoto());
        Image image = profilePhoto.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Width and height in pixels
        profilePhoto = new ImageIcon(image);
        profilePhotoLabel.setIcon(profilePhoto);
        String userName = user.getUsername();
        lblUsername.setText(userName);
//        viewPostsPanel.setUserName(userName);
//        PostService postService = ServiceFactory.createPostService();
//        List<Post> posts = new ArrayList<>();
//        if(friends == null)
//            return;
//        for(User friend : friends) {
//            posts.addAll(postService.getListOfUserContents(friend.getUserId()));
//        }
//        if(!posts.isEmpty())
//            viewPostsPanel.setPosts(posts);

        List<Post> posts;
        posts = postService.getListOfContents();
        displayPosts(posts);
    }

    private void displayPosts(List<Post> posts) {
        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        postsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel

        for (int i = posts.size() - 1; i >= 0; i--) {
            Post post = posts.get(i);
            JPanel postPanel = new JPanel();
            postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
            postPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around each post
            postPanel.setBackground(new java.awt.Color(255, 255, 255)); // Set background color to white
            postPanel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT); // Center align the post panel

            User user = userService.getUserById(post.getAuthorId());
            JLabel authorLabel = new JLabel(user.getUsername());
            authorLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14)); // Set font for author label
            postPanel.add(authorLabel);

            JLabel timeStampLabel = new JLabel(post.getTimestamp().toString());
            timeStampLabel.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, 12)); // Set font for timestamp label
            postPanel.add(timeStampLabel);

            JLabel postTextLabel = new JLabel("<html>" + post.getContentData().getText() + "</html>");
            postTextLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14)); // Set font for post text label
            postPanel.add(postTextLabel);

            String imagePath = post.getContentData().getImagePath();
            if (imagePath != null) {
                Image image = new ImageIcon(imagePath).getImage();
                image = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(image);
                JLabel postImageLabel = new JLabel(icon);
                postPanel.add(postImageLabel);
            }

            postPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)), // Add border around each post
                    javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10) // Add padding inside the border
            ));

            postsPanel.add(postPanel);
            postsPanel.add(javax.swing.Box.createVerticalStrut(10)); // Add space between posts
        }

        jDisplayPosts.setViewportView(postsPanel);
        postsPanel.revalidate();
        postsPanel.repaint();
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

        profilePhotoLabel.setToolTipText("Click to view Profile");
        profilePhotoLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
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
        jDisplayPosts.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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
                .addGap(43, 43, 43)
                .addComponent(jDisplayPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 54, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        UserService userService = new UserService();
        userService.logout(user.getUserId());
        this.dispose();
        new Login();
    }//GEN-LAST:event_formWindowClosed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        UserService userService = new UserService();
        userService.logout(user.getUserId());
        this.dispose();
        new Login().createAndShowGUI();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnViewStoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewStoriesActionPerformed
        StoryService storyService = ServiceFactory.createStoryService();
        if(friends == null) {
            JOptionPane.showMessageDialog(null, "No friend Stories!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<Story> stories = new ArrayList<>();
        for(User friend : friends) {
            stories.addAll(storyService.getListOfUserContents(friend.getUserId()));
        }
        if(!stories.isEmpty()) {
            ViewStories viewStories = new ViewStories(this, true, stories);
            viewStories.setVisible(true);
        }
    }//GEN-LAST:event_btnViewStoriesActionPerformed

    private void profilePhotoLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePhotoLabelMouseClicked
        this.dispose();
        Profile profile = new Profile(user);
        profile.setVisible(true);
    }//GEN-LAST:event_profilePhotoLabelMouseClicked

    private void btnCreateContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateContentActionPerformed
        ContentCreator contentCreator = new ContentCreator(this, true, user);
        contentCreator.setVisible(true);
    }//GEN-LAST:event_btnCreateContentActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        postService.refreshContents();
        userService.refreshContents();
        storyService.refreshContents();
        displayPosts(postService.getListOfContents());
        revalidate();
        repaint();
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
                    new NewsFeed(u.getUser("seif@gmail.com", "noura123")).setVisible(true);
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
