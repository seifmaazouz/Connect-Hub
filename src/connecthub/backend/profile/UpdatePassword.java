package connecthub.backend.profile;

import connecthub.backend.models.User;
import connecthub.backend.services.UserService;
import connecthub.backend.utils.password.PBKDF2Validation;
import connecthub.backend.utils.password.ValidationBehaviour;
import connecthub.frontend.Profile;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class UpdatePassword {

    private User user;
    private ValidationBehaviour validationBehaviour;
    private UserService userService;

    public UpdatePassword(User user) {
        this.user = user;
        this.validationBehaviour = new PBKDF2Validation();
        this.userService = new UserService();
    }

    public void update() {
        JPasswordField passwordField = new JPasswordField();
        int choice = JOptionPane.showConfirmDialog(null, passwordField, "Enter current password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        String enteredPassword = null;
        if (choice == JOptionPane.OK_OPTION) {
            char[] password = passwordField.getPassword();
            enteredPassword = new String(password);
            if (enteredPassword != null) {
                try {
                    boolean validated = validationBehaviour.validatePassword(enteredPassword, user.getHashedPassword(), user.getSalt());
                    if (validated) {
                        choice = JOptionPane.showConfirmDialog(null, passwordField, "Enter new password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        String newPassword = null;
                        if (choice == JOptionPane.OK_OPTION) {
                            password = passwordField.getPassword();
                            newPassword = new String(password);
                            if (newPassword != null) {
                                JOptionPane.showMessageDialog(null, "Password has changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                userService.setUserPassword(user.getUserId(), newPassword);
                            } else {
                                JOptionPane.showMessageDialog(null, "Empty password!");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong password!");
                    }
                } catch (InvalidKeySpecException ex) {
                    Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
