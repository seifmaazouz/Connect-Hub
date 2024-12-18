package connecthub.frontend.homepage;

import connecthub.backend.models.Comment;
import connecthub.backend.services.UserService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultListModel;

public class ViewPostComments extends javax.swing.JDialog {
    
    public ViewPostComments(java.awt.Frame parent, boolean modal, List<Comment> comments) {
        super(parent, modal);
        initComponents();

        // create custom date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");

        // Create a list model and populate it with comments
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Comment comment : comments) {
            String username = comment.getUsername();
            String commentText = comment.getText();
            String commentDate = comment.getTimestamp().format(formatter);  
            listModel.addElement(username + ": " + commentText + " (" + commentDate + ")");
        }

        jList1.setModel(listModel);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View Comments");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
