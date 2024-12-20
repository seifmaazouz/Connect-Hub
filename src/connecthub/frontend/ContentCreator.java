package connecthub.frontend;

import connecthub.backend.models.Notification;
import connecthub.backend.models.Post;
import connecthub.backend.models.Story;
import connecthub.backend.models.User;
import connecthub.backend.services.NotificationService;
import connecthub.backend.services.PostService;
import connecthub.backend.services.StoryService;
import connecthub.backend.utils.factories.ContentFactory;
import connecthub.backend.utils.factories.ServiceFactory;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import connecthub.frontend.utils.ImageManager;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ContentCreator extends javax.swing.JDialog {

    private User user;
    private File imageFile;
    private StoryService storyService;
    private PostService postService;

    public ContentCreator(java.awt.Frame parent, boolean modal, User user) {
        super(parent, modal);
        initComponents();
        this.user = user;
        imageFile = null;
    }

    // validate input text is not empty
    private String ValidateInputText(JTextArea textArea) throws IOException {
        String text = textArea.getText().strip();
        if (text.isEmpty()) {
            throw new IIOException("Text field cannot be left empty!");
        } else {
            return text;
        }
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
        previewPost = new javax.swing.JLabel();
        createStoryPanel = new javax.swing.JPanel();
        lblTextStory = new javax.swing.JLabel();
        lblImageStory = new javax.swing.JLabel();
        btnUploadImageStory = new javax.swing.JButton();
        btnPublishStory = new javax.swing.JButton();
        lblImageStatusStory = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaStory = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        previewStory = new javax.swing.JLabel();
        Title = new java.awt.Label();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(635, 700));
        setUndecorated(true);
        setResizable(false);

        backgroundPanel.setBackground(new java.awt.Color(255, 204, 102));
        backgroundPanel.setMinimumSize(new java.awt.Dimension(635, 700));
        backgroundPanel.setPreferredSize(new java.awt.Dimension(635, 700));

        jTabbedPane1.setBackground(new java.awt.Color(255, 204, 102));
        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        createPostPanel.setBackground(new java.awt.Color(251, 224, 170));
        createPostPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Post Creator Menu", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        createPostPanel.setAutoscrolls(true);

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

        textAreaPost.setColumns(20);
        textAreaPost.setLineWrap(true);
        textAreaPost.setRows(5);
        jScrollPane2.setViewportView(textAreaPost);

        previewPost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout createPostPanelLayout = new javax.swing.GroupLayout(createPostPanel);
        createPostPanel.setLayout(createPostPanelLayout);
        createPostPanelLayout.setHorizontalGroup(
            createPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createPostPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(createPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, createPostPanelLayout.createSequentialGroup()
                        .addComponent(lblImagePost, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnUploadImagePost))
                    .addComponent(lblTextPost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(126, 126, 126))
            .addGroup(createPostPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImageStatusPost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(createPostPanelLayout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addGroup(createPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPublishPost, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(previewPost, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(190, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImageStatusPost, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(previewPost, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPublishPost, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        btnPublishStory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublishStoryActionPerformed(evt);
            }
        });

        lblImageStatusStory.setForeground(new java.awt.Color(153, 0, 0));
        lblImageStatusStory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        textAreaStory.setColumns(20);
        textAreaStory.setLineWrap(true);
        textAreaStory.setRows(5);
        jScrollPane3.setViewportView(textAreaStory);

        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Note: Story will expire after 24-hours upon publishing");

        previewStory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout createStoryPanelLayout = new javax.swing.GroupLayout(createStoryPanel);
        createStoryPanel.setLayout(createStoryPanelLayout);
        createStoryPanelLayout.setHorizontalGroup(
            createStoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
            .addGroup(createStoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImageStatusStory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(createStoryPanelLayout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addGroup(createStoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPublishStory, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(previewStory, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImageStatusStory, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(previewStory, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPublishStory, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Create Story", createStoryPanel);

        Title.setAlignment(java.awt.Label.CENTER);
        Title.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        Title.setForeground(new java.awt.Color(51, 51, 51));
        Title.setText("Content Creator");

        jButton1.setBackground(new java.awt.Color(204, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadImagePostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImagePostActionPerformed
        File selectedFile = ImageManager.uploadImage();
        // Set global selected imageFile to this imageFile;
        imageFile = selectedFile;
        // Set label status to selected file and preview image
        if (imageFile != null) {
            lblImageStatusPost.setText("Selected File: " + selectedFile.getName());
            try {
                Image image = ImageManager.getImageFromFile(imageFile, 200, 200);
                previewPost.setIcon(new ImageIcon(image));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot Load Image!", "Publish Error", JOptionPane.ERROR_MESSAGE);
                // reset image
                lblImageStatusPost.setText("");
                imageFile = null;
            }
        }
    }//GEN-LAST:event_btnUploadImagePostActionPerformed

    private void btnPublishPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublishPostActionPerformed
        String userText;
        try {
            userText = ValidateInputText(textAreaPost);
            // create postService
            postService = ServiceFactory.createPostService();
            // copy selected image to database and get new image path
            String imagePath = ImageManager.copyImageToProgramFiles(user, imageFile);
            // create post
            Post newPost = ContentFactory.createPost(user.getUserId(), userText, imagePath);
            // add post
            postService.addContent(newPost);
            imageFile = null;
            NotificationService notificationService = new NotificationService(user);
            notificationService.sendNotification(Notification.Type.NEW_POST, null, newPost.getContentId());
            this.dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Cannot Publish Post: " + ex.getMessage(), "Publish Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPublishPostActionPerformed

    private void btnUploadImageStoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImageStoryActionPerformed
        File selectedFile = ImageManager.uploadImage();
        // Set global selected imageFile to this imageFile;
        imageFile = selectedFile;
        // Set label status to selected file and preview image
        if (imageFile != null) {
            lblImageStatusStory.setText("Selected File: " + selectedFile.getName());
            try {
                Image image = ImageManager.getImageFromFile(imageFile, 200, 200);
                previewStory.setIcon(new ImageIcon(image));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot Load Image!", "Publish Error", JOptionPane.ERROR_MESSAGE);
                // reset image
                lblImageStatusStory.setText("");
                imageFile = null;
            }
        }
    }//GEN-LAST:event_btnUploadImageStoryActionPerformed

    private void btnPublishStoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublishStoryActionPerformed
        String userText;
        try {
            userText = ValidateInputText(textAreaStory);
            // create storyService
            storyService = ServiceFactory.createStoryService();
            // copy selected image to database and get new image path
            String imagePath = ImageManager.copyImageToProgramFiles(user, imageFile);
            // create story
            Story newStory = ContentFactory.createStory(user.getUserId(), userText, imagePath);
            // add story
            storyService.addContent(newStory);
            imageFile = null;
            this.dispose();
            System.out.println("Here");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Cannot Publish Story: " + ex.getMessage(), "Publish Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPublishStoryActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // reset fields
        textAreaPost.setText("");
        textAreaStory.setText("");
        lblImageStatusPost.setText("");
        lblImageStatusStory.setText("");
        previewPost.setIcon(null);
        previewStory.setIcon(null);
        imageFile = null;
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label Title;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton btnPublishPost;
    private javax.swing.JButton btnPublishStory;
    private javax.swing.JButton btnUploadImagePost;
    private javax.swing.JButton btnUploadImageStory;
    private javax.swing.JPanel createPostPanel;
    private javax.swing.JPanel createStoryPanel;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel previewPost;
    private javax.swing.JLabel previewStory;
    private javax.swing.JTextArea textAreaPost;
    private javax.swing.JTextArea textAreaStory;
    // End of variables declaration//GEN-END:variables
}
