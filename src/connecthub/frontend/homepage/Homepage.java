package connecthub.frontend.homepage;

import connecthub.backend.models.Friendship;
import connecthub.backend.models.Post;
import connecthub.backend.models.Story;
import connecthub.backend.models.User;
import connecthub.backend.services.FriendshipService;
import connecthub.backend.services.PostService;
import connecthub.backend.services.StoryService;
import connecthub.backend.services.UserService;
import connecthub.backend.utils.factories.ServiceFactory;
import connecthub.frontend.ContentCreator;
import connecthub.frontend.FriendshipUI.FriendListPanel;
import connecthub.frontend.FriendshipUI.FriendshipManagementMainWindow;
import connecthub.frontend.Login;
import connecthub.frontend.Profile;
import connecthub.frontend.ViewStories;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class Homepage extends javax.swing.JFrame {
    private  NewsFeedPanel newsFeedPanel;
    private  FriendsPanel friendsPanel;
    private  ProfilePostsPanel profilePostsPanel;
    private final User user;
    private final UserService userService;
    private final PostService postService;
    private final StoryService storyService;
    private final FriendshipService friendshipService;
    private Friendship friendship;
    private List<String> friends;
    private boolean profileMode = false, exitMode = true;


    public Homepage(User user) throws IOException {
        initComponents();
        this.user = user;

        // create all services
        friendshipService = new FriendshipService();
        userService = UserService.getInstance();
        postService = ServiceFactory.createPostService();
        storyService = ServiceFactory.createStoryService();

        // load user friends
        try {
            friendship = friendshipService.loadFriendship();
        } catch (IOException e) {
            System.out.println("No Friends.");
        }

        // set profile photo
        Image profileImage = new ImageIcon(user.getProfilePhoto()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ProfilePhoto profilePhotoPanel = new ProfilePhoto(profileImage, 100, 2);
        profilePhotoLabel.setLayout(new BorderLayout());
        profilePhotoLabel.add(profilePhotoPanel, BorderLayout.CENTER);

        // set username
        String userName = user.getUsername();
        lblUsername.setText(userName);

        // get friends posts
        friends = friendship.getUserFriends(user.getUserId());
        List<Post> posts = new ArrayList<>();
        for(String friend : friends) {
            posts.addAll(postService.getListOfUserContents(friend));
        }

        customizeTabbedPane(posts);
        
        // initialize friends list
        try {
            friendship = friendshipService.loadFriendship();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JPanel friendsPanel = new FriendListPanel(friendship, user.getUserId());
        friendsPanel.setVisible(true);
        sideBarHolder.addTab("Friends", friendsPanel);
        friendsPanel.revalidate();
        friendsPanel.repaint();

        JPanel searchUsersPanel = new SearchUsersPanel(friendship, user.getUserId());
        searchUsersPanel.setVisible(true);
        sideBarHolder.addTab("Search Users", searchUsersPanel);
        searchUsersPanel.revalidate();
        searchUsersPanel.repaint();
    }

    private void customizeTabbedPane(List<Post> posts) {
        // setup tabbed pane
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 16));
        tabbedPane.setBackground(Color.LIGHT_GRAY);
        tabbedPane.setForeground(Color.DARK_GRAY);

        // add all tabs
        newsFeedPanel = new NewsFeedPanel(posts);
        friendsPanel = new FriendsPanel(user.getUserId());
        profilePostsPanel = new ProfilePostsPanel(user.getUserId());

        // label all tabs
        tabbedPane.addTab("NewsFeed", newsFeedPanel);
        tabbedPane.addTab("Friends", friendsPanel);
        tabbedPane.addTab("My Posts", profilePostsPanel);

        // set starting default at newsfeed
        tabbedPane.setSelectedIndex(0);

        // Customize tabbed pane to add space and increase tab width
        tabbedPane.setUI(new BasicTabbedPaneUI() {
            @Override
            protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
                return 200; // Set the width of each tab
            }

            @Override
            protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {
                Rectangle tabRect = rects[tabIndex];

                // Hover effect logic (check if mouse is within the tab area)
                Point mousePos = tabPane.getMousePosition();
                if (mousePos != null && tabRect.contains(mousePos)) {
                    g.setColor(Color.LIGHT_GRAY); // Hover background color
                } else {
                    g.setColor(Color.WHITE); // Normal background color
                }

                // Paint the tab with the hover effect applied
                g.fillRect(tabRect.x, tabRect.y, tabRect.width, tabRect.height);

                // Call the parent class's method to render text, icon, etc.
                super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
            }

            @Override
            protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
                return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) + 5; // Add vertical padding
            }

            @Override
            protected void installDefaults() {
                super.installDefaults();
                tabInsets = new Insets(10, 10, 10, 10); // Add padding around the tab text
            }
        });

        // Add a MouseMotionListener to trigger repaint on mouse hover
        tabbedPane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // Repaint the tabbed pane when the mouse is moved
                tabbedPane.repaint();
            }
        });
    }

    
    private void refresh() {
        postService.refreshContents();
        userService.refreshContents();
        storyService.refreshContents();
        storyService.deleteExpiredStories();
        profilePostsPanel.refresh();
        try {
            friendship = friendshipService.loadFriendship();
            friendshipService.saveFriendship(friendship);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // get refreshed friends posts
        List<String> friends = friendship.getUserFriends(user.getUserId());
        List<Post> posts = new ArrayList<>();
        for(String friend : friends) {
            posts.addAll(postService.getListOfUserContents(friend));
        }
        newsFeedPanel.refresh(posts);

        // refresh friends list
        try {
            friendship = friendshipService.loadFriendship();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sideBarHolder.removeAll();
        sideBarHolder.addTab("Friends", new FriendListPanel(friendship, user.getUserId()));
        sideBarHolder.revalidate();
        sideBarHolder.repaint();
        sideBarHolder.addTab("Search Users", new SearchUsersPanel(friendship, user.getUserId()));
        sideBarHolder.revalidate();
        sideBarHolder.repaint();
        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        btnFriendsManager = new javax.swing.JButton();
        tabbedPane = new javax.swing.JTabbedPane();
        btnCreateContent = new javax.swing.JButton();
        profilePhotoLabel = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        btnViewStories = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        sideBarHolder = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Connect Hub Homepage");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setPreferredSize(new java.awt.Dimension(1007, 569));

        btnFriendsManager.setBackground(new java.awt.Color(0, 0, 0));
        btnFriendsManager.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFriendsManager.setForeground(new java.awt.Color(255, 255, 255));
        btnFriendsManager.setText("Friends Manager");
        btnFriendsManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFriendsManagerActionPerformed(evt);
            }
        });

        tabbedPane.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

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

        lblUsername.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
                .addGap(42, 42, 42)
                .addComponent(btnFriendsManager)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(58, 58, 58))
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sideBarHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRefresh)
                        .addGap(86, 86, 86)
                        .addComponent(btnCreateContent, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                        .addComponent(btnViewStories)
                        .addGap(144, 144, 144)
                        .addComponent(profilePhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefresh)
                    .addComponent(profilePhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCreateContent, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnViewStories, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogout)
                    .addComponent(btnFriendsManager, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sideBarHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 1092, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        refresh();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        exitMode = false;
        profileMode = false;
        new Login().createAndShowGUI();
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnViewStoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewStoriesActionPerformed
        storyService.refreshContents();
        storyService.deleteExpiredStories();
        if(friends == null) {
            JOptionPane.showMessageDialog(null, "You have no friends!", "Stories Expired", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<Story> stories = new ArrayList<>();
        for(String friend : friends) {
            stories.addAll(storyService.getListOfUserContents(friend));
        }
        if(!stories.isEmpty()) {
            ViewStories viewStories = new ViewStories(this, true, stories);
            viewStories.setVisible(true);
        }
        else
        JOptionPane.showMessageDialog(null, "There are currently no active friend stories!", "Stories Expired", JOptionPane.ERROR_MESSAGE);
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

    private void btnFriendsManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFriendsManagerActionPerformed
        try {
            Friendship friendship = (new FriendshipService()).loadFriendship();
            new FriendshipManagementMainWindow(friendship, user.getUserId());
        } catch (IOException ex) {
            Logger.getLogger(FriendsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFriendsManagerActionPerformed

    
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
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UserService u = UserService.getInstance();
                try {
                    new Homepage(u.getUser("seif@gmail.com", "seif123")).setVisible(true);
                } catch (InvalidKeySpecException ex) {
                    Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnCreateContent;
    private javax.swing.JButton btnFriendsManager;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnViewStories;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel profilePhotoLabel;
    private javax.swing.JTabbedPane sideBarHolder;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
