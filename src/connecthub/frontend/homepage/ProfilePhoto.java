package connecthub.frontend.homepage;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ProfilePhoto extends javax.swing.JPanel {
    private Image image;
    private int size;
    private int strokeLength;

    public ProfilePhoto(Image image, int size, int strokeLength) {
        
        this.image = image;
        this.size = size;
        this.strokeLength = strokeLength;
        this.setPreferredSize(new Dimension(size, size)); // Ensure the panel matches the size
        this.setMaximumSize(new Dimension(size, size));   // Prevent stretching
        this.setMinimumSize(new Dimension(size, size));   // Ensure consistent dimensions
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            // Define the circular clip
            Ellipse2D.Double clip = new Ellipse2D.Double(strokeLength / 2.0, strokeLength / 2.0, size - strokeLength, size - strokeLength);

            // Save the current clip and set the new clip for the image rendering
            Shape originalClip = g2d.getClip();
            g2d.clip(clip);

            // Scale the image to fit the circular area
            g2d.drawImage(image, 0, 0, size, size, this);

            // Reset the clip after drawing the image
            g2d.setClip(originalClip);

            // Draw the border within the visible bounds
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(strokeLength));
            g2d.draw(clip);

            g2d.dispose();
        }
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
