package view;
import controller.GameController;
import javax.imageio.ImageIO;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.border.EmptyBorder;
import java.awt.GradientPaint;
import java.awt.RenderingHints;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class MainMenuPanel extends JPanel {
    private Image backgroundImage;
    public MainMenuPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        
        // Create title label
        JLabel titleLabel = new JLabel("Smoothie Maker Challenge", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        add(titleLabel, BorderLayout.NORTH);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        
        // Create and style buttons
        JButton[] buttons = {
            new JButton("Play Game"),
            new JButton("Upgrades"),
            new JButton("Scoreboard"),
            new JButton("Exit")
        };
        
        for (JButton button : buttons) {
            styleButton(button);
            buttonPanel.add(button);
        }
        
        add(buttonPanel, BorderLayout.CENTER);
    }

    public MainMenuPanel(GameController controller) {
        this();
        setLayout(new GridBagLayout());
        
        // Load background image
        try {
            backgroundImage = ImageIO.read(new URL("https://example.com/smoothie-bg.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Style and add start button
        JButton startButton = new JButton("Start Game");
        styleButton(startButton);
        add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void styleButton(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBorder(new CompoundBorder(
            new LineBorder(new Color(255, 165, 0), 2), 
            new EmptyBorder(10, 20, 10, 20)
        ));
        button.setUI(new BasicButtonUI() {
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradient background
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(255, 140, 0),
                    0, c.getHeight(), new Color(255, 69, 0)
                );
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 15, 15);
                super.paint(g2, c);
                button.repaint();
                g2.dispose();
            }
        });
    }
}