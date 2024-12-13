package connecthub.frontend.homepage;

import connecthub.backend.models.Post;
import connecthub.backend.services.UserService;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.SwingUtilities;

public class NewsFeedPanel extends javax.swing.JPanel {
    private final UserService userService;

    public NewsFeedPanel(List<Post> posts) {
        initComponents();
        this.userService = new UserService();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel
        this.setOpaque(false);
        displayPosts(posts);
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

    public void refresh(List<Post> posts) {
        displayPosts(posts);
        jDisplayPosts.getVerticalScrollBar().setValue(0); // reset to top
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDisplayPosts = new javax.swing.JScrollPane();

        setPreferredSize(new java.awt.Dimension(1020, 600));

        jDisplayPosts.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jDisplayPosts.setMaximumSize(new java.awt.Dimension(500, 32767));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(jDisplayPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(269, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDisplayPosts, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jDisplayPosts;
    // End of variables declaration//GEN-END:variables

}
