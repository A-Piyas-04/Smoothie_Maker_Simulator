import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlenderAnimationPanel extends JPanel {
    private float angle = 0;
    private Timer animationTimer;
    private int progress = 0;
    private Runnable onAnimationComplete;
    
    public BlenderAnimationPanel() {
        setPreferredSize(new Dimension(200, 200));
        animationTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angle += 0.3;
                progress = Math.min(progress + 2, 100);
                if (progress >= 100) {
                    animationTimer.stop();
                    if (onAnimationComplete != null) {
                        onAnimationComplete.run();
                    }
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Set smooth rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw blender base
        g2d.setColor(new Color(80, 80, 80));
        g2d.fillRoundRect(50, 50, 100, 100, 20, 20);

        // Draw rotating blades
        g2d.setColor(Color.WHITE);
        g2d.translate(100, 100);
        g2d.rotate(angle);
        for(int i=0; i<4; i++) {
            g2d.fillRect(-5, -40, 10, 20);
            g2d.rotate(Math.PI/2);
        }

        // Draw progress bar
        g2d.dispose();
        g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(0, 150, 0));
        g2d.fillRect(50, 160, progress, 20);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(50, 160, 100, 20);
    }

    public void startAnimation() {
        progress = 0;
        animationTimer.start();
    }

    public void stopAnimation() {
        animationTimer.stop();
    }

    public void setOnAnimationComplete(Runnable callback) {
        this.onAnimationComplete = callback;
    }
}