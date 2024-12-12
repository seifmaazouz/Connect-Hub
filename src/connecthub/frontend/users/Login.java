package connecthub.frontend.users;

import connecthub.backend.models.User;
import connecthub.backend.services.UserService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Login {
    private UserService userService;

    public Login() {
        this.userService = new UserService();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new Login().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLayout(new BorderLayout());

        // Create panel for inputs
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel emailLabel = createCenteredLabel("Email:");
        JTextField emailField = createSizedTextField();
        JLabel passwordLabel = createCenteredLabel("Password:");
        JPasswordField passwordField = createSizedPasswordField();

        inputPanel.add(emailLabel);
        inputPanel.add(emailField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton loginButton = new JButton("Login");
        JButton resetButton = new JButton("Reset");
        JButton signUpButton = new JButton("Sign Up");
        JButton logoutButton = new JButton("Logout");

        buttonPanel.add(loginButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(signUpButton);
        buttonPanel.add(logoutButton);

        // Add ActionListeners
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            try {
                User user = userService.login(email, password);
                // If login is successful, display a success message
                if (user != null) {
                    JOptionPane.showMessageDialog(frame,
                            "Login successful!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(frame,
                            "Login failed: Invalid email or password.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                // TODO: Open the newsfeed window here

            } catch (InvalidKeySpecException ex) {
                // Handle InvalidKeySpecException specifically
                JOptionPane.showMessageDialog(frame,
                        "An error occurred while processing your login. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } catch (NoSuchAlgorithmException ex) {
                // Handle NoSuchAlgorithmException specifically
                JOptionPane.showMessageDialog(frame,
                        "A technical error occurred. Please contact support.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } catch (Exception ex) {
                // General catch block for login failures
                JOptionPane.showMessageDialog(frame,
                        "Login failed: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });


        resetButton.addActionListener(e -> {
            emailField.setText("");
            passwordField.setText("");
        });

        signUpButton.addActionListener(e -> openSignUpDialog(frame));

        // Add components to the frame
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Set frame to visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        logoutButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            try {
                userService.getUser(email, password).setStatus("offline");
                // If logout is successful, display a success message
                JOptionPane.showMessageDialog(frame,
                        "Logout successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (InvalidKeySpecException ex) {
                // Handle InvalidKeySpecException specifically
                JOptionPane.showMessageDialog(frame,
                        "An error occurred while processing your logout. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } catch (NoSuchAlgorithmException ex) {
                // Handle NoSuchAlgorithmException specifically
                JOptionPane.showMessageDialog(frame,
                        "A technical error occurred. Please contact support.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } catch (Exception ex) {
                // General catch block for login failures
                JOptionPane.showMessageDialog(frame,
                        "Logout failed: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void openSignUpDialog(JFrame parent) {
        JDialog signUpDialog = new JDialog(parent, "Sign Up", true);
        signUpDialog.setSize(500, 400);
        signUpDialog.setLayout(new BorderLayout());

        // Create panel for inputs
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10)); // Updated grid layout: 5 rows

        JLabel emailLabel = createCenteredLabel("Email:");
        JTextField emailField = createSizedTextField();
        JLabel usernameLabel = createCenteredLabel("Username:");
        JTextField usernameField = createSizedTextField();
        JLabel passwordLabel = createCenteredLabel("Password:");
        JPasswordField passwordField = createSizedPasswordField();
        JLabel dobLabel = createCenteredLabel("Date of Birth (yyyy-MM-dd):");
        JTextField dobField = createSizedTextField();
        JLabel statusLabel = createCenteredLabel("Status:");
        JComboBox<String> statusComboBox = new JComboBox<>(new String[]{"online", "offline"});

        inputPanel.add(emailLabel);
        inputPanel.add(emailField);
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(dobLabel);
        inputPanel.add(dobField);
        inputPanel.add(statusLabel);
        inputPanel.add(statusComboBox);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        // Add ActionListeners
        submitButton.addActionListener(e -> {
            String email = emailField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String dobText = dobField.getText();
            String status = (String) statusComboBox.getSelectedItem();

            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || dobText.isEmpty() || status == null) {
                JOptionPane.showMessageDialog(signUpDialog, "Please fill all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dob = sdf.parse(dobText);

                User newUser = new User(email, username, password, dob, status);

                boolean isSignedUp = userService.signup(newUser);

                if (isSignedUp) {
                    JOptionPane.showMessageDialog(signUpDialog, "Sign Up Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    signUpDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(signUpDialog, "Sign Up failed: User already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(signUpDialog, "Invalid Date of Birth format. Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        cancelButton.addActionListener(e -> signUpDialog.dispose());

        // Add components to the dialog
        signUpDialog.add(inputPanel, BorderLayout.CENTER);
        signUpDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Set dialog to visible
        signUpDialog.setLocationRelativeTo(parent);
        signUpDialog.setVisible(true);
    }


    private JLabel createCenteredLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        return label;
    }

    private JTextField createSizedTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 25)); // Smaller height
        return textField;
    }

    private JPasswordField createSizedPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25)); // Smaller height
        return passwordField;
    }
}