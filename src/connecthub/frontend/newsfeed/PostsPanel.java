package connecthub.frontend.newsfeed;

import connecthub.backend.models.Post;
import connecthub.backend.models.User;
import connecthub.backend.services.UserService;
import connecthub.frontend.utils.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PostsPanel extends javax.swing.JPanel {
    private final List<Post> posts;
    private final UserService userService;

    public PostsPanel(List<Post> posts, UserService userService) {
        initComponents();
        this.posts = posts;
        this.userService = userService;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel
        this.setOpaque(false);
        initializePosts();
    }

    private void initializePosts() {
        if (posts != null) {
            for (int i = posts.size() - 1; i >= 0; i--) {
                Post post = posts.get(i);
                JPanel postPanel = new JPanel();
                postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
                postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around each post
                postPanel.setBackground(Color.WHITE); // Set background color to white
                postPanel.setOpaque(true);

                // Fetch the author
                User user = userService.getUserById(post.getAuthorId());
                JPanel authorPanel = new JPanel();
                authorPanel.setLayout(new BoxLayout(authorPanel, BoxLayout.Y_AXIS));
                authorPanel.setOpaque(false);

                // Author details (photo + username)
                JPanel authorDetailsPanel = new JPanel();
                authorDetailsPanel.setLayout(new BoxLayout(authorDetailsPanel, BoxLayout.X_AXIS));
                authorDetailsPanel.setOpaque(false);
                authorDetailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                ImageIcon profileIcon = new ImageIcon(user.getProfilePhoto());
                Image profileImage = ImageManager.getScaledImage(profileIcon.getImage(), 35, 35);
                ProfilePhoto profilePhoto = new ProfilePhoto(profileImage, 35, 1);
                authorDetailsPanel.add(profilePhoto);

                JLabel authorLabel = new JLabel(" " + user.getUsername());
                authorLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Set font for author label
                authorDetailsPanel.add(authorLabel);

                // Timestamp
                JPanel timePanel = new JPanel();
                timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.X_AXIS));
                timePanel.setOpaque(false);
                timePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
                JLabel timeStampLabel = new JLabel(post.getTimestamp().format(formatter));
                timeStampLabel.setFont(new Font("Arial", Font.ITALIC, 12)); // Set font for timestamp label
                timePanel.add(timeStampLabel);

                // Add author and timestamp to the author panel
                authorPanel.add(authorDetailsPanel);
                authorPanel.add(timePanel);

                postPanel.add(authorPanel);

                // Post content
                JLabel postTextLabel = new JLabel("<html>" + post.getContentData().getText() + "</html>");
                postTextLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for post text label
                postTextLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                postPanel.add(postTextLabel);

                // Post image (if any)
                String imagePath = post.getContentData().getImagePath();
                if (imagePath != null) {
                    Image image = new ImageIcon(imagePath).getImage();
                    image = ImageManager.getScaledImage(image, 400, 400);
                    ImageIcon icon = new ImageIcon(image);
                    JLabel postImageLabel = new JLabel(icon);
                    postImageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    postPanel.add(postImageLabel);
                }

                // Set border and padding
                postPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200)), // Add border around each post
                        BorderFactory.createEmptyBorder(10, 10, 10, 10) // Add padding inside the border
                ));

                // Add the post panel and space between posts
                this.add(postPanel);
                this.add(Box.createVerticalStrut(10));
            }
        }
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
