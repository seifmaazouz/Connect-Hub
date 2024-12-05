package connecthub.frontend;

import java.io.File;
import java.io.IOException;
import javax.imageio.IIOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ContentCreatorWindow extends javax.swing.JFrame {
    private File UPLOAD_DIRECTORY;

    public ContentCreatorWindow() {
        initComponents();
        UPLOAD_DIRECTORY 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        createPostPanel = new javax.swing.JPanel();
        lblTextPost = new javax.swing.JLabel();
        lblImagePost = new javax.swing.JLabel();
        btnUploadImagePost = new javax.swing.JButton();
        btnPublishPost = new javax.swing.JButton();
        lblImageStatusPost = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaPost = new javax.swing.JTextArea();
        createStoryPanel = new javax.swing.JPanel();
        lblTextStory = new javax.swing.JLabel();
        lblImageStory = new javax.swing.JLabel();
        btnUploadImageStory = new javax.swing.JButton();
        btnPublishStory = new javax.swing.JButton();
        lblImageStatusStory = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaStory = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        Title = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Content Creator");
        setResizable(false);

        backgroundPanel.setBackground(new java.awt.Color(255, 204, 102));

        jTabbedPane1.setBackground(new java.awt.Color(255, 204, 102));
        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        createPostPanel.setBackground(new java.awt.Color(251, 224, 170));
        createPostPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Post Creator Menu", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        lblTextPost.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTextPost.setText("Enter Text To Post:");

        lblImagePost.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblImagePost.setText("Attach Image To Post (Optional):");

        btnUploadImagePost.setBackground(new java.awt.Color(0, 0, 0));
        btnUploadImagePost.setForeground(new java.awt.Color(255, 255, 255));
        btnUploadImagePost.setText("Upload Image");
        btnUploadImagePost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadImagePostActionPerformed(evt);
            }
        });

        btnPublishPost.setBackground(new java.awt.Color(0, 0, 0));
        btnPublishPost.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPublishPost.setForeground(new java.awt.Color(255, 255, 255));
        btnPublishPost.setText("Publish Post");
        btnPublishPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublishPostActionPerformed(evt);
            }
        });

        lblImageStatusPost.setForeground(new java.awt.Color(153, 0, 0));
        lblImageStatusPost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImageStatusPost.setText("\"Image.png\"  Uploaded Successfully");

        textAreaPost.setColumns(20);
        textAreaPost.setLineWrap(true);
        textAreaPost.setRows(5);
        jScrollPane2.setViewportView(textAreaPost);

        javax.swing.GroupLayout createPostPanelLayout = new javax.swing.GroupLayout(createPostPanel);
        createPostPanel.setLayout(createPostPanelLayout);
        createPostPanelLayout.setHorizontalGroup(
            createPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createPostPanelLayout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addGroup(createPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, createPostPanelLayout.createSequentialGroup()
                        .addComponent(lblImagePost, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnUploadImagePost))
                    .addComponent(lblTextPost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(126, 126, 126))
            .addGroup(createPostPanelLayout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addGroup(createPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImageStatusPost, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPublishPost, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        createPostPanelLayout.setVerticalGroup(
            createPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createPostPanelLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(lblTextPost)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblImagePost, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUploadImagePost))
                .addGap(18, 18, 18)
                .addComponent(lblImageStatusPost, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnPublishPost, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("Create Post", createPostPanel);

        createStoryPanel.setBackground(new java.awt.Color(251, 224, 170));
        createStoryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Story Creator Menu", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        lblTextStory.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTextStory.setText("Enter Text To Story:");

        lblImageStory.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblImageStory.setText("Attach Image To Story (Optional):");

        btnUploadImageStory.setBackground(new java.awt.Color(0, 0, 0));
        btnUploadImageStory.setForeground(new java.awt.Color(255, 255, 255));
        btnUploadImageStory.setText("Upload Image");
        btnUploadImageStory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadImageStoryActionPerformed(evt);
            }
        });

        btnPublishStory.setBackground(new java.awt.Color(0, 0, 0));
        btnPublishStory.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPublishStory.setForeground(new java.awt.Color(255, 255, 255));
        btnPublishStory.setText("Publish Story");

        lblImageStatusStory.setForeground(new java.awt.Color(153, 0, 0));
        lblImageStatusStory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImageStatusStory.setText("\"Image.png\"  Uploaded Successfully");

        textAreaStory.setColumns(20);
        textAreaStory.setLineWrap(true);
        textAreaStory.setRows(5);
        jScrollPane3.setViewportView(textAreaStory);

        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Note: Story will expire upon 24-hours from publishing");

        javax.swing.GroupLayout createStoryPanelLayout = new javax.swing.GroupLayout(createStoryPanel);
        createStoryPanel.setLayout(createStoryPanelLayout);
        createStoryPanelLayout.setHorizontalGroup(
            createStoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createStoryPanelLayout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addGroup(createStoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImageStatusStory, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPublishStory, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createStoryPanelLayout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addGroup(createStoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, createStoryPanelLayout.createSequentialGroup()
                        .addComponent(lblImageStory, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUploadImageStory))
                    .addComponent(lblTextStory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(126, 126, 126))
        );
        createStoryPanelLayout.setVerticalGroup(
            createStoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createStoryPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(46, 46, 46)
                .addComponent(lblTextStory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createStoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblImageStory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUploadImageStory))
                .addGap(18, 18, 18)
                .addComponent(lblImageStatusStory, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnPublishStory, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("Create Story", createStoryPanel);

        Title.setAlignment(java.awt.Label.CENTER);
        Title.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        Title.setForeground(new java.awt.Color(51, 51, 51));
        Title.setText("Content Creator");

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private String ValidateInputText(JTextArea textArea) throws IOException {
        String text = textArea.getText().strip();
        if(text.isEmpty())
            throw new IIOException("Text field cannot be left empty!");
        else
             return text;
    }
    
    private JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt")); // Show only text files;
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Load Shapes");
        // Create the folder if it doesn't exist in documents
        if (!UPLOAD_DIRECTORY.exists()) {
            boolean isCreated = UPLOAD_DIRECTORY.mkdirs();
            if (!isCreated) {
                System.out.println("Failed to create the directory.");
                return null;
            }
        }
        fileChooser.setCurrentDirectory(UPLOAD_DIRECTORY);
        return fileChooser;
    }
    
    private void btnUploadImagePostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImagePostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUploadImagePostActionPerformed

    private void btnUploadImageStoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImageStoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUploadImageStoryActionPerformed

    private void btnPublishPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublishPostActionPerformed
        String userText;
        String userImage;
        try {
            userText = ValidateInputText(textAreaPost);
            System.out.println(userText);
            userImage = 
        } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Cannot Publish: " +ex.getMessage(), "Publish Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPublishPostActionPerformed

    public static void main(String args[]) {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContentCreatorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContentCreatorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContentCreatorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContentCreatorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContentCreatorWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label Title;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton btnPublishPost;
    private javax.swing.JButton btnPublishStory;
    private javax.swing.JButton btnUploadImagePost;
    private javax.swing.JButton btnUploadImageStory;
    private javax.swing.JPanel createPostPanel;
    private javax.swing.JPanel createStoryPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblImagePost;
    private javax.swing.JLabel lblImageStatusPost;
    private javax.swing.JLabel lblImageStatusStory;
    private javax.swing.JLabel lblImageStory;
    private javax.swing.JLabel lblTextPost;
    private javax.swing.JLabel lblTextStory;
    private javax.swing.JTextArea textAreaPost;
    private javax.swing.JTextArea textAreaStory;
    // End of variables declaration//GEN-END:variables
}
