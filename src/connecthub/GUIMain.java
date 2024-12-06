package connecthub;

import connecthub.backend.models.User;
import connecthub.frontend.ContentCreator;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.swing.*;

public class GUIMain {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        setLook();
        User user = new User("userID" ,"email", "asser", null, "online", "hashedpass", "salt");
        ContentCreator window = new ContentCreator(null, true, user);
        window.setVisible(true);
//        Profile p = new Profile(user);
//        p.setVisible(true);
    }
    
    private static void setLook() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContentCreator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }
}
