package view;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel() {
        setLayout(new BorderLayout());
        
        // Create title label
        JLabel titleLabel = new JLabel("Smoothie Maker Challenge", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        add(titleLabel, BorderLayout.NORTH);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        
        JButton playButton = new JButton("Play Game");
        JButton upgradesButton = new JButton("Upgrades");
        JButton scoreboardButton = new JButton("Scoreboard");
        JButton exitButton = new JButton("Exit");
        
        // Add buttons to panel
        buttonPanel.add(playButton);
        buttonPanel.add(upgradesButton);
        buttonPanel.add(scoreboardButton);
        buttonPanel.add(exitButton);
        
        add(buttonPanel, BorderLayout.CENTER);
    }
}