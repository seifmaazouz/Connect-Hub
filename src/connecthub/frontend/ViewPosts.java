package connecthub.frontend;

import connecthub.backend.models.Post;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class ViewPosts extends javax.swing.JDialog {

    List<Post> posts;
    private static int postIndex;
    
    public ViewPosts(java.awt.Frame parent, boolean modal, List<Post> posts, String userName) {
        super(parent, modal);
        initComponents();
        this.posts = posts;
        authorName.setText(userName);
        postIndex = 1;
        postNumber.setText(Integer.toString(postIndex));
        totalPostsNumber.setText(Integer.toString(posts.size()));
        changePost(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        previousPost = new javax.swing.JToggleButton();
        nextPost = new javax.swing.JToggleButton();
        authorName = new javax.swing.JLabel();
        timeStamp = new javax.swing.JLabel();
        postText = new javax.swing.JLabel();
        postImage = new javax.swing.JLabel();
        postNumber = new javax.swing.JLabel();
        separator = new javax.swing.JLabel();
        totalPostsNumber = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View Posts");
        setResizable(false);

        previousPost.setText("Previous Post");
        previousPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousPostActionPerformed(evt);
            }
        });

        nextPost.setText("Next Post");
        nextPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextPostActionPerformed(evt);
            }
        });

        authorName.setText("Author Name");
        authorName.setToolTipText("");

        timeStamp.setText("Date & Time");

        postText.setText("Text");

        postImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        postNumber.setText("##");

        separator.setText("/");

        totalPostsNumber.setText("##");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(postImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(postText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(previousPost, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(postNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(separator)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalPostsNumber)
                        .addGap(59, 59, 59)
                        .addComponent(nextPost, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(authorName)
                            .addComponent(timeStamp))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(authorName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeStamp)
                .addGap(7, 7, 7)
                .addComponent(postText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(postImage, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previousPost)
                    .addComponent(nextPost)
                    .addComponent(postNumber)
                    .addComponent(separator)
                    .addComponent(totalPostsNumber))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void changePost(int i)
    {
        postIndex += i;
        timeStamp.setText(posts.get(posts.size() - postIndex).getTimestamp().toString());
        postText.setText(posts.get(posts.size() - postIndex).getContentData().getText());
        String path = posts.get(posts.size() - postIndex).getContentData().getImagePath();
        if (path != null) {
            Image image = new ImageIcon(path).getImage();
            image = image.getScaledInstance(391, 188, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(image);
            postImage.setIcon(icon);
        }
        else
            postImage.setIcon(null);
    }
    
    private void previousPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousPostActionPerformed
        int i = Integer.parseInt(postNumber.getText());
        if (i > 1) {
            i--;
            postNumber.setText(Integer.toString(i));
            changePost(-1);
        }
    }//GEN-LAST:event_previousPostActionPerformed

    private void nextPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextPostActionPerformed
        int i = Integer.parseInt(postNumber.getText());
        int n = Integer.parseInt(totalPostsNumber.getText());
        if (i < n) {
            i++;
            postNumber.setText(Integer.toString(i));
            changePost(1);
        }
    }//GEN-LAST:event_nextPostActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewPosts dialog = new ViewPosts(new javax.swing.JFrame(), true, new ArrayList<>(), "asser");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorName;
    private javax.swing.JToggleButton nextPost;
    private javax.swing.JLabel postImage;
    private javax.swing.JLabel postNumber;
    private javax.swing.JLabel postText;
    private javax.swing.JToggleButton previousPost;
    private javax.swing.JLabel separator;
    private javax.swing.JLabel timeStamp;
    private javax.swing.JLabel totalPostsNumber;
    // End of variables declaration//GEN-END:variables
}
