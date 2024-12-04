//package connecthub.controllers;
//
//import connecthub.models.User;
//import connecthub.services.UserService;
//import javafx.scene.control.Alert;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.fxml.FXML;
//
//import java.security.NoSuchAlgorithmException;
//import java.security.spec.InvalidKeySpecException;
//
//
//public class LoginController {
//
//    @FXML
//    private TextField emailField;
//
//    @FXML
//    private PasswordField passwordField;
//
//
//    private UserService userService;
//
//
//    public LoginController() {
//        userService = new UserService();
//    }
//
//
//    @FXML
//    private void handleLogin() throws InvalidKeySpecException, NoSuchAlgorithmException {
//        String email = emailField.getText();
//        String password = passwordField.getText();
//
//        if (email.isEmpty() || password.isEmpty()) {
//            showAlert("Error", "Please fill out all fields.");
//            return;
//        }
//
//        User user = userService.login(email, password);
//
//        if (user != null) {
//            showAlert("Success", "Welcome, " + user.getUsername() + "!");
//            // TODO: Transition to the next scene or update the UI
//        } else {
//            showAlert("Error", "Invalid username or password.");
//        }
//    }
//
//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//}
