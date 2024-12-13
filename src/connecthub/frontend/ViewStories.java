package connecthub.frontend;

import connecthub.backend.models.Story;
import connecthub.backend.models.User;
import connecthub.backend.services.UserService;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class ViewStories extends javax.swing.JDialog {

    List<Story> stories;
    private static int storyIndex;

    public ViewStories(java.awt.Frame parent, boolean modal, List<Story> stories) {
        super(parent, modal);
        initComponents();
        this.stories = stories;
        storyIndex = 1;
        storyNumber.setText(Integer.toString(storyIndex));
        totalStoriesNumber.setText(Integer.toString(stories.size()));
        changeStory(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        previousStory = new javax.swing.JToggleButton();
        nextStory = new javax.swing.JToggleButton();
        authorName = new javax.swing.JLabel();
        timeStamp = new javax.swing.JLabel();
        storyText = new javax.swing.JLabel();
        storyImage = new javax.swing.JLabel();
        storyNumber = new javax.swing.JLabel();
        separator = new javax.swing.JLabel();
        totalStoriesNumber = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View Stories");
        setResizable(false);

        previousStory.setText("Previous Story");
        previousStory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousStoryActionPerformed(evt);
            }
        });

        nextStory.setText("Next Story");
        nextStory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextStoryActionPerformed(evt);
            }
        });

        authorName.setText("Author Name");
        authorName.setToolTipText("");

        timeStamp.setText("Date & Time");

        storyText.setText("Text");

        storyImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        storyNumber.setText("##");

        separator.setText("/");

        totalStoriesNumber.setText("##");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(storyImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(storyText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(previousStory, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(storyNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(separator)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalStoriesNumber)
                        .addGap(59, 59, 59)
                        .addComponent(nextStory, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(storyText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(storyImage, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previousStory)
                    .addComponent(nextStory)
                    .addComponent(storyNumber)
                    .addComponent(separator)
                    .addComponent(totalStoriesNumber))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void changeStory(int i) {
        storyIndex += i;
        String autherId = stories.get(stories.size() - storyIndex).getAuthorId();
        UserService userService = new UserService();
        User user = userService.getUserById(autherId);
        authorName.setText(user.getUsername());
        timeStamp.setText(stories.get(stories.size() - storyIndex).getTimestamp().toString());
        storyText.setText(stories.get(stories.size() - storyIndex).getContentData().getText());
        String path = stories.get(stories.size() - storyIndex).getContentData().getImagePath();
        if (path != null) {
            Image image = new ImageIcon(path).getImage();
            image = image.getScaledInstance(391, 188, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(image);
            storyImage.setIcon(icon);
        }
    }

    private void previousStoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousStoryActionPerformed
        int i = Integer.parseInt(storyNumber.getText());
        if (i > 1) {
            i--;
            storyNumber.setText(Integer.toString(i));
            changeStory(-1);
        }
    }//GEN-LAST:event_previousStoryActionPerformed

    private void nextStoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextStoryActionPerformed
        int i = Integer.parseInt(storyNumber.getText());
        int n = Integer.parseInt(totalStoriesNumber.getText());
        if (i < n) {
            i++;
            storyNumber.setText(Integer.toString(i));
            changeStory(1);
        }
    }//GEN-LAST:event_nextStoryActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorName;
    private javax.swing.JToggleButton nextStory;
    private javax.swing.JToggleButton previousStory;
    private javax.swing.JLabel separator;
    private javax.swing.JLabel storyImage;
    private javax.swing.JLabel storyNumber;
    private javax.swing.JLabel storyText;
    private javax.swing.JLabel timeStamp;
    private javax.swing.JLabel totalStoriesNumber;
    // End of variables declaration//GEN-END:variables
}
