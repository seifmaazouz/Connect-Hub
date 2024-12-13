package connecthub.frontend.group;

import connecthub.backend.models.ContentData;
import connecthub.backend.models.Post;
import connecthub.backend.models.User;
import connecthub.backend.models.group.Group;
import connecthub.backend.services.UserService;
import connecthub.backend.utils.factories.ContentFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.UUID;

public class GroupWindow extends JFrame {
    private JPanel postsPanel;
    private JTextField newPostTextField;
    private User user;
    private Group group;

    public GroupWindow(String groupName, List<Post> initialPosts, User user, Group group) {
        this.user = user;
        this.group = group;

        // Frame settings
        setTitle(groupName);
        setSize(1200, 700); // Increased size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Layout
        setLayout(new BorderLayout());

        // Header with "Leave Group" button
        JPanel headerPanel = new JPanel();
        JLabel groupNameLabel = new JLabel(groupName + " - " + user.getUsername());
        JButton leaveGroupButton = new JButton("Leave Group");
        leaveGroupButton.setBackground(Color.RED);
        leaveGroupButton.setForeground(Color.WHITE);
        leaveGroupButton.setFocusPainted(false); // Removes focus border
        headerPanel.add(groupNameLabel);
        headerPanel.add(leaveGroupButton);
        add(headerPanel, BorderLayout.NORTH);

        // Posts section
        postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(postsPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Add post section
        JPanel addPostPanel = new JPanel(new BorderLayout());
        newPostTextField = new JTextField();
        JButton submitPostButton = new JButton("Add Post");
        submitPostButton.setBackground(Color.GREEN);
        submitPostButton.setForeground(Color.WHITE);
        submitPostButton.setFocusPainted(false); // Removes focus border
        addPostPanel.add(newPostTextField, BorderLayout.CENTER);
        addPostPanel.add(submitPostButton, BorderLayout.EAST);
        add(addPostPanel, BorderLayout.SOUTH);

        // Event listeners
        leaveGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leaveGroup();
            }
        });

        submitPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPost();
            }
        });

        // Initialize the post list with the given posts
        if (initialPosts != null) {
            updatePostsListWithPanel(initialPosts);
        }
    }

    private void leaveGroup() {
        // Logic to leave the group
        System.out.println("User leaves the group");
        dispose(); // Close the window
    }

    private void addPost() {
        String newPostContent = newPostTextField.getText();
        if (!newPostContent.trim().isEmpty()) {
            newPostTextField.setText("");
            CreatePost createPost = new CreatePost(this, true, group.getId(), user, newPostContent);
            createPost.setVisible(true);
        }
    }

    private void updatePostsListWithPanel(List<Post> posts) {
        postsPanel.removeAll(); // Clear the existing posts
        for (Post post : posts) {
            JPanel postPanel = createPostPanel(post);
            postsPanel.add(postPanel);
        }
        postsPanel.revalidate(); // Revalidate to apply layout changes
        postsPanel.repaint(); // Repaint the panel to display the new posts
    }

    private JPanel createPostPanel(Post post) {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        ContentData contentData = post.getContentData();

        // Add text content to the panel
        if (contentData.getText() != null && !contentData.getText().isEmpty()) {
            JLabel textLabel = new JLabel(contentData.getText());
            postPanel.add(textLabel);
        }

        // If there is an image, display it
        if (contentData.getImagePath() != null && !contentData.getImagePath().isEmpty()) {
            ImageIcon originalIcon = new ImageIcon(contentData.getImagePath());

            // Scale the image to 200x200 pixels
            Image img = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);

            JLabel imageLabel = new JLabel(scaledIcon);
            postPanel.add(imageLabel);
        }

        return postPanel;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Example usage with initial posts
            User user = null;
            try {
                user = UserService.getInstance().getUser("seif@gmail.com", "seif123");
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            // Assuming the group and posts are fetched or created properly
            Group group = new Group(); // You need to fetch or initialize a valid group here
            group.setId(UUID.randomUUID().toString());

            List<Post> examplePosts = List.of(
                    ContentFactory.createPost(user.getUserId() ,"First Post", "D:\\Github\\Connect-Hub\\src\\connecthub\\backend\\database\\images\\1022_2024-12-12_21-32-23.jpg"),
                    ContentFactory.createPost(user.getUserId() ,"Second Post", null),
                    ContentFactory.createPost(user.getUserId() ,"Third Post", null) // no image
            );

            GroupWindow window = new GroupWindow("Group Name", examplePosts, user, group);
            window.setVisible(true);
        });
    }
}
