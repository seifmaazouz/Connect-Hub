package connecthub.backend.profile;

import connecthub.backend.database.UserDatabase;
import connecthub.backend.models.User;
import javax.swing.JOptionPane;

public class UpdateBio {

    private User user;
    private UserDatabase userDatabase;

    public UpdateBio(User user, UserDatabase userDatabase) {
        this.user = user;
        this.userDatabase = userDatabase;
    }

    public String update() {
        String text = JOptionPane.showInputDialog(null, "Enter bio");
        user.setBio(text);
        userDatabase.saveUser(user);
        return text;
    }
}
