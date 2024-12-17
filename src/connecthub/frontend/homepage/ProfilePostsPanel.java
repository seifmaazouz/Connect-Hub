package connecthub.frontend.homepage;

import connecthub.backend.models.Post;
import connecthub.backend.models.User;
import connecthub.backend.services.PostService;
import connecthub.backend.services.UserService;
import connecthub.backend.utils.factories.ServiceFactory;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.SwingUtilities;

public class ProfilePostsPanel extends javax.swing.JPanel {
    private final UserService userService;
    private final PostService postService;
    private List<Post> posts;
    private String userId;
   
    public ProfilePostsPanel(String userId) {
        this.userId = userId;
        userService = UserService.getInstance();
        postService = ServiceFactory.createPostService();
        initComponents();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel
        this.setOpaque(false);
        posts = postService.getListOfUserContents(userId);
        displayPosts(posts);
    }

    public void refresh() {
        posts = postService.getListOfUserContents(userId);
        displayPosts(posts);
        jDisplayPosts.getVerticalScrollBar().setValue(0); // reset to top
        revalidate();
        repaint();
    }
    
    private void displayPosts(List<Post> posts) {
        PostsPanel postsPanel = new PostsPanel(posts, userId);
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

        jDisplayPosts = new javax.swing.JScrollPane();

        jDisplayPosts.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jDisplayPosts.setMaximumSize(new java.awt.Dimension(500, 32767));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(260, 260, 260)
                .addComponent(jDisplayPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDisplayPosts, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jDisplayPosts;
    // End of variables declaration//GEN-END:variables
}
