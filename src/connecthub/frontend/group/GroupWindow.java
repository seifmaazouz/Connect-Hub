package connecthub.frontend.group;

import connecthub.backend.models.User;
import connecthub.backend.models.group.Group;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GroupWindow extends JFrame {
    private JList<String> postsList;
    private DefaultListModel<String> listModel;
    private JTextField newPostTextField;

    public GroupWindow(String groupName, List<String> initialPosts, User user, Group group) {
        listModel = new DefaultListModel<>();
        postsList = new JList<>(listModel);
        updatePostsList(initialPosts);

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
        JScrollPane scrollPane = new JScrollPane(postsList);
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
    }

    private void leaveGroup() {
        // Logic to leave the group
        System.out.println("User leaves the group");
        dispose(); // Close the window
    }

    private void addPost() {
        String newPostContent = newPostTextField.getText();
        if (!newPostContent.trim().isEmpty()) {
            listModel.addElement(newPostContent);
            newPostTextField.setText("");
        }
    }

    private void updatePostsList(List<String> posts) {
        listModel.clear();
        for (String post : posts) {
            listModel.addElement(post);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Example usage with initial posts
            List<String> examplePosts = List.of("First Post", "Second Post", "Third Post");
            String id = UUID.randomUUID().toString();
            GroupWindow window;
            User user;
            try {
                user = new User(id, "es@gmail.com", "ahmedn", new Date(2024, 12, 25, 15, 30), "online", "sss", "sss");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }

            window = new GroupWindow("Group Name", examplePosts, user, new Group());

            window.setVisible(true);
        });
    }
}
