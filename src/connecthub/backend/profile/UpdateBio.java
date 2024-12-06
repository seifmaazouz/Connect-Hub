package connecthub.backend.profile;

import connecthub.backend.models.User;
import javax.swing.JOptionPane;

public class UpdateBio {

    User user;

    public UpdateBio(User user) {
        this.user = user;
    }

    public String update() {
        String text = JOptionPane.showInputDialog(null, "Enter bio");
        user.setBio(text);
        return text;
    }
}
