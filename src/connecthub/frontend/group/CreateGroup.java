package connecthub.frontend.group;

import connecthub.backend.models.ContentData;
import connecthub.backend.models.User;
import connecthub.backend.services.GroupService;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import connecthub.frontend.utils.ImageManager;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class CreateGroup extends javax.swing.JDialog {
    private User user;
    private  File imageFile;
    private GroupService groupService;

    
    public CreateGroup(java.awt.Frame parent, boolean modal, User user) {
        super(parent, modal);
        initComponents();
        this.user = user;
        imageFile = null;
    }
    
    // validate input text is not empty
    private String ValidateInputText(JTextArea textArea) throws IOException {
        String text = textArea.getText().strip();
        if(text.isEmpty())
            throw new IIOException("Text field cannot be left empty!");
        else
             return text;
    }
    
    // validate input text is not empty
    private String ValidateInputText(JTextField textArea) throws IOException {
        String text = textArea.getText().strip();
        if(text.isEmpty())
            throw new IIOException("Text field cannot be left empty!");
        else
             return text;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        createPostPanel = new javax.swing.JPanel();
        lblDescription = new javax.swing.JLabel();
        lblImageUpload = new javax.swing.JLabel();
        btnUploadImage = new javax.swing.JButton();
        btnCreateGroup = new javax.swing.JButton();
        lblImageStatus = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaDescription = new javax.swing.JTextArea();
        previewImage = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        textFieldTitle = new javax.swing.JTextField();
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

        createPostPanel.setBackground(new java.awt.Color(251, 224, 170));
        createPostPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Post Creator Menu", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        createPostPanel.setAutoscrolls(true);

        lblDescription.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDescription.setText("Enter Group Description:");

        lblImageUpload.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblImageUpload.setText("Attach Group Image:");

        btnUploadImage.setBackground(new java.awt.Color(0, 0, 0));
        btnUploadImage.setForeground(new java.awt.Color(255, 255, 255));
        btnUploadImage.setText("Upload Image");
        btnUploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadImageActionPerformed(evt);
            }
        });

        btnCreateGroup.setBackground(new java.awt.Color(0, 0, 0));
        btnCreateGroup.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCreateGroup.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateGroup.setText("Create Group");
        btnCreateGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateGroupActionPerformed(evt);
            }
        });

        lblImageStatus.setForeground(new java.awt.Color(153, 0, 0));
        lblImageStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        textAreaDescription.setColumns(20);
        textAreaDescription.setLineWrap(true);
        textAreaDescription.setRows(5);
        jScrollPane2.setViewportView(textAreaDescription);

        previewImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitle.setText("Enter Group Title:");

        textFieldTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldTitleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout createPostPanelLayout = new javax.swing.GroupLayout(createPostPanel);
        createPostPanel.setLayout(createPostPanelLayout);
        createPostPanelLayout.setHorizontalGroup(
            createPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createPostPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImageStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(createPostPanelLayout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addGroup(createPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCreateGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(previewImage, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(190, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createPostPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(createPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(textFieldTitle)
                    .addGroup(createPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, createPostPanelLayout.createSequentialGroup()
                            .addComponent(lblImageUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(btnUploadImage))
                        .addComponent(lblDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(126, 126, 126))
        );
        createPostPanelLayout.setVerticalGroup(
            createPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createPostPanelLayout.createSequentialGroup()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lblDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblImageUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUploadImage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(previewImage, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCreateGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Create Group", createPostPanel);

        Title.setAlignment(java.awt.Label.CENTER);
        Title.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        Title.setForeground(new java.awt.Color(51, 51, 51));
        Title.setText("Group Creator");

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

    private void btnUploadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImageActionPerformed
        File selectedFile = ImageManager.uploadImage();
        // Set global selected imageFile to this imageFile;
        imageFile = selectedFile;
        // Set label status to selected file and preview image
        if(imageFile != null) {
            lblImageStatus.setText("Selected File: " + selectedFile.getName());
            try {
                Image image = ImageManager.getImageFromFile(imageFile, 200, 200);
                previewImage.setIcon(new ImageIcon(image));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot Load Image!", "Publish Error", JOptionPane.ERROR_MESSAGE);
                // reset image
                lblImageStatus.setText("");
                imageFile = null;
            }
        }
    }//GEN-LAST:event_btnUploadImageActionPerformed

    private void btnCreateGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateGroupActionPerformed
        String textTitle;
        String textDescription;
        try {
            textTitle = ValidateInputText(textFieldTitle);
            textDescription = ValidateInputText(textAreaDescription);
            // create groupService
            groupService = GroupService.getInstance();
            // copy selected image to database and get new image path
            String imagePath = ImageManager.copyImageToProgramFiles(user, imageFile);
            if(textTitle.isEmpty() || textDescription.isEmpty() || imagePath == null)
                throw new IOException("Fill in all fields");
            // create group
            groupService.createGroup(textTitle, textDescription, imagePath, user.getUserId());
            this.dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Cannot Create Group: " + ex.getMessage(), "Creation Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCreateGroupActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textFieldTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldTitleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label Title;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton btnCreateGroup;
    private javax.swing.JButton btnUploadImage;
    private javax.swing.JPanel createPostPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblImageStatus;
    private javax.swing.JLabel lblImageUpload;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel previewImage;
    private javax.swing.JTextArea textAreaDescription;
    private javax.swing.JTextField textFieldTitle;
    // End of variables declaration//GEN-END:variables
}
