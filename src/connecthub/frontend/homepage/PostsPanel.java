package connecthub.frontend.homepage;

import connecthub.backend.models.ContentData;
import connecthub.backend.models.Post;
import connecthub.backend.models.User;
import connecthub.backend.services.PostService;
import connecthub.backend.services.UserService;
import connecthub.backend.utils.factories.ServiceFactory;
import connecthub.frontend.utils.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PostsPanel extends javax.swing.JPanel {
    private List<Post> posts;
    private final UserService userService;
    private final PostService postService;
    private final String viewingUserId;

    public PostsPanel(List<Post> posts, String viewingUserId) {
        initComponents();
        this.posts = posts;
        this.userService = UserService.getInstance();
        this.postService = ServiceFactory.createPostService();
        this.viewingUserId = viewingUserId;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel
        this.setOpaque(false);
        initializePosts(posts);
    }

    private void initializePosts(List<Post> posts) {
        if (posts != null) {
            for (int i = posts.size() - 1; i >= 0; i--) {
                Post post = posts.get(i);
                JPanel postPanel = new JPanel();
                postPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
                postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around each post
                postPanel.setBackground(Color.WHITE); // Set background color to white
                postPanel.setOpaque(true);

                // Fetch the author
                User author = userService.getUserById(post.getAuthorId());
                JPanel authorPanel = new JPanel();
                authorPanel.setLayout(new BoxLayout(authorPanel, BoxLayout.Y_AXIS));
                authorPanel.setOpaque(false);

                // Author details (photo + username)
                JPanel authorDetailsPanel = new JPanel();
                authorDetailsPanel.setLayout(new BoxLayout(authorDetailsPanel, BoxLayout.X_AXIS));
                authorDetailsPanel.setOpaque(false);
                authorDetailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                ImageIcon profileIcon = new ImageIcon(author.getProfilePhoto());
                Image profileImage = ImageManager.getScaledImage(profileIcon.getImage(), 35, 35);
                ProfilePhoto profilePhoto = new ProfilePhoto(profileImage, 35, 1);
                authorDetailsPanel.add(profilePhoto);

                JLabel authorLabel = new JLabel(" " + author.getUsername());
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
                
                // get post content
                ContentData contentData = post.getContentData();
                
                // Post content
                JTextArea postTextArea = new JTextArea(contentData.getText());
                postTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for post text
                postTextArea.setLineWrap(true); // Enable line wrapping
                postTextArea.setWrapStyleWord(true); // Wrap at word boundaries
                postTextArea.setEditable(false); // Make it non-editable
                postTextArea.setOpaque(false); // Set background to transparent to blend with the panel
                postTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);

                // Remove the border around the JTextArea
                postTextArea.setBorder(BorderFactory.createEmptyBorder());
                postTextArea.setBackground(new Color(0, 0, 0, 0)); // Transparent background
                postPanel.add(postTextArea);

                // Post image (if any)
                String imagePath = contentData.getImagePath();
                if (imagePath != null) {
                    Image image = new ImageIcon(imagePath).getImage();
                    image = ImageManager.getScaledImage(image, 400, 400);
                    JLabel postImageLabel = ImageManager.getImageLabel(image);
                    postImageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    postPanel.add(postImageLabel);
                }

                // Set border and padding
                postPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200)), // Add border around each post
                        BorderFactory.createEmptyBorder(10, 20, 10, 20) // Add padding inside the border
                ));

                // Show comments button
                JButton showCommentsButton = new JButton("Show Comments");
                showCommentsButton.addActionListener(e -> {
                    // Logic to show comments
                    new ViewPostComments(null, true, post.getComments()).setVisible(true);
                });
                postPanel.add(showCommentsButton);

                // Like button
                JButton likeButton = new JButton("Like (" + post.getLikes() + ")");
                if (post.getLikedBy().contains(viewingUserId)) {
                    likeButton.setBackground(Color.decode("#003297"));
                    likeButton.setForeground(Color.WHITE);
                }
                likeButton.addActionListener(e -> {
                    post.like(viewingUserId);
                    likeButton.setText("Like (" + post.getLikes() + ")");
                    if (post.getLikedBy().contains(viewingUserId)) {
                        likeButton.setBackground(Color.decode("#003297"));
                        likeButton.setForeground(Color.WHITE);
                    } else {
                        likeButton.setBackground(null);
                        likeButton.setForeground(null);
                    }
                    postService.addContent(post); // update json file
                });
                postPanel.add(likeButton);

                // Add comment button
                JButton addCommentButton = new JButton("Add Comment");
                addCommentButton.addActionListener(e -> {
                    String comment = JOptionPane.showInputDialog(this, "Enter your comment:");
                    if (comment != null && !comment.trim().isEmpty()) {
                        post.comment(userService.getUserById(viewingUserId).getUsername(), comment);
                        postService.addContent(post); // update json file
                    }
                });

                // Panel for buttons
                JPanel buttonsPanel = new JPanel();
                buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
                buttonsPanel.setOpaque(false);
                buttonsPanel.add(showCommentsButton);
                buttonsPanel.add(Box.createHorizontalStrut(10)); // Add space between buttons
                buttonsPanel.add(likeButton);
                buttonsPanel.add(Box.createHorizontalStrut(10)); // Add space between buttons
                buttonsPanel.add(addCommentButton);

                postPanel.add(Box.createVerticalStrut(10)); // Add space between post content and buttons
                postPanel.add(buttonsPanel);
                this.add(postPanel);
                this.add(Box.createVerticalStrut(10)); // Add space between posts
            }
        }
    }

    public void updatePosts(List<Post> posts) {
        this.posts = posts;
        removeAll();
        initializePosts(posts);
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

        setMaximumSize(new java.awt.Dimension(500, 32767));

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
